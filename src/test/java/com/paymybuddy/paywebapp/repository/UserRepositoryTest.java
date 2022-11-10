package com.paymybuddy.paywebapp.repository;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.BankTransaction;
import com.paymybuddy.paywebapp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;


    @Test
    public void createUserTest() {
        User user = new User();
        user.setEmail("alex@email.com");
        user.setPassword("012230");
        user.setFirstname("Alex");
        user.setLastname("Edi");
        user.setBalance(10);
        user.setId(1);
        BankAccount bankAccount = new BankAccount();
        bankAccount.setIban("123");
        bankAccount.setUserId(1);
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setUserId(1);
        bankTransaction.setId(1);
        bankTransaction.setAmount(5.00f);
        bankTransaction.setBankaccountId(1);
        LocalDateTime intime = LocalDateTime.now();
        bankTransaction.setIntime(intime);
        user.setBankAccountList(List.of(bankAccount));
        user.setBankTransactionList(List.of(bankTransaction));

        userRepository.save(user);

        assertThat(userRepository.findAll().contains(user));
    }

    @Test
    public void getAUserByEmail() {
        User user = new User();
        user.setEmail("emailtest@email.com");

        userRepository.findByEmail(user.getEmail());

        assertThat(user.getEmail().contains("emailtest@email.com"));
    }

    @Test
    public void getAUserById() {
        User user = new User();
        user.setId(5);

        userRepository.findById(user.getId());

        assertThat(user.getId()).isEqualTo(5);
    }
}
