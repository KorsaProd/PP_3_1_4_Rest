package com.example.pp_3_1_4_rest.controller;


import com.example.pp_3_1_4_rest.dto.UserDTO;
import com.example.pp_3_1_4_rest.model.Role;
import com.example.pp_3_1_4_rest.model.User;
import com.example.pp_3_1_4_rest.repository.UserRepository;
import com.example.pp_3_1_4_rest.service.UserService;
import com.example.pp_3_1_4_rest.service.UserServiceImpl;
import com.example.pp_3_1_4_rest.utils.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRESTController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Autowired
    public AdminRESTController(UserService userService, UserRepository userRepository, UserMapper userMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }



    @GetMapping("/getRoles")
    private List<Role> allRoles() {
        return userService.listRoles();
    }


    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable(name = "id") Integer id) {
        User user = userService.getUserById(id);
        return userMapper.toUserDto(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.getAllUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping()
    public ResponseEntity<User> newUser(@RequestBody User user) {
        userService.saveUser(user, "ROLE");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.saveUser(user, user.getRoles().toString());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
