package com.paymybuddy.paywebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private int number;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userCreditor;
    @ManyToOne
    @JoinColumn(name = "user_debtor_id", referencedColumnName = "id")
    private User userDebtor;
    @Column(name = "amount")
    @NotNull(message = "amount is mandatory")
    private float amount;
    @Column(name = "intime")
    private LocalDateTime intime;
    @Column(name = "description", length = 200)
    @NotNull(message = "description is mandatory")
    private String description;
    @Column(name = "fee")
    private float fee;

    private static final float FEE = 0.005f;
    //fichier config.properties (mettre les infos fee)
//
//    private User userCreditor;
//    private User userDebtor;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    public User getUserCreditor() {
//        return userCreditor;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    public User getUserDebtor() {
//        return userDebtor;
//    }
//

    public Transaction(int number, User userCreditor, User userDebtor, LocalDateTime intime, float amount, String description, float fee) {
        //mette en place une sécurité (rollback, opérations en bdd débit/crédit/ Concept @Transactionnal
        this.number = number;
        this.userCreditor = userCreditor;
        this.userDebtor = userDebtor;
        this.amount = amount;
        this.intime = intime;
        this.description = description;
        this.fee = amount * FEE;
    }

    public Transaction(User userCreditor, User userDebtor, float amount, LocalDateTime intime, String description, float fee) {
        //mette en place une sécurité (rollback, opérations en bdd débit/crédit/ Concept @Transactionnal

        this.userCreditor = userCreditor;
        this.userDebtor = userDebtor;
        this.amount = amount;
        this.intime = intime;
        this.description = description;
        this.fee = amount * FEE;
    }

//    public void transferMoney(User userDebtor, float amount) {
//        if (this.userCreditor.getBalance() < amount) {
//            System.out.println("Transfer fails");
//        } else {
//            this.userCreditor.setBalance(userCreditor.getBalance() - amount);
//            this.userDebtor.setBalance(userDebtor.getBalance() + (amount * fee));
//        }
//    }


//    public void transaction(User userCreditor, User userDebtor,float amount) {
//        if (amount<userCreditor.getBalance() || amount != 0) {
//            userCreditor.transferMoney(userDebtor, amount);
//        } else {
//            System.out.println("No Transaction, please verify your balance");
//        }
//
//    }


}
