package com.codecool.fittinder.service.security;

import com.codecool.fittinder.model.User;

public interface UserService {

    void createUser(User user);

    User findByUsername(String username);
}
