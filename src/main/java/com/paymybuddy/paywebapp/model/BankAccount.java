package com.paymybuddy.paywebapp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="bankaccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="user_id")
    private int userId;
    @Column(name="iban", length = 100)
    @NotNull(message = "iban is mandatory")
    private String iban;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY // Lazy afin de ne pas loader toutes les infos des transfer liée à un bankAccount.
    )
    @JoinColumn(name = "user_id")
    private List<Transfer> transferList = new ArrayList<>();

}
