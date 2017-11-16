package com.codecool.fittinder.service.user;

import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User findByUsername(String username);

    void save(User user);

    User findByProfile(Profile profile);

    List<User> findByConfirmed(Boolean b);

    void delete(User user);

    String login(User user, String raw);
}
