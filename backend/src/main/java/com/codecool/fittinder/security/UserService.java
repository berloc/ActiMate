package com.codecool.fittinder.security;

import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.enums.Role;

public interface UserService {

    void createUser(User user, Role role);

    User findByEmail(String email);
}
