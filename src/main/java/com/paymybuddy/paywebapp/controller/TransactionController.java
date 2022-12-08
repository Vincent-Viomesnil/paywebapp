package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.Transaction;
import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.TransactionService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;


//    @GetMapping("/transactions")
//    public String getAllTransactions(Model model, @AuthenticationPrincipal UserPrincipal user) {
//        return findPaginated(1, model, user);
//    }

    //    @GetMapping("/transactions/page/{pageNumber}/")
    //  public String findPaginated(@PathVariable(name = "pageNumber") int currentPage,
    @GetMapping("/transactions")
    public String findPaginated(@RequestParam(value = "page", defaultValue = "1") int currentPage,
                                Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
//        Page<Transaction> page = transactionService.findPaginated(pageNo);
//        List<Transaction> transactionPageList = userConnected.getTransactionList();
        Pageable pageable = PageRequest.of(currentPage - 1, 5, Sort.by("intime").descending());

        Page<Transaction> listTransaction = transactionService.getPaginated(userConnected, pageable);
        //listTransaction = 7
        // listTransfer = 5
        List<Transaction> listTransfer = listTransaction.getContent();
        List<User> contactsList = userConnected.getContactUserList();


        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", listTransaction.getTotalPages());
        model.addAttribute("totalTransactions", listTransaction.getTotalElements());
        model.addAttribute("listTransfer", listTransfer);
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
