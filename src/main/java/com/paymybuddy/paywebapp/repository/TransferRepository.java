package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Integer> {

    List<Transfer> findAll();
    Optional<Transfer> findById(Integer id);

    Transfer save(Transfer transfer);
}
