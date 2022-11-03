package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @GetMapping("/registry" )
    public String addUserRegistry(Model model) {
        model.addAttribute("user",new User());
        return "addUser";
        //renvoit à la page HTML du même nom
    }
    @PostMapping("/process_register")
    public String processRegister(User user) throws IOException {
        if (userService.addUser(user) != null) {
            return "register_success";
        } else {
            String name = httpServletResponse.encodeRedirectURL("addUser");
            return name;
        }
    }

//    @GetMapping("/users")
//    public String listUsers(Model model) {
//        Iterable<User> listUsers = userService.getAllUsers();
//        model.addAttribute("listUsers", listUsers);
//
//        return "usersList";
//    }

    @GetMapping("/contactsList")
    public String getContactsList(Model model, @AuthenticationPrincipal UserPrincipal user) {
        String userEmail = user.getUsername();
        User userConnected = userService.getUserByEmail(userEmail);
        List<User> contactsList = userConnected.getContactUserList();
        model.addAttribute("contactsList", contactsList);
        model.addAttribute("user", user);
        return "addContact";
    }

    @PostMapping("/contactsList")
    public String addContactToList(Model model, String email,@AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        User contactToAdd = userService.getUserByEmail(email);
        if (contactToAdd == null || userConnected.getContactUserList().contains(contactToAdd)){
            log.info("Error: wrong email or contact already in your contactList");
            String name = httpServletResponse.encodeRedirectURL("contactsList");
            return name;
        } else {
        userService.addContact(userConnected, contactToAdd);
        log.info("Successfull Contact Added in your list");
        model.addAttribute("addContactSuccess", httpServletResponse.encodeRedirectURL("contactsList"));
        model.addAttribute("user", user);
        return "addContact";
        }
    }

    @GetMapping("/user_home" )
    public String viewUserHome(@AuthenticationPrincipal UserPrincipal user, Model model){
       Iterable<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("user", user);
        return "user_home";
        //renvoit à la page HTML du même nom
    }



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
