package com.codecool.fittinder.service.user;

import com.codecool.fittinder.model.Confirmation;
import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.repository.ConfirmationRepository;
import com.codecool.fittinder.repository.ProfileRepository;
import com.codecool.fittinder.repository.UserRepository;
import com.codecool.fittinder.service.security.AuthService;
import com.codecool.fittinder.service.security.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConfirmationRepository confirmationRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthService authService;

    public void createUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRegDate(new Date());
        createProfile(user);
        userRepository.save(user);
        createConfirmation(user);
        logger.info("User with {} username saved into the database", user.getUsername());
    }

    public String login(User user, String rawPassword) {
        String token = null;
        User authUser = authService.authenticate(user, rawPassword);
        if (authUser != null) {
            if (authUser.getConfirmed()) {
                token = tokenService.generateToken(authUser.getUsername());
            } else {
                token = "not confirmed";
            }
        }
        return token;
    }

    public User findByUsername(String email) {
        return userRepository.findByUsername(email);
    }

    public User findByProfile(Profile profile) {
        return userRepository.findByProfile(profile);
    }

    public List<User> findByConfirmed(Boolean bool) {
        return userRepository.findByConfirmed(bool);
    }

    private void createConfirmation(User user) {
        Confirmation confirmation = new Confirmation();
        confirmation.setCreated(new Date());
        confirmation.setToken(tokenService.generateString());
        user.setConfirmation(confirmation);
        confirmationRepository.save(confirmation);
    }

    private void createProfile(User user) {
        Profile profile = new Profile();
        user.setProfile(profile);
        profileRepository.save(profile);
        logger.info("Save profile into the {} table", profile.getClass().getSimpleName());
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
