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
    public String getAllBankTransactions(Model model, @AuthenticationPrincipal UserPrincipal user) {
        return findPaginated(1, model, user);
    }

//    @RequestMapping(value = "/banktransaction", method = RequestMethod.GET)
//    public String getAllBankTransactions(Model model, @AuthenticationPrincipal UserPrincipal user) {
//        User userConnected = userService.getUserByEmail(user.getUsername());
//        List<BankTransaction> bankTransactionList = userConnected.getBankTransactionList();
//        model.addAttribute("banktransaction", new BankTransaction());
//        model.addAttribute("banktransactionlist", bankTransactionList);
//        model.addAttribute("bankaccount", new BankAccount());
//        model.addAttribute("userconnected", userConnected);
//        return "bankaccount"; //renvoit à la page HTML du même nom
//    }


    @GetMapping("/banktransactions/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        Page<BankTransaction> page = bankTransactionService.findPaginated(pageNo);
        List<BankTransaction> bankTransactionPageList = page.getContent();
        List<BankAccount> bankAccountList = userConnected.getBankAccountList();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalBankTransactions", page.getTotalElements());
        model.addAttribute("bankTransactionPageList", bankTransactionPageList);
        model.addAttribute("bankaccountList", bankAccountList);
        model.addAttribute("banktransaction", new BankTransaction());

        return "banktransactions";
    }


    @RequestMapping(value = "/banktransactions", method = RequestMethod.POST, params = "action=depositmoney")
    public String depositMoney(@AuthenticationPrincipal UserPrincipal user, @ModelAttribute BankAccount bankAccount, @ModelAttribute BankTransaction bankTransaction, RedirectAttributes Redir) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountIban = bankAccountService.getBankAccountByIban(bankAccount.getIban());

        if (userConnected.getBankAccountList().contains(bankAccountIban) && bankTransaction.getAmount() <= userConnected.getBalance()) {
            bankTransactionService.deposit(userConnected, bankAccount.getIban(), bankTransaction.getAmount());
            Redir.addFlashAttribute("banktransactionsuccess", "OK");
            return "redirect:/banktransactions";
        } else {
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/banktransactions";
        }
    }

    @RequestMapping(value = "/banktransactions", method = RequestMethod.POST, params = "action=withdrawmoney")
    public String withdrawMoney(@AuthenticationPrincipal UserPrincipal user, @ModelAttribute BankAccount bankAccount, @ModelAttribute BankTransaction bankTransaction, RedirectAttributes Redir) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        BankAccount bankAccountIban = bankAccountService.getBankAccountByIban(bankAccount.getIban());

        if (userConnected.getBankAccountList().contains(bankAccountIban)) {
            bankTransactionService.withdraw(userConnected, bankAccount.getIban(), bankTransaction.getAmount());
            Redir.addFlashAttribute("banktransactionsuccess", "OK");
            return "redirect:/banktransactions";
        } else {
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/banktransactions";
        }
    }

}
