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
@Table(name ="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int number;
    private int id;
    private String email;
    private float amount;
    private LocalDateTime intime;
    private String description;
}
