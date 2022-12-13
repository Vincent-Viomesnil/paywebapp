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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping("/banktransactions")
    public String findPaginated(@RequestParam(value = "page", defaultValue = "1") int currentPage,
                                Model model, @AuthenticationPrincipal UserPrincipal user) {

        User userConnected = userService.getUserByEmail(user.getUsername());
        Pageable pageable = PageRequest.of(currentPage - 1, 5, Sort.by("intime").descending());
        Page<BankTransaction> listBankTransaction = bankTransactionService.getPaginated(userConnected, pageable);
        List<BankTransaction> bankTransactionPageList = listBankTransaction.getContent();
        List<BankAccount> bankAccountList = userConnected.getBankAccountList();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", listBankTransaction.getTotalPages());
        model.addAttribute("totalBankTransactions", listBankTransaction.getTotalElements());
        model.addAttribute("bankTransactionPageList", bankTransactionPageList);
        model.addAttribute("bankaccountList", bankAccountList);
        model.addAttribute("banktransaction", new BankTransaction());

        return "banktransactions";
    }


    @RequestMapping(value = "/banktransactions", method = RequestMethod.POST, params = "action=transfermoney")
    public String depositMoney(@AuthenticationPrincipal UserPrincipal user, @ModelAttribute BankAccount bankAccount, @ModelAttribute BankTransaction bankTransaction, RedirectAttributes Redir) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountIban = bankAccountService.getBankAccountByIban(bankAccount.getIban());

        if (userConnected.getBankAccountList().contains(bankAccountIban) && bankTransaction.getAmount() <= userConnected.getBalance()) {
            bankTransactionService.transfer(userConnected, bankAccount.getIban(), bankTransaction.getAmount());
            Redir.addFlashAttribute("banktransactionsuccess", "OK");
            return "redirect:/banktransactions";
        } else {
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/banktransactions";
        }
    }

    @RequestMapping(value = "/banktransactions", method = RequestMethod.POST, params = "action=depositmoney")
    public String withdrawMoney(@AuthenticationPrincipal UserPrincipal user, @ModelAttribute BankAccount bankAccount, @ModelAttribute BankTransaction bankTransaction, RedirectAttributes Redir) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountIban = bankAccountService.getBankAccountByIban(bankAccount.getIban());

        if (userConnected.getBankAccountList().contains(bankAccountIban)) {
            bankTransactionService.deposit(userConnected, bankAccount.getIban(), bankTransaction.getAmount());
            Redir.addFlashAttribute("banktransactionsuccess", "OK");
            return "redirect:/banktransactions";
        } else {
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/banktransactions";
        }
    }

}
