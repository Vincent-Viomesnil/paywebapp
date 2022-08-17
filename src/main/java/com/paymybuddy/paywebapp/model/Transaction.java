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
@GeneratedValue(strategy= GenerationType.IDENTITY)
@Column(name = "number")
private int number;
@Column(name = "id")
private int id;
@Column(name = "email")
private String email;
@Column(name = "amount")
private float amount;
@Column(name = "intime")
private LocalDateTime intime;
@Column(name = "description")
private String description;
@Column(name = "fee")
private Float fee;
}
