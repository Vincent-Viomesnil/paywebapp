package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

//    public User addUser(User user) {
//        User user1 = new User();
//            if (user.getEmail().contains(user1.getEmail())) {
//                log.error("email already exist");
//                return null;
//            } else {
//                user.setEmail(user.getEmail());
//                user.setPassword(user.getPassword());
//                user.setFirstname(user.getFirstname());
//                user.setLastname(user.getLastname());
//                user.setDescription(user.getDescription());
//                user.setBalance(user.getBalance());
//
//                log.info("Post new User SUCCESS");
//                return userRepository.save(user);
//            }
//    }

    public User addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            log.error("email already exists");
            return null;
        } else {
            log.info("Post new User SUCCESS");
            return userRepository.save(user);
        }

    }
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

}
