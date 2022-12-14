package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    List<BankAccount> findAll();

    Optional<BankAccount> findById(Integer id);

    BankAccount save(BankAccount bankAccount);

    BankAccount findByIban(String iban);

    BankAccount findByUserId(Integer userId);


}
