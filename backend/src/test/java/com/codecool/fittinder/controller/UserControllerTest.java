package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import com.codecool.fittinder.security.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerTest extends TestConfig {

    private String regUrl = "/registration";

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        initMockMvc();
    }

    @Test
    public void registrationTestFindRegisteredUsers() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"email\":\"user@user.com\", \"password\":\"12345678\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        assertEquals("user@user.com",
                userService.getUserByEmail("user@user.com").getEmail());
    }


}