package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.BankTransaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.BankAccountService;
import com.paymybuddy.paywebapp.service.BankTransactionService;
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
public class BankTransactionController {

    @Autowired
    private BankTransactionService bankTransactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/banktransaction")
    public String getAllBankTransactions(Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        List<BankTransaction> bankTransactionList = userConnected.getBankTransactionList();
        model.addAttribute("banktransaction", new BankTransaction());
        model.addAttribute("banktransactionlist", bankTransactionList);
        model.addAttribute("bankaccount", new BankAccount());
        model.addAttribute("userconnected", userConnected);
        return "bankaccount"; //renvoit à la page HTML du même nom
    }


    @PostMapping("/depositmoney")
    public String depositMoney(@AuthenticationPrincipal UserPrincipal user, String iban, float amount) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountIban = bankAccountService.getBankAccountByIban(iban);

        if (userConnected.getBankAccountList().contains(bankAccountIban)) {
            bankTransactionService.deposit(userConnected, iban, amount);
            return "redirect:/banktransaction";
        } else {
            return "redirect:/banktransaction";
        }
    }

    @PostMapping("/withdrawmoney")
    public String withdrawMoney(@AuthenticationPrincipal UserPrincipal user, String iban, float amount) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountIban = bankAccountService.getBankAccountByIban(iban);

        if (userConnected.getBankAccountList().contains(bankAccountIban)) {
            bankTransactionService.withdraw(userConnected, iban, amount);
            return "redirect:/banktransaction";
        } else {
            return "redirect:/banktransaction";
        }
    }

}
