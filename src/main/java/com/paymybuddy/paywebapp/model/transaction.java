package com.paymybuddy.paywebapp.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class transaction {
    private int id;
    private String email;
    private float amount;
    private LocalDateTime intime;
    private String description;
}
