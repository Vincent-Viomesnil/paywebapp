package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.BankTransaction;
import com.paymybuddy.paywebapp.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BankTransactionService {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    public Iterable<BankTransaction> getAllTransfers() {
        return bankTransactionRepository.findAll();
    }

    public Optional<BankTransaction> getTransferById(Integer id) {
        return bankTransactionRepository.findById(id);
    }

    public BankTransaction addTransfer(BankTransaction bankTransaction) {
        return bankTransactionRepository.save(bankTransaction);


    }
}
