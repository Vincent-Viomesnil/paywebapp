package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;


    @Test
    public void createUserTes(){
        User user = new User();
        user.setEmail("alex@email.com");
        user.setPassword("012230");
        user.setFirstname("Alex");
        user.setLastname("Edi");
        user.setBalance(10);

        userRepository.save(user);

        assertThat(userRepository.findAll().contains(user));
    }
    @Test
    public void getAUserByEmail(){
        User user = new User();
        user.setEmail("emailtest@email.com");

        userRepository.findByEmail(user.getEmail());

        assertThat(user.getEmail().contains("emailtest@email.com"));
    }

    @Test
    public void getAUserById(){
        User user = new User();
        user.setId(5);

        userRepository.findById(user.getId());

        assertThat(user.getId()).isEqualTo(5);
    }
}
