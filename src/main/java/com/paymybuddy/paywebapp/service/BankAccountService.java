package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.Transfer;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;


    public Iterable<BankAccount> getAllBanksAccounts() {
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getBankAccountById(Integer id) {
        return bankAccountRepository.findById(id);
    }

    public BankAccount addBankAccount(BankAccount bankAccount) {
        if (bankAccountRepository.findByUserId(bankAccount.getUserId()).isPresent()){
            log.error("bankAccount already exists");
            return null;
        } else {
            log.info("Post new bankAccount SUCCESS");
            return bankAccountRepository.save(bankAccount);
        }
    }

    public Optional<BankAccount> getBankAccountByIban(String iban) {
        return bankAccountRepository.findByIban(iban);
    }

    public Optional<BankAccount> getBankAccountByUserId(Integer userId) {
        return bankAccountRepository.findByUserId(userId);
    }

    public BankAccount updateBankAccount(Integer userId, String iban) {
        BankAccount bankAccount = new BankAccount();
        if (bankAccountRepository.findByUserId(userId).isPresent()){
            bankAccount.setUserId(userId);
            bankAccount.setIban(iban);
            log.info("Update bankaccount SUCCESS ");
            return bankAccountRepository.save(bankAccount);
        } else {
            log.error("Update bankaccount FAILED, userID doesn't exist");
            return null;
        }
    }
}
