package com.example.pp_3_1_4_rest.controller;

import com.example.pp_3_1_4_rest.model.Role;
import com.example.pp_3_1_4_rest.model.User;
import com.example.pp_3_1_4_rest.service.UserService;
import com.example.pp_3_1_4_rest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

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
//
//
//
//    @PostMapping("/newUser")
//    public String createUser(User user, String role) {
//        userService.saveUser(user, role);
//        return "redirect:/admin";
//    }
//
//
//    @PostMapping("/{id}")
//    public String update(@ModelAttribute("newUser") User newUser, String role,
//                         @PathVariable Integer id) {
//        userService.edit(newUser, id, role);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("deleteUser/{id}")
//    public String deleteUser(@PathVariable Integer id) {
//        userService.removeUserById(id);
//        return "redirect:/admin";
//    }
//
//
//    @PostMapping ("/deleteUser/{id}")
//    public String delete(@PathVariable("id") Integer id) {
//        userService.removeUserById(id);
//        return "redirect:/admin";
//    }
//
}
