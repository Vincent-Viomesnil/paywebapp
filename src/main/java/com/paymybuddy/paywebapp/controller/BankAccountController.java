package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.service.BankAccountService;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/bankaccount")
    public BankAccount addBankAccount(@RequestParam(value = "userid") int userId, @RequestParam(value = "iban") String iban) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setUserId(userId);
        bankAccount.setIban(iban);

        return bankAccountService.addBankAccount(bankAccount);
    }

    @GetMapping("/bankacccounts")
    public Iterable<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBanksAccounts();
    }

    @GetMapping(value = "/bankid")
    public Optional<BankAccount> getBankAccountById(@RequestParam(value = "id") Integer id) {
        Optional<BankAccount> bankAccountById = bankAccountService.getBankAccountById(id);
        if (bankAccountById.isPresent()) {
            log.info("Request get bankaccount by id SUCCESS");
        } else {
            log.error("Request get bankaccount by id FAILED");
        }
        return bankAccountById;
    }

    @GetMapping(value = "/bankiban")
    public Optional<BankAccount> getBankAccountByIban(@RequestParam(value = "iban") String iban) {
        Optional<BankAccount> bankAccountByIban = bankAccountService.getBankAccountByIban(iban);
        if (bankAccountByIban.isPresent()) {
            log.info("Request get bankaccount by iban SUCCESS");
        } else {
            log.error("Request get bankaccount by iban FAILED");

        }
        return bankAccountByIban;
    }

    @GetMapping(value = "/bankuserid")
    public Optional<BankAccount> getBankAccountByUserId(@RequestParam(value = "userid") Integer userId) {
        Optional<BankAccount> bankAccountByUserId = bankAccountService.getBankAccountByUserId(userId);
        if (bankAccountByUserId.isPresent()) {
            log.info("Request get bankaccount by userId SUCCESS");

        } else {
            log.error("Request get bankaccount by userId FAILED");
        }
        return bankAccountByUserId;
    }

    @PutMapping(value = "/bankuserid/{userId}")
    public BankAccount updateBankAccount(@PathVariable Integer userId, @RequestParam String iban){
        return bankAccountService.updateBankAccount(userId,iban);
//        if (updateBankAccount != null) {
//            log.info("Update bankaccount request SUCCESS");
//        } else {
//            log.error("Update bankaccount request FAILED, the userID doesn't exist");
//        } return updateBankAccount;
    }
}
