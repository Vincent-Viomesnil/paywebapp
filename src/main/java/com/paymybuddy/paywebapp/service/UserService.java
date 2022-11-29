package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public void addContact(User user, User contactToAdd) {
        user.addContactUser(contactToAdd);
        userRepository.save(user);
        log.info("The User : " + user + "as added this contact =>" + contactToAdd);
    }

    public User addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            log.info("Post new User SUCCESS");

            user.setLastname(user.getLastname());
            user.setFirstname(user.getFirstname());
            user.setEmail(user.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setBalance(0.00f);

            return userRepository.save(user);
        } else {
            log.error("email already exists : " + user.getEmail());
            return null;
        }
    }

    public void deleteContact(User user, User contactToDelete) {
        user.deleteContact(contactToDelete);
        log.info("The User : " + user + "as delete this contact =>" + contactToDelete);
    }

}