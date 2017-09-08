package com.codecool.fittinder.security;

import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.enums.Role;
import com.codecool.fittinder.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    UserRepository userRepository;

    @Override
    public void createUser(User user, Role role) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRole(role);
        user.setRegDate(Calendar.getInstance().getTime());
        userRepository.save(user);
        logger.info("User with {} email saved into the database", user.getEmail());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
