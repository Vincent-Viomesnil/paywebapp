package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.Transfer;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }


    public void sendMoney(User userCreditor, String emailUserDebtor, String description, float amount) {
       User userDebtor = userService.getUserByEmail(emailUserDebtor);
       LocalDateTime intime = LocalDateTime.now();
       float fee = amount * FEE;


       Transaction transaction = new Transaction(userCreditor, userDebtor, amount, intime, description, fee);
       transaction.transferMoney(userDebtor, amount);

       transactionRepository.save(transaction);
       log.info("Transaction OK :" +transaction);

    }


}
