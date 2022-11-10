package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.BankTransaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.BankTransactionService;
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
public class BankTransactionController {

    @Autowired
    private BankTransactionService bankTransactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/user_home")
    public String getAllTransactions(Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        List<BankTransaction> bankTransactionList = userConnected.getBankTransactionList();
        model.addAttribute("banktransaction", new BankTransaction());
        model.addAttribute("banktransactionlist", bankTransactionList);
        model.addAttribute("user", user);
        model.addAttribute("userconnected", userConnected);
        return "user_home"; //renvoit à la page HTML du même nom
    }


    @PostMapping("/banktransaction")
    public String depositMoney(@AuthenticationPrincipal UserPrincipal user,
                               @ModelAttribute BankTransaction bankTransaction, Model model) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        bankTransactionService.deposit(userConnected, bankTransaction.getAmount());
        return "redirect:/banktransaction";

    }


}
