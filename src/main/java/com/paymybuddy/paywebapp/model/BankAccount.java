package com.paymybuddy.paywebapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name="id_user")
    private int id_user;
    @Column(name="iban")
    private String iban;

}
