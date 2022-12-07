package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.BankTransaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public void transfer(User user, String bankAccountIban, float amount) {
        String operationType = "transfer";
        LocalDateTime intime = LocalDateTime.now();
        User userConnected = userService.getUserByEmail(user.getEmail());
        BankAccount bankAccount = bankAccountService.getBankAccountByIban(bankAccountIban);
        BankTransaction bankTransaction = new BankTransaction(userConnected, bankAccount, amount, intime, operationType);

        userConnected.setBalance(userConnected.getBalance() - amount);

        bankTransactionRepository.save(bankTransaction);
    }

    public void deposit(User user, String bankAccountIban, float amount) {
        String operationType = "deposit";
        LocalDateTime intime = LocalDateTime.now();
        User userConnected = userService.getUserByEmail(user.getEmail());
        BankAccount bankAccount = bankAccountService.getBankAccountByIban(bankAccountIban);
        BankTransaction bankTransaction = new BankTransaction(userConnected, bankAccount, amount, intime, operationType);

        userConnected.setBalance(userConnected.getBalance() + amount);

        bankTransactionRepository.save(bankTransaction);
    }

    public Page<BankTransaction> findPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by("intime").descending());
        return this.bankTransactionRepository.findAll(pageable);
    }
}
