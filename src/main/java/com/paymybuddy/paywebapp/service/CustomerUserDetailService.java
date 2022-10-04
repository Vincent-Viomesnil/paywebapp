package com.paymybuddy.paywebapp.service;


import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            log.error("User not found: {} " + email);
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }

        log.info("Found User: {}" + user);

      UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
              .password(user.getPassword())
              .authorities("USER").build();

      return userDetails;


    }
}