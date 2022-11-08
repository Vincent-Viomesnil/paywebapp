package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.TransactionService;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/transaction")
    public String addTransaction(Model model) {

        model.addAttribute("transaction", new Transaction());

        return "addTransaction"; //renvoit à la page HTML du même nom
    }

    @PostMapping("/transaction")
    public String sendMoney(@AuthenticationPrincipal UserPrincipal user,
                            @ModelAttribute Transaction transaction) {
        //récupère la transaction du get
        User userConnected = userService.getUserByEmail(user.getUsername());
        User userDebtor = userService.getUserByEmail(transaction.getUserDebtor().getEmail());

        transactionService.sendMoney(userConnected, userDebtor, transaction.getDescription(), transaction.getAmount());//email ou objet ?
//        model.addAttribute("transaction", transaction);
//            model.addAttribute("emaildebtor", emailDebtor);

        //Liste déroulante avec les emails de la liste de contacts
        return "transaction_success";
    }


}
