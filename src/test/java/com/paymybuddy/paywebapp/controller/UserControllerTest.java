package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.repository.UserRepository;
import com.paymybuddy.paywebapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void getLogin() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserTest() throws Exception {

        User user = new User();
        user.setId(1);
        user.setBalance(125);
        user.setPassword("pass");
        user.setEmail("email");
        user.setFirstname("prenom");
        user.setLastname("nom");

        mockMvc.perform(get("/registry?id=1&password=pass&email=email&firstname=prenom&lastname=nom&balance=125")).andExpect(status().isOk());

    }

    @Test
    public void getAUserById() throws Exception {
        User user = new User();
        user.setId(1);
        mockMvc.perform(get("/user?id=1"))
                .andExpect(status().isOk());

    }
}

