package com.paymybuddy.paywebapp.model;


import lombok.*;

import javax.persistence.*;
import java.sql.ConnectionBuilder;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name="balance")
    private float balance;

    @OneToMany( //relation unidirectionnelle One to many
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY // Lazy afin de ne pas loader toutes les infos des transfers liée à un user.
    )
    @JoinColumn(name = "user_id")
    private List<Transfer> transferList = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY // Lazy afin de ne pas loader toutes les infos des bankAccount liée à un user.
    )
    @JoinColumn(name = "user_id")
    private List<BankAccount> bankAccountList = new ArrayList<>();

    public User(int id, String email, String password, String firstname, String lastname, String description, int balance) {
    }

    public User(String firstName, String lastName) {
    }

}
