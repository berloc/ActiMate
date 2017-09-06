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

    @Test
    public void registrationTestNotValidEmail() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"email\":\"useruser.com\", \"password\":\"12345678\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void registrationTestNotValidPassword() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"email\":\"user@user.com\", \"password\":\"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void registrationTestEmailInTheDatabase() throws Exception {
        mockMvc.perform(post(host+port+regUrl)
                .content("{\"email\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(post(host+port+regUrl)
                .content("{\"email\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void UnRegisteredUserNotFound() throws Exception {
        assertEquals(null, userService.getUserByEmail("user@user.com"));
    }

}