package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.BankAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class BankAccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserService userService;

    public List<BankAccount> getAllBanksAccounts() {
        return bankAccountRepository.findAll();
    }


    public void addBankAccount(User user, String name, String iban) {
        User userConnected = userService.getUserByEmail(user.getEmail());

        if (bankAccountRepository.findByIban(iban) == null) {
            log.info("Post new bankAccount SUCCESS");
            BankAccount bankAccount = new BankAccount(userConnected, name, iban);
            bankAccountRepository.save(bankAccount);

        } else {
            log.error("bankAccount already exists, iban => " + iban);
        }
    }

    public BankAccount getBankAccountByIban(String iban) {
        return bankAccountRepository.findByIban(iban);
    }

    public BankAccount getBankAccountByUserId(Integer userId) {
        return bankAccountRepository.findByUserId(userId);
    }

}
