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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/transaction")
    public String getAllTransactions(Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        List<Transaction> transactionList = userConnected.getTransactionList();
        List<User> contactsList = userConnected.getContactUserList();
        model.addAttribute("contactsList", contactsList);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactionList", transactionList);
        return "addTransaction"; //renvoit à la page HTML du même nom
    }


    @PostMapping("/transaction")
    public String sendMoney(@AuthenticationPrincipal UserPrincipal user, String userDebtorEmail, String description, float amount) {
        //@ModelAttribute Transaction récupère la transaction du getmapping
        //Enlever modeleAttribute et mettre en paramètres les objets.
        User userConnected = userService.getUserByEmail(user.getUsername());
        User userDebtor = userService.getUserByEmail(userDebtorEmail);

        if (userConnected.getContactUserList().contains(userDebtor)) {
            transactionService.sendMoney(userConnected, userDebtorEmail, description, amount);
            return "redirect:/transaction";
        } else {
            log.error("The email is not correct or not in the contact list");
            return "redirect:/transaction";
        }
    }


}
