package com.codecool.fittinder.controller;

import com.codecool.fittinder.dto.UserDto;
import com.codecool.fittinder.exception.EmailIsInTheDatabaseException;
import com.codecool.fittinder.model.Profile;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.enums.Role;
import com.codecool.fittinder.service.DtoConverter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController extends AbstractController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private String status = "status";

    @Autowired
    private DtoConverter converter;

    @PostMapping(value = "/registration")
    @ResponseBody
    public String registration(@RequestBody @Valid UserDto userDto, HttpServletRequest request) throws JSONException, MessagingException, EmailIsInTheDatabaseException {
        logger.debug("{} route called with method: {}", request.getRequestURI(), request.getMethod());
        JSONObject response = new JSONObject().put(status,"fail");
        User user = converter.convertToUser(userDto);
        if (userService.getUserByEmail(user.getEmail()) == null) {
            userService.createUser(user, Role.USER);
            Profile profile = new Profile(user);
            profileRepository.save(profile);
            logger.info("Save profile into the {} table", profile.getClass().getSimpleName());
            emailService.sendMessage(user.getEmail(), "registration", template.getText());
            response.put(status,"success");
        } else {
            logger.debug("{} is already in the db", user.getEmail());
            response.put(status,"email exist");
            throw new EmailIsInTheDatabaseException();
        }

        return response.toString();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private String validationException(MethodArgumentNotValidException e) throws JSONException {
        logger.info("{} occurred not valid email or password format while tried to save the User into the db, {} ", e.getClass().getSimpleName(), e.getMessage());
        return new JSONObject().put(status, "invalid email or password format").toString();
    }

    @ExceptionHandler(MessagingException.class)
    private String messagingException(MessagingException e) throws JSONException {
        logger.info("{} occurred while trying to send the welcome email; {} ", e.getClass().getSimpleName(), e.getMessage());
        return new JSONObject().put(status, "messaging exception").toString();
    }

    @ExceptionHandler(EmailIsInTheDatabaseException.class)
    private String emailIsInTheDatabase(EmailIsInTheDatabaseException e) throws JSONException {
        logger.info("{} occurred while trying to save the user into the db; {} ", e.getClass().getSimpleName(), e.getMessage());
        return new JSONObject().put(status, "email exist").toString();
    }
}
