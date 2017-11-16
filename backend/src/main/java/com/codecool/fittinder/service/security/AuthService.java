package com.codecool.fittinder.service.security;

import com.codecool.fittinder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User authenticate(User user, String rawPw) {

        if (user == null) {
            return null;
        }
        if (!passwordEncoder.matches(rawPw, user.getPassword())) {
            return null;
        }
        return user;
    }
}
