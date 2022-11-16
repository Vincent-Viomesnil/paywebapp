package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.model.BankTransaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.BankAccountService;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;


    @GetMapping("/bankaccount")
    public String getAllBankaccounts(Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        List<BankAccount> bankAccountList = userConnected.getBankAccountList();
        List<BankTransaction> bankTransactionList = userConnected.getBankTransactionList();
        model.addAttribute("banktransaction", new BankTransaction());
        model.addAttribute("banktransactionlist", bankTransactionList);
        model.addAttribute("bankaccount", new BankAccount());
        model.addAttribute("bankaccountlist", bankAccountList);

        return "bankaccount";
    }

    @PostMapping("/addbankaccount")
    public String addBankAccount(@AuthenticationPrincipal UserPrincipal user,
                                 BankAccount bankAccount, RedirectAttributes Redir) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountExisting = bankAccountService.getBankAccountByIban(bankAccount.getIban());
        if (userConnected.getBankAccountList().contains(bankAccountExisting)) {
            Redir.addFlashAttribute("errorbankaccount", "KO");
            log.error("Iban bankaccount already exist : " + bankAccount.getIban());
            return "redirect:/bankaccount";
        } else {
            Redir.addFlashAttribute("bankaccountsuccess", "OK");
            log.info("bankaccount can be created");
            bankAccountService.addBankAccount(userConnected, bankAccount.getName(), bankAccount.getIban());
            return "redirect:/bankaccount";
        }
    }


    @GetMapping("/bankacccounts")
    public Iterable<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBanksAccounts();
    }


}
