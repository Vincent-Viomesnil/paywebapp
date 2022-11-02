package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getAllUsersEmail(String email) {
        User user1 = null;
        if (userRepository.findByEmail(email) != null) {
            user1 = new User();
            user1.setEmail(user1.getEmail());
            user1.setFirstname(user1.getEmail());
            user1.setLastname(user1.getLastname());

            log.info("this is user1 created :" + user1);

        }
        return user1;
    }

    public User addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null){
            log.info("Post new User SUCCESS");

            user.setLastname(user.getLastname());
            user.setFirstname(user.getFirstname());
            user.setEmail(user.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setBalance(0.00f);

            return userRepository.save(user);
        } else {
            log.error("email already exists : " +user.getEmail());
            return null;
        }
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

    public Iterable<User> getAllUsersPrincipal() {
        List<User> contactsList = new ArrayList<>();
        for (User user : contactsList) {
            log.info("user : " +user);
            if (userRepository.findByEmail(user.getEmail()) != null){
                user.setEmail(user.getEmail());
                user.setLastname(user.getLastname());
                user.setFirstname(user.getFirstname());

                contactsList.add(user);
            }
        }
        return contactsList;
    }


    public void getAllContacts(UserPrincipal userPrincipal){
    //User loggé qui veut ajouter à sa liste de contacts la nouvelle personne
        User user = new User();
        User user1 = new User();
        List<User> contactsList = new ArrayList<>();
        user.setFirstname(user1.getFirstname());
        user.setLastname(user1.getLastname());
        user.setEmail(user1.getEmail());
        contactsList.add(user);
    }

    public void addListUsers(List<User> userList) {
    }


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        final User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException(email);
//        }
//
//        return org.springframework.security.core.userdetails.User.withUsername(user.getEmail()).password(user.getPassword()).authorities("USER").build();
//    }
}
