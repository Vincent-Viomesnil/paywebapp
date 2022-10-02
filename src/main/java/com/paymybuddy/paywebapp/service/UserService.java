package com.paymybuddy.paywebapp.service;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null){
            log.info("Post new User SUCCESS");
            return userRepository.save(user);
        } else {
            log.error("email already exists");
            return null;
        }

    }
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) {
       return userRepository.findByEmail(email);
    }

//    public void getContactlist(String email) {
//        //User loggé qui veut ajouter à sa liste de contacts la nouvelle personne
//        if (userRepository.findByEmail(email) == null) {
//            User user = new User();
//            List<User> contactsList = new ArrayList<>();
//            user.setFirstname(user.getFirstname());
//            user.setLastname(user.getLastname());
//            user.setEmail(user.getEmail());
//            contactsList.add(user);
//        }
//    }
//
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
