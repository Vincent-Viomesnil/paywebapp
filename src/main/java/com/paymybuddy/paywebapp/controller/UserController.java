package com.paymybuddy.paywebapp.controller;

import com.paymybuddy.paywebapp.model.User;
import com.paymybuddy.paywebapp.model.UserPrincipal;
import com.paymybuddy.paywebapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.script.SimpleScriptContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

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
            return name; //renvoit à la page HTML du même nom
        }
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        Iterable<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);

        return "usersList";
    }

    @GetMapping("/user_home" )
    public String viewUserHome(@AuthenticationPrincipal UserPrincipal user, Model model){
        model.addAttribute("user", user);
        Iterable<User> listUsersPrincipal = userService.getAllUsersPrincipal();
        model.addAttribute("listUsersPrincipal", listUsersPrincipal);
        return "user_home";
        //renvoit à la page HTML du même nom
    }

    @PostMapping("/addContact")
    public String addContact() throws IOException {
        if (userService.getAllUsersPrincipal() != null) {
            return "addContact";
        } else {
            String name1 = httpServletResponse.encodeRedirectURL("addContact");
            return name1; //renvoit à la page HTML du même nom
        }
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
