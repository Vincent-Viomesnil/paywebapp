package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAll();

    Optional<Transaction> findById(Integer id);

    Transaction save(Transaction transaction);

//    List<Transaction> findByDate(LocalDateTime date);


}
