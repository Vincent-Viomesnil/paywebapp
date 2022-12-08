package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    private static final float FEE = 0.005f;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }


    public void sendMoney(User userCreditor, String userDebtorEmail, String description, float amount) {
        User userDebtor = userService.getUserByEmail(userDebtorEmail);
        LocalDateTime intime = LocalDateTime.now();
        float fee = amount * FEE;

        if (userCreditor.getBalance() < amount || amount < 0) {
            System.out.println("Transfer fails");
        } else {
            Transaction transaction = new Transaction(userCreditor, userDebtor, amount, intime, description, fee);
            userCreditor.setBalance(userCreditor.getBalance() - amount);
            userDebtor.setBalance(userDebtor.getBalance() + (amount - fee));
            transactionRepository.save(transaction);
        }
    }


    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Page<Transaction> getPaginated(User userCreditor, Pageable pageable) {

        Page<Transaction> transactionsSentList = transactionRepository.getTransactionsByUserCreditorOrderByIntimeDesc(userCreditor, pageable);
        return transactionsSentList;
    }

}



