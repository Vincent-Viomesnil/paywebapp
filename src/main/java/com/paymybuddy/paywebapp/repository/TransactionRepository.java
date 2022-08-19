package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.Transfer;
import com.paymybuddy.paywebapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {

    List<Transaction> findAll();
    Optional<Transaction> findById(Integer id);

    Transaction save(Transaction transaction);
}
