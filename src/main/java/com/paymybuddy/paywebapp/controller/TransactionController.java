package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.TransactionService;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/transactions")
    public String getAllTransactions(Model model, @AuthenticationPrincipal UserPrincipal user) {
        return findPaginated(1, model, user);
    }

    @GetMapping("/transactions/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        Page<Transaction> page = transactionService.findPaginated(pageNo);
        List<Transaction> transactionPageList = page.getContent();
        List<User> contactsList = userConnected.getContactUserList();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalTransactions", page.getTotalElements());
        model.addAttribute("transactionPageList", transactionPageList);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("contactsList", contactsList);

        return "transactions";
    }


    @PostMapping("/transactions")
    public String sendMoney(@AuthenticationPrincipal UserPrincipal user, String userDebtorEmail,
                            float amount, String description, RedirectAttributes Redir) {
        //@ModelAttribute Transaction récupère la transaction du getmapping => userDebtorEmail
        User userConnected = userService.getUserByEmail(user.getUsername());


        if (amount > 0 && amount <= userConnected.getBalance()) {
            transactionService.sendMoney(userConnected, userDebtorEmail, description, amount);
            Redir.addFlashAttribute("transactionsuccess", "OK");
            return "redirect:/transactions";
        } else {
            log.error("Error amount");
            Redir.addFlashAttribute("erroramount", "KO");
            return "redirect:/transactions";
        }
    }


}
