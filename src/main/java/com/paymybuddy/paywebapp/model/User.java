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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email", length = 60)
    @NotNull(message = "email is mandatory")
    private String email;
    @Column(name = "password", length = 64)
    @NotNull(message = "password is mandatory")
    private String password;
    @Column(name = "firstname", length = 60)
    @NotNull(message = "firstname is mandatory")
    private String firstname;
    @Column(name = "lastname", length = 60)
    @NotNull(message = "lastname is mandatory")
    private String lastname;
    @Column(name = "balance")
    float balance;

    @OneToMany( //relation unidirectionnelle One to many
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY // Lazy afin de ne pas loader toutes les infos des transfers liée à un user.
    )
    @JoinColumn(name = "user_id")
    private List<Transfer> transferList = new ArrayList<>();


    @OneToMany( //relation unidirectionnelle One to many
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY // Lazy afin de ne pas loader toutes les infos des transfers liée à un user.
    )
    @JoinColumn(name = "user_id")
    private List<Transaction> transactionList = new ArrayList<>();

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id")
    private BankAccount bankAccount;

    public User(int id, String email, String password, String firstname, String lastname, float balance) {
    }


    public boolean getMoney() {
        User userCreditor = new User();
        if (userCreditor.getBalance() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

    @OneToMany
    public List<User> contactUserList;

    public void addContactUser(User contact) {
        contactUserList.add(contact);
    }

    public void deleteContact(User contactToDelete) {
        contactUserList.remove(contactToDelete);
    }


}
