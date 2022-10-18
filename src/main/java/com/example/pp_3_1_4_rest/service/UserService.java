package com.example.pp_3_1_4_rest.service;

import com.example.pp_3_1_4_rest.model.Role;
import com.example.pp_3_1_4_rest.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void saveUser(User user);
    void removeUserById(Integer id);
    User getUserById(Integer id);
    User findByUsername(String username);
    List<Role> listRoles();
}
