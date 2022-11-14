package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.BankTransaction;
import com.paymybuddy.paywebapp.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankTransactionService {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Autowired
    private UserService userService;

    public List<BankTransaction> getAllBankTransactions() {
        return bankTransactionRepository.findAll();
    }

    public Optional<BankTransaction> getBankTransactionById(Integer id) {
        return bankTransactionRepository.findById(id);
    }

    public BankTransaction addBankTransaction(BankTransaction bankTransaction) {
        return bankTransactionRepository.save(bankTransaction);
    }

//    public void deposit(User user, float amount) {
//        String transferType = "deposit";
//        LocalDateTime intime = LocalDateTime.now();
//        BankTransaction bankTransaction = new BankTransaction(user amount, intime, transferType);
//
//        user.setBalance(user.getBalance() - amount);
//
//        bankTransactionRepository.save(bankTransaction);
//    }

}
