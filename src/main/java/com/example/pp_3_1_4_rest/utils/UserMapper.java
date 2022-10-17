package com.example.pp_3_1_4_rest.utils;

import com.example.pp_3_1_4_rest.dto.UserDTO;
import com.example.pp_3_1_4_rest.model.Role;
import com.example.pp_3_1_4_rest.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO toUserDto(User user) {

        Set<String> roleDTO = user.getRoles().stream().map(Role::toString).collect(Collectors.toSet());
        UserDTO toDTO = new UserDTO (
                user.getId(),
                user.getUsername(),
                user.getLastName(),
                user.getAge(),
                user.getPassword(),
                roleDTO
        );
        return toDTO;
    }

}
