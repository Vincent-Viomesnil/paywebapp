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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping(value = "/banktransaction", method = RequestMethod.GET)
    public String getAllBankTransactions(Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        List<BankTransaction> bankTransactionList = userConnected.getBankTransactionList();
        model.addAttribute("banktransaction", new BankTransaction());
        model.addAttribute("banktransactionlist", bankTransactionList);
        model.addAttribute("bankaccount", new BankAccount());
        model.addAttribute("userconnected", userConnected);
        return "bankaccount"; //renvoit à la page HTML du même nom
    }


    @RequestMapping(value = "/banktransaction", method = RequestMethod.POST, params = "action=depositmoney")
    public String depositMoney(@AuthenticationPrincipal UserPrincipal user, String iban, float amount, RedirectAttributes Redir) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountIban = bankAccountService.getBankAccountByIban(iban);

        if (userConnected.getBankAccountList().contains(bankAccountIban) && amount <= userConnected.getBalance()) {
            bankTransactionService.deposit(userConnected, iban, amount);
            Redir.addFlashAttribute("banktransactionsuccess", "OK");
            return "redirect:/banktransaction";
        } else {
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/banktransaction";
        }
    }

    @RequestMapping(value = "/banktransaction", method = RequestMethod.POST, params = "action=withdrawmoney")
    public String withdrawMoney(@AuthenticationPrincipal UserPrincipal user, String iban, float amount, RedirectAttributes Redir) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountIban = bankAccountService.getBankAccountByIban(iban);

        if (userConnected.getBankAccountList().contains(bankAccountIban)) {
            bankTransactionService.withdraw(userConnected, iban, amount);
            Redir.addFlashAttribute("banktransactionsuccess", "OK");
            return "redirect:/banktransaction";
        } else {
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/banktransaction";
        }
    }

}
