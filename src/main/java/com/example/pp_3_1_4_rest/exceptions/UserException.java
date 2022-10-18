package com.example.pp_3_1_4_rest.exceptions;

import com.example.pp_3_1_4_rest.model.User;

import javax.persistence.EntityExistsException;

public class UserException extends EntityExistsException {

        private User user;

        public UserException(User user) {
            this.user = user;
        }

        @Override
        public String getMessage() {
            StringBuilder builder = new StringBuilder();
            builder.append("User with login ")
                    .append(user.getUsername())
                    .append(" already exists.");
            return super.getMessage();
        }
    }
