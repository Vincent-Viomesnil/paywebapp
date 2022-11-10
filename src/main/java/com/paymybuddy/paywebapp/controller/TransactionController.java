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
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactionList", transactionList);
        return "addTransaction"; //renvoit à la page HTML du même nom
    }


    @PostMapping("/transaction")
    public String sendMoney(@AuthenticationPrincipal UserPrincipal user,
                            @ModelAttribute Transaction transaction, Model model) {
        //@ModelAttribute Transaction récupère la transaction du getmapping
        User userConnected = userService.getUserByEmail(user.getUsername());
        User userDebtor = userService.getUserByEmail(transaction.getUserDebtor().getEmail());

        if (userConnected.getContactUserList().contains(userDebtor)) {
            transactionService.sendMoney(userConnected, userDebtor, transaction.getDescription(), transaction.getAmount());
//        model.addAttribute("transaction", transaction);
//            model.addAttribute("emaildebtor", userDebtor);

            //Liste déroulante avec les emails de la liste de contacts
            return "redirect:/transaction";
        } else {
            log.error("The email is not correct or not in the contact list");
            return "redirect:/transaction";
        }
    }


}
