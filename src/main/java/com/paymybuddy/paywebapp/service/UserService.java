package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addNewUser(User user) {
        return userRepository.addNewUser(user);
    }

}
