package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User addUser(@RequestParam String email, @RequestParam String password, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String description, @RequestParam Float balance) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setDescription(description);
        user.setBalance(balance);

        return userService.addUser(user);
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user")
    public Optional<User> getUserById(@RequestParam(value = "id") Integer id) {
        Optional<User> userById =  userService.getUserById(id);
        if (userById.isEmpty()) {
            log.error("Request get user by id FAILED");
        } else {
            log.info("Request get user by id SUCCESS");
        }
        return userById;
    }
}
