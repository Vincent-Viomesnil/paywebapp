package com.paymybuddy.paywebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bankaccount")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    //    @Column(name = "user_id")
//    private int userId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Column(name = "name", length = 60)
    @NotNull(message = "iban is mandatory")
    private String name;

    @Column(name = "iban", length = 100)
    @NotNull(message = "iban is mandatory")
    private String iban;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY // Lazy afin de ne pas loader toutes les infos des transfer liée à un bankAccount.
    )
    @JoinColumn(name = "user_id")
    private List<BankTransaction> bankTransactionList = new ArrayList<>();

    public BankAccount(int id, User user, String name, String iban) {
        this.id = id;
        this.iban = iban;
        this.user = user;
        this.name = name;
    }

    public BankAccount(User user, String name, String iban) {
        this.user = user;
        this.name = name;
        this.iban = iban;

    }
}
