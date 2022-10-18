package com.example.pp_3_1_4_rest.service;

import com.example.pp_3_1_4_rest.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String name);
    void saveRole(Role role);
}
