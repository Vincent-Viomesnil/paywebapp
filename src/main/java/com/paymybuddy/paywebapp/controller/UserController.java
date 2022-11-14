package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @GetMapping("/registry")
    public String addUserRegistry(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
        //renvoit à la page HTML du même nom
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        if (userService.addUser(user) != null) {
            return "register_success";
        } else {
            String name = httpServletResponse.encodeRedirectURL("addUser");
            return name;
        }
    }


    @GetMapping("/contactsList")
    public String getContactsList(Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        List<User> contactsList = userConnected.getContactUserList();
        model.addAttribute("contactsList", contactsList);
        model.addAttribute("user", user);
        return "addConnection";
    }


    @PostMapping("/addcontact")
    public String addContact(Model model, @AuthenticationPrincipal UserPrincipal user, @RequestParam(name = "emailtoadd") String email) throws IOException {
        User contactToAdd = userService.getUserByEmail(email);
        List<User> contactList = new ArrayList<>();
        if (contactToAdd != null) {
            model.addAttribute("user", user);
            log.info("contact to ADD already exist :" + contactToAdd);

            System.out.println("This is the Stream :" + contactList);
            return "addConnection";
        }
        log.error("contact to add is NULL");
//        String name = httpServletResponse.encodeURL("addConnection");
//        return name;
        return "redirect:/contactsList";
    }

    @DeleteMapping("/deletecontact")
    public String deleteContact(Model model, @AuthenticationPrincipal UserPrincipal user, @Valid @RequestParam(name = "emailtodelete") String email) throws IOException {
        User userConnected = userService.getUserByEmail(user.getUsername());
        User contactToDelete = userService.getUserByEmail(email);

        if (userConnected.getContactUserList().contains(contactToDelete)) {
            model.addAttribute("user", user);
            model.addAttribute("contactToDelete", contactToDelete);
            log.info("contact to delete :" + contactToDelete);
            userService.deleteContact(userConnected, contactToDelete);
            return "redirect:/contactsList";
        }
        log.error("contact to delete is null or not in the contact list");
//        String name = httpServletResponse.encodeURL("addConnection");
//        return name;
        return "redirect:/contactsList";
    }

    @PostMapping("/contactsList")
    public String addContactToList(@AuthenticationPrincipal UserPrincipal user, @RequestParam(name = "emailtoadd") String email) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        User contactToAdd = userService.getUserByEmail(email);
        if (contactToAdd == null || userConnected.getContactUserList().contains(contactToAdd)) {
            log.info("Error: wrong email or contact already in your contactList " + contactToAdd);
            return "redirect:/contactsList";
        } else {
            userService.addContact(userConnected, contactToAdd);
            log.info("Successfull Contact Added in your list");
            return "redirect:/contactsList";
        }
    }


//    @GetMapping("/user_home")
//    public String viewUserHome(@AuthenticationPrincipal UserPrincipal user, Model model) {
//        List<User> listUsers = userService.getAllUsers();
//        User userConnected = userService.getUserByEmail(user.getUsername());
//        model.addAttribute("listUsers", listUsers);
//        model.addAttribute("user", user);
//        model.addAttribute("userconnected", userConnected);
//        return "user_home";
//        //renvoit à la page HTML du même nom
//    }


//    @RequestMapping(value = "/username", method = RequestMethod.GET)
//    @ResponseBody
//    public String currentUserName(Principal principal) {
//        return principal.getName();
//    }

//    @GetMapping("/")
//    public String viewHomePage(){
//        return ("<h1>Welcome</h1>");
//    }
//    @GetMapping("/user")
//    public String getUser()
//    {
//        return ("<h1>Welcome User</h1>");
//    }
//
//    @GetMapping("/admin")
//    public String getAdmin()
//    {
//        return ("<h1>Welcome Admin</h1>");
//    }

}
