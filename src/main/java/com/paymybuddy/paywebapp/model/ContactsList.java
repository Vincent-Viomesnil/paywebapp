package com.paymybuddy.paywebapp.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactsList {
    private List<User> contactsList = new ArrayList<>();
}
