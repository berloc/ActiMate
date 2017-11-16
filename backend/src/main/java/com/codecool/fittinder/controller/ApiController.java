package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.model.User;
import com.codecool.fittinder.model.response.StatusResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
public class ApiController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping(value = "/status")
    public StatusResponse status(HttpServletRequest request) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        return new StatusResponse("server is running");
    }

    @GetMapping(value = "/interest")
    public List<Interest> getInterest(HttpServletRequest request) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        return interestRepository.findAll();
    }

    @GetMapping(value = "/check/username")
    public Boolean checkUsername(@RequestParam String username) {
        return userService.findByUsername(username) == null;
    }


    @GetMapping(value = "/u/me")
    public User getMe(HttpServletRequest request, Principal principal) {
        logger.debug(DEBUG_LOG_MES, request.getRequestURI(), request.getMethod());
        return userService.findByUsername(getCurrentUser(principal).getUsername());
    }
}
