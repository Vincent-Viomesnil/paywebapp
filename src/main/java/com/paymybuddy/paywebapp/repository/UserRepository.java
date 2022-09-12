package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAll();

    User save(User user);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    }
