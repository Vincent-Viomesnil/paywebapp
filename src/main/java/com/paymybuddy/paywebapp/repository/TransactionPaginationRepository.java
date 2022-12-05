package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionPaginationRepository extends PagingAndSortingRepository<Transaction, Integer> {

}
