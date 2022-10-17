package com.example.pp_3_1_4_rest.service;

import com.example.pp_3_1_4_rest.model.Role;
import com.example.pp_3_1_4_rest.model.User;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    public Set<Role> getRoleSetFromString(User userNeedsForRoleId, String str) {
        String[] roles = str.split(",");
        Set<Role> roleSet = new HashSet<>();
        for (String s : roles) {
            roleSet.add(new Role(userNeedsForRoleId.getId(), s));
        }
        return roleSet;
    }

}
