package com.paymybuddy.paywebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transfer")
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "bankaccount_id")
    private int bankaccountId;
    @Column(name = "amount")
    private Float amount;
    @Column(name = "intime")
    private LocalDateTime intime;


//type de Transfer/Viremnt (retrait - depuis banque / dépot vers la banque)
    //Pas besoin de balance
}
