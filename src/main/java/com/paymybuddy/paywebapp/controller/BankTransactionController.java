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
//
//
//    @PostMapping("/transaction")
//    public String sendMoney(@AuthenticationPrincipal UserPrincipal user,
//                            @ModelAttribute Transaction transaction, Model model) {
//        //@ModelAttribute Transaction récupère la transaction du getmapping
//        User userConnected = userService.getUserByEmail(user.getUsername());
//        User userDebtor = userService.getUserByEmail(transaction.getUserDebtor().getEmail());
//
//        if (userConnected.getContactUserList().contains(userDebtor)) {
//            transactionService.sendMoney(userConnected, userDebtor, transaction.getDescription(), transaction.getAmount());
////        model.addAttribute("transaction", transaction);
////            model.addAttribute("emaildebtor", userDebtor);
//
//            //Liste déroulante avec les emails de la liste de contacts
//            return "redirect:/transaction";
//        } else {
//            log.error("The email is not correct or not in the contact list");
//            return "redirect:/transaction";
//        }
//    }


}
