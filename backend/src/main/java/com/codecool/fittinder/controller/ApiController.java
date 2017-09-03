package com.codecool.fittinder.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping(value = "/status")
    public String status(HttpServletRequest request) throws JSONException {
        logger.debug("{} is called with method: {}", request.getRequestURI(), request.getMethod());
        JSONObject status = new JSONObject().put("status","server is running");
        return status.toString();
    }

}
