package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void sendMoney(User userCreditor, User userDebtor, String description, float amount) {
        User userDebtorEmail = userService.getUserByEmail(userDebtor.getEmail());
        LocalDateTime intime = LocalDateTime.now();
        float fee = amount * FEE;

        if (userCreditor.getBalance() < amount) {
            System.out.println("Transfer fails");
        } else {
            Transaction transaction = new Transaction(userCreditor, userDebtorEmail, amount, intime, description, fee);
            userCreditor.setBalance(userCreditor.getBalance() - amount);
            userDebtor.setBalance(userDebtor.getBalance() + (amount - fee));
            transactionRepository.save(transaction);


        }
    }

}


