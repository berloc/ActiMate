package com.codecool.fittinder.security;

import com.codecool.fittinder.model.User;

public interface UserService {

    void createUser(User user);

    User getUserByEmail(String email);
}
