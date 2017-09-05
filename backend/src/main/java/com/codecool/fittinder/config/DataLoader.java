package com.codecool.fittinder.config;


import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.enums.Role;
import com.codecool.fittinder.repository.InterestRepository;
import com.codecool.fittinder.repository.ProfileRepository;
import com.codecool.fittinder.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataLoader {

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProfileRepository profileRepository;

    private List<String> interestList = new ArrayList<String>() {{
        add("climbing");
        add("tennis");
        add("water sports");
        add("winter sports");
        add("outdoor sports");
        add("cultural");
        add("outdoor activity");
        add("ball games");
        add("board games");
        add("eSports");
        add("indoor sports");
        add("other");
    }};

    @PostConstruct
    public void createInterest() {
        if (interestRepository.findAll().isEmpty()) {
            interestList.forEach(name -> {
                Interest interest = new Interest(name);
                interestRepository.save(interest);
            });
        }
    }

    @PostConstruct
    public void loadAdmin() {
        if (userService.getUserByEmail("admin@admin.com") == null) {
            User admin = new User("admin@admin.com", "admin");
            userService.createUser(admin, Role.ADMIN);
            Profile profile = new Profile(admin);
            profileRepository.save(profile);
        }
    }
}
