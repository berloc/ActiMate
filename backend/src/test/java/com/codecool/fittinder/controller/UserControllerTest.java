package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class UserControllerTest extends TestConfig {

    @Before
    public void setup() {
        initMockMvc();
    }

    @Test
    public void registrationFindRegisteredUsersTest() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"12345678\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        assertEquals("user@user.com",
                userService.findByUsername("user@user.com").getUsername());
    }

    @Test
    public void registrationTestNotValidEmail() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"useruser.com\", \"password\":\"12345678\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void registrationNotValidPasswordTest() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void registrationEmailInTheDatabaseTest() throws Exception {
        mockMvc.perform(post(host+port+regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(post(host+port+regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void UnRegisteredUserNotFound() throws Exception {
        assertEquals(null, userService.findByUsername("user@user.com"));
    }

}