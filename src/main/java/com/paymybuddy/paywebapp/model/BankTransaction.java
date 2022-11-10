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
@Table(name = "banktransaction")
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
    private float amount;
    @Column(name = "intime")
    private LocalDateTime intime;
    @Column(name = "transfer_type")
    private String transferType;


//type de Transfer/Viremnt (retrait - depuis banque / dépot vers la banque)
    //Pas besoin de balance

    public BankTransaction(int userId, float amount, LocalDateTime intime, String transferType) {
        this.userId = userId;
        this.amount = amount;
        this.intime = intime;
        this.transferType = transferType;
    }
}
