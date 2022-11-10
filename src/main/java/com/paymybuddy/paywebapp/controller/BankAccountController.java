package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.BankAccount;
import com.paymybuddy.paywebapp.service.BankAccountService;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private UserService userService;

//    @PostMapping("/bankaccount")
//    public String addBankAccount(@ModelAttribute BankAccount bankAccount,
//                                 @RequestParam(value = "nametoadd") String bankName,
//                                 @RequestParam(value = "ibantoadd") String iban,
//                                 @AuthenticationPrincipal UserPrincipal user) {
//        User userConnected = userService.getUserByEmail(user.getUsername());
//        bankAccountService.addBankAccount(bankAccount);
//
//        return "redirect:/bankaccount";
//    }

    @GetMapping("/bankaccount")
    public String addBankAccount(Model model) {
        model.addAttribute("bankaccount", new BankAccount());
        return "bankaccount";
        //renvoit à la page HTML du même nom
    }

    @PostMapping("/addbankaccount")
    public String addBankAccount(BankAccount bankAccount) {
        if (bankAccountService.addBankAccount(bankAccount) != null) {
            log.info("bankaccount added" + bankAccount);
            return "redirect:/bankaccount";
        }
        log.error("error when added bankaccount");
        return "redirect:/bankaccount";
    }


//    @GetMapping("/bankaccount")
//    public String getBankAcount(Model model, @AuthenticationPrincipal UserPrincipal user) {
//        User userConnected = userService.getUserByEmail(user.getUsername());
//        List<BankAccount> bankAccountList = userConnected.getBankAccount();
//        model.addAttribute("bankaccount", new BankAccount());
//        model.addAttribute("bankaccountlist", bankAccountList);
//        model.addAttribute("user", user);
//        model.addAttribute("userconnected", userConnected);
//        return "redirect:/bankaccount";
//    }


    @GetMapping("/bankacccounts")
    public Iterable<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBanksAccounts();
    }

//    @GetMapping(value = "/bankid")
//    public Optional<BankAccount> getBankAccountById(@RequestParam(value = "id") Integer id) {
//        Optional<BankAccount> bankAccountById = bankAccountService.getBankAccountById(id);
//        if (bankAccountById.isPresent()) {
//            log.info("Request get bankaccount by id SUCCESS");
//        } else {
//            log.error("Request get bankaccount by id FAILED");
//        }
//        return bankAccountById;
//    }
//
//    @GetMapping(value = "/bankiban")
//    public Optional<BankAccount> getBankAccountByIban(@RequestParam(value = "iban") String iban) {
//        Optional<BankAccount> bankAccountByIban = bankAccountService.getBankAccountByIban(iban);
//        if (bankAccountByIban.isPresent()) {
//            log.info("Request get bankaccount by iban SUCCESS");
//        } else {
//            log.error("Request get bankaccount by iban FAILED");
//
//        }
//        return bankAccountByIban;
//    }
//
//    @GetMapping(value = "/bankuserid")
//    public Optional<BankAccount> getBankAccountByUserId(@RequestParam(value = "userid") Integer userId) {
//        Optional<BankAccount> bankAccountByUserId = bankAccountService.getBankAccountByUserId(userId);
//        if (bankAccountByUserId.isPresent()) {
//            log.info("Request get bankaccount by userId SUCCESS");
//
//        } else {
//            log.error("Request get bankaccount by userId FAILED");
//        }
//        return bankAccountByUserId;
//    }
//
//    @PutMapping(value = "/bankuserid/{userId}")
//    public BankAccount updateBankAccount(@PathVariable Integer userId, @RequestParam String iban) {
//        return bankAccountService.updateBankAccount(userId, iban);
//        if (updateBankAccount != null) {
//            log.info("Update bankaccount request SUCCESS");
//        } else {
//            log.error("Update bankaccount request FAILED, the userID doesn't exist");
//        } return updateBankAccount;
//    }
}
