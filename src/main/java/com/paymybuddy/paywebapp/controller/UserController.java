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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public String processRegister(User user, RedirectAttributes redir) {
        if (userService.addUser(user) != null) {
            return "login";
        } else {
            redir.addFlashAttribute("errorregister", "KO");
            log.error("email already exists : " + user.getEmail());
            return "redirect:/registry";
        }
    }


    @GetMapping("/contact")
    public String getContactsList(Model model, @AuthenticationPrincipal UserPrincipal user) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        List<User> contactsList = userConnected.getContactUserList();
        model.addAttribute("contactsList", contactsList);
        model.addAttribute("user", user);
        return "addConnection";
    }


    @DeleteMapping("/deletecontact")
    public String deleteContact(Model model, @AuthenticationPrincipal UserPrincipal user, String email,
                                RedirectAttributes Redir) throws IOException {
        User userConnected = userService.getUserByEmail(user.getUsername());
        User contactToDelete = userService.getUserByEmail(email);

        if (userConnected.getContactUserList().contains(contactToDelete)) {
            Redir.addFlashAttribute("deletesuccess", "OK");
            model.addAttribute("user", user);
            model.addAttribute("contactToDelete", contactToDelete);
            log.info("contact to delete :" + contactToDelete);
            userService.deleteContact(userConnected, contactToDelete);
            return "redirect:/contact";
        }
        Redir.addFlashAttribute("errordelete", "KO");
        log.error("contact to delete is null or not in the contact list");
//        String name = httpServletResponse.encodeURL("addConnection");
//        return name;
        return "redirect:/contact";
    }

    @PostMapping("/addcontact")
    public String addContactToList(@AuthenticationPrincipal UserPrincipal user, @RequestParam(name = "emailtoadd") String email,
                                   RedirectAttributes Redir) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        User contactToAdd = userService.getUserByEmail(email);
        if (contactToAdd == null || userConnected.getContactUserList().contains(contactToAdd)) {
            Redir.addFlashAttribute("errorconnection", "KO");
            log.error("Error: wrong email or contact already in your contactList " + contactToAdd);
            return "redirect:/contact";
        } else {
            Redir.addFlashAttribute("connectionsuccess", "OK");
            userService.addContact(userConnected, contactToAdd);
            log.info("Successfull Contact Added in your list");
            return "redirect:/contact";
        }
    }


    @GetMapping("/user_home")
    public String viewUserHome(@AuthenticationPrincipal UserPrincipal user, Model model) {
        User userConnected = userService.getUserByEmail(user.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("userconnected", userConnected);
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
