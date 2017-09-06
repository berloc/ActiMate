package com.codecool.fittinder.controller;

import com.codecool.fittinder.model.Interest;
import com.codecool.fittinder.repository.InterestRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private InterestRepository interestRepository;

    @GetMapping(value = "/status")
    public String status(HttpServletRequest request) throws JSONException {
        logger.debug("{} is called with method: {}", request.getRequestURI(), request.getMethod());
        JSONObject status = new JSONObject().put("status","server is running");
        return status.toString();
    }

    @GetMapping(value = "/interest")
    public List<Interest> getInterest(HttpServletRequest request) {
        logger.debug("{} is called with method: {}", request.getRequestURI(), request.getMethod());
        return interestRepository.findAll();
    }
}
