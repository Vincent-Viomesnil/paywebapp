package com.paymybuddy.paywebapp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private int number;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "amount")
    private float amount;
    @Column(name = "intime")
    private LocalDateTime intime;
    @Column(name = "description")
    private String description;
    @Column(name = "fee")
    private Float fee;


    //fichier config.properties (mettre les infos fee)
//
    @Transient
    private User userCreditor;
    @Transient
    private User userDebtor;
    public Transaction(int number, User userCreditor, User userDebtor, LocalDateTime intime, String description, float amount){
        //mette en place une sécurité (rollback, opérations en bdd débit/crédit/ Concept @Transactionnal
        this.number = number;
        this.userCreditor = userCreditor;
        this.userDebtor = userDebtor;
        this.intime = intime;
        this.description = description;
        this.amount = amount;
    }

}
