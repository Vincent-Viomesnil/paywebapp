package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository  extends CrudRepository<BankAccount, Integer> {

    List<BankAccount> findAll();
    Optional<BankAccount> findById(Integer id);

    BankAccount save(BankAccount bankAccount);
}
