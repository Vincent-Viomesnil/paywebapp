package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Integer> {

    List<BankTransaction> findAll();

    Optional<BankTransaction> findById(Integer id);

    BankTransaction save(BankTransaction bankTransaction);
}
