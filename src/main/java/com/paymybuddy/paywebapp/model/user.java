package com.paymybuddy.paywebapp.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class user {
    private int id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private String description;
    private float balance;
}
