package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Event;
import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.model.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ApiController extends AbstractController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping(value = "/status")
    public String status(HttpServletRequest request) throws JSONException {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        JSONObject status = new JSONObject().put("status","server is running");
        return status.toString();
    }

    @GetMapping(value = "/interest")
    public List<Interest> getInterest(HttpServletRequest request) {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        return interestRepository.findAll();
    }

    @GetMapping(value = "/events")
    public List<Event> getEvents(HttpServletRequest request) {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        return eventRepository.findAll();
    }

    @GetMapping(value = "/me")
    @ResponseBody
    public User getMe(HttpServletRequest request, Principal principal) {
        logger.debug(debugLogMes, request.getRequestURI(), request.getMethod());
        return userService.findByUsername(getCurrentUser(principal).getUsername());
    }
}
