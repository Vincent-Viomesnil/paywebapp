package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstname(String firstname);

    List<User> findAll();

    User addNewUser(User user);

    }
