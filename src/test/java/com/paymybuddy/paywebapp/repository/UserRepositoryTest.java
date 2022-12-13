package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepositoryTest;


    @Test
    public void createUserTest() {
        User user = new User(1, "alex@email.com", "password", "alex", "lastname", 100);

        assertThat(userRepositoryTest.findAll().contains(user));
    }

    @Test
    public void getAUserByEmail() {
        User user = new User();
        user.setEmail("emailtest@email.com");

        userRepositoryTest.findByEmail(user.getEmail());

        assertThat(user.getEmail().contains("emailtest@email.com"));
    }

    @Test
    public void getAUserById() {
        User user = new User();
        user.setId(5);

        userRepositoryTest.findById(user.getId());

        assertThat(user.getId()).isEqualTo(5);
    }
}
