package com.codecool.fittinder.controller;

import com.codecool.fittinder.controller.microservice.EmailController;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.repository.*;
import com.codecool.fittinder.service.event.EventService;
import com.codecool.fittinder.service.user.UserServiceImpl;
import com.codecool.fittinder.service.util.DtoConverterService;
import com.codecool.fittinder.service.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping(value = "/api")
abstract class AbstractController {

    final static String DEBUG_LOG_MES = "{} is called with method: {}";


    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    UserPasswordResetRepository userPasswordResetRepository;

    @Autowired
    ConfirmationRepository confirmationRepository;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    EventService eventService;

    @Autowired
    EmailController emailController;

    @Autowired
    DtoConverterService converter;

    @Autowired
    TokenService tokenService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    User getCurrentUser(Principal principal) {
        return userService.findByUsername(principal.getName());
    }
}
