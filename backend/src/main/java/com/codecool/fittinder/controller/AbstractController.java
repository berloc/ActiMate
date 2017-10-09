package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.repository.EventRepository;
import com.codecool.fittinder.repository.InterestRepository;
import com.codecool.fittinder.repository.ProfileRepository;
import com.codecool.fittinder.service.DtoConverter;
import com.codecool.fittinder.service.email.EmailServiceImpl;
import com.codecool.fittinder.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import java.security.Principal;

abstract class AbstractController {

    final String debugLogMes = "{} is called with method: {}";

    @Autowired
    UserService userService;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    SimpleMailMessage template;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    DtoConverter converter;

    @Autowired
    InterestRepository interestRepository;

//    TODO create loggerMessage

    Profile getCurrentProfile(Principal principal) {
        return profileRepository.findByUser(getCurrentUser(principal));
    }

    User getCurrentUser(Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}
