package com.paymybuddy.paywebapp.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String description;
    private float balance;
}
