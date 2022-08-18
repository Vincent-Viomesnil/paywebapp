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
@Table(name ="transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="id_user")
    private int id_user;
    @Column(name="id_bankaccount")
    private int id_bankaccount;
    @Column(name="amount")
    private Float amount;
    @Column(name = "intime")
    private LocalDateTime intime;


//type de Transfer/Viremnt (retrait - depuis banque / dépot vers la banque)
}
