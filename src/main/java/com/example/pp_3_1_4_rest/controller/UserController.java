package com.example.pp_3_1_4_rest.controller;

import com.example.pp_3_1_4_rest.model.User;
import com.example.pp_3_1_4_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
        public ResponseEntity<User> getUser(Principal principal) {
            return new ResponseEntity<>(userService.findByUsername(principal.getName()), HttpStatus.OK);
        }
}
