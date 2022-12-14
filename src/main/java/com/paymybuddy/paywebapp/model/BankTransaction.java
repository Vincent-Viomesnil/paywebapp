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
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bankaccount_id", referencedColumnName = "id")
    private BankAccount bankAccount;
    @Column(name = "amount")
    private float amount;
    @Column(name = "intime")
    private LocalDateTime intime;
    @Column(name = "operation_type")
    private String operationType;

    public BankTransaction(User user, BankAccount bankAccount, float amount, LocalDateTime intime, String operationType) {
        this.user = user;
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.intime = intime;
        this.operationType = operationType;
    }
}
