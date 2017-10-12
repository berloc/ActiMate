package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.dto.UserDto;
import com.codecool.fittinder.exception.EmailIsInTheDatabaseException;
import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController extends AbstractController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String STATUS = "status";

    @PostMapping(value = "/registration")
    @ResponseBody
    public String registration(@RequestBody @Valid UserDto userDto, HttpServletRequest request) throws JSONException, MessagingException, EmailIsInTheDatabaseException {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        JSONObject response = new JSONObject().put(STATUS, "fail");
        User user = converter.convertToUser(userDto);
        if (userService.findByUsername(user.getUsername()) == null) {
            userService.createUser(user);
            Profile profile = new Profile(user);
            profileRepository.save(profile);
            logger.info("Save profile into the {} table", profile.getClass().getSimpleName());
//            emailService.sendMessage(user.getUsername(), "registration", template.getText());
            response.put(STATUS, "success");
        } else {
            logger.debug("{} is already in the db", user.getUsername());
            response.put(STATUS, "username exist");
            throw new EmailIsInTheDatabaseException();
        }

        return response.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private String validationException(MethodArgumentNotValidException e) throws JSONException {
        logger.info("{} occurred not valid username or password format while tried to save the User into the db, {} ", e.getClass().getSimpleName(), e.getMessage());
        return new JSONObject().put(STATUS, "invalid username or password format").toString();
    }

    @ExceptionHandler(MessagingException.class)
    private String messagingException(MessagingException e) throws JSONException {
        logger.info("{} occurred while trying to send the welcome username; {} ", e.getClass().getSimpleName(), e.getMessage());
        return new JSONObject().put(STATUS, "messaging exception").toString();
    }

    @ExceptionHandler(EmailIsInTheDatabaseException.class)
    private String emailIsInTheDatabase(EmailIsInTheDatabaseException e) throws JSONException {
        logger.info("{} occurred while trying to save the user into the db; {} ", e.getClass().getSimpleName(), e.getMessage());
        return new JSONObject().put(STATUS, "username exist").toString();
    }
}
