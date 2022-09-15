package com.paymybuddy.paywebapp.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="bankaccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="user_id")
    private int userId;
    @Column(name="iban")
    private String iban;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY // Lazy afin de ne pas loader toutes les infos des transfer liée à un bankAccount.
    )
    @JoinColumn(name = "user_id")
    private List<Transfer> transferList = new ArrayList<>();

}
