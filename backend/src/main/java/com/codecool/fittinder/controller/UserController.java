package com.codecool.fittinder.controller;

import com.codecool.fittinder.exception.EmailIsInTheDatabaseException;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.dto.UserDto;
import com.codecool.fittinder.model.request.LoginUser;
import com.codecool.fittinder.model.response.StatusResponse;
import com.codecool.fittinder.model.response.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/registration")
    public StatusResponse registration(@RequestBody @Valid UserDto userDto, HttpServletRequest request)
            throws EmailIsInTheDatabaseException {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        User user = converter.convertToUser(userDto);
        if (userService.findByUsername(user.getUsername()) == null) {
            userService.createUser(user);
            emailController.sendConfirmationEmail(user);
            return new StatusResponse("success");
        } else {
            logger.debug("{} is already in the db", user.getUsername());
            throw new EmailIsInTheDatabaseException();
        }
    }


    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginUser request) {
        User user = userService.findByUsername(request.getUsername());
        String token = userService.login(user, request.getPassword());
        return new TokenResponse(token);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private StatusResponse validationException(MethodArgumentNotValidException e) {
        logger.info("{} occurred not valid username or password format while tried to save the User into the db, {} ", e.getClass().getSimpleName(), e.getMessage());
        return new StatusResponse("invalid username or password format");
    }

    @ExceptionHandler(EmailIsInTheDatabaseException.class)
    private StatusResponse emailIsInTheDatabase(EmailIsInTheDatabaseException e) {
        logger.info("{} occurred while trying to save the user into the db; {} ", e.getClass().getSimpleName(), e.getMessage());
        return new StatusResponse("username exist");
    }
}
