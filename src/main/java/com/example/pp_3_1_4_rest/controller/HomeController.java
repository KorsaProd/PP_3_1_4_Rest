package com.example.pp_3_1_4_rest.controller;

import com.example.pp_3_1_4_rest.model.User;
import com.example.pp_3_1_4_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String getAllUser(Model model, Principal principal) {
        User activeUser = userService.findByUsername(principal.getName());
        model.addAttribute("nav-user-table", userService.getAllUsers());
        model.addAttribute("userRoles", activeUser.getRoles());
        model.addAttribute("userActive", userService.findByUsername(principal.getName()));
        return "admin";
    }

    @GetMapping("/user")
    public String index(@AuthenticationPrincipal User userActive, Model model) {
        model.addAttribute("userActive", userActive);
        return "user";
    }

}
