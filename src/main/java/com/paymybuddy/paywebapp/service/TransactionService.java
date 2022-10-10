package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.Transfer;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Iterable<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

//    public Transaction addTransaction(Transaction transaction) {
//
//        return transactionRepository.save(transaction);
//    }

    public Transaction doTransaction(Transaction transaction) {
        User userCreditor = new User();
        User userDebtor = new User();
        if (userCreditor.getMoney()) {
            log.info("User have money" + "/r/n" + "UserCreditor " +userCreditor + "/r/n" + "UserDebtor " +userDebtor);
        transaction.setAmount(transaction.getAmount());
        transaction.setIntime(transaction.getIntime());
        transaction.setDescription(transaction.getDescription());
        transaction.setUserEmail(userCreditor.getEmail());
        transaction.setUserCreditor(userCreditor);
        transaction.setUserDebtor(userDebtor);
            return transactionRepository.save(transaction);
        }
        log.error("Transaction doesn't work");
        return null;
    }
}
