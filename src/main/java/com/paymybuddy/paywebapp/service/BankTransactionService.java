package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.BankTransaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankTransactionService {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BankAccountService bankAccountService;

    public List<BankTransaction> getAllBankTransactions() {
        return bankTransactionRepository.findAll();
    }

    public Optional<BankTransaction> getBankTransactionById(Integer id) {
        return bankTransactionRepository.findById(id);
    }

    public BankTransaction addBankTransaction(BankTransaction bankTransaction) {
        return bankTransactionRepository.save(bankTransaction);
    }

    public void deposit(User user, String bankAccountIban, float amount) {
        String transferType = "deposit";
        LocalDateTime intime = LocalDateTime.now();
        User userConnected = userService.getUserByEmail(user.getEmail());
        BankAccount bankAccount = bankAccountService.getBankAccountByIban(bankAccountIban);
        BankTransaction bankTransaction = new BankTransaction(userConnected, bankAccount, amount, intime, transferType);

        userConnected.setBalance(userConnected.getBalance() - amount);

        bankTransactionRepository.save(bankTransaction);
    }

    public void withdraw(User user, String bankAccountIban, float amount) {
        String transferType = "withdraw";
        LocalDateTime intime = LocalDateTime.now();
        User userConnected = userService.getUserByEmail(user.getEmail());
        BankAccount bankAccount = bankAccountService.getBankAccountByIban(bankAccountIban);
        BankTransaction bankTransaction = new BankTransaction(userConnected, bankAccount, amount, intime, transferType);

        userConnected.setBalance(userConnected.getBalance() + amount);

        bankTransactionRepository.save(bankTransaction);
    }
}
