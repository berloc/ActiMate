package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.repository.ProfileRepository;
import com.codecool.fittinder.security.UserService;
import com.codecool.fittinder.service.email.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import java.security.Principal;

abstract class AbstractController {


    @Autowired
    UserService userService;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    SimpleMailMessage template;


    Profile getCurrentProfile(Principal principal) {
        return profileRepository.findByUser(getCurrentUser(principal));
    }

    User getCurrentUser(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }
}
