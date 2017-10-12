package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends TestConfig {

    @Before
    public void setup() {
        initMockMvc();
    }

    @Test
    public void RegistrationFindRegisteredUsersTest() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"12345678\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        assertEquals("user@user.com",
                userService.findByUsername("user@user.com").getUsername());
    }

    @Test
    public void RegistrationTestNotValidEmail() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"useruser.com\", \"password\":\"12345678\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void RegistrationNotValidPasswordTest() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void RegistrationEmailInTheDatabaseTest() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void UnRegisteredUserNotFound() throws Exception {
        assertEquals(null, userService.findByUsername("user@user.com"));
    }

    @Test
    public void UserCannotAccessWithoutLoginTest() throws Exception {
        mockMvc.perform(get(host + port + getEventsUrl))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void UserCannotAccessWithoutTokenTest() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(post(host + port + loginUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getHeader("Authorization");

        mockMvc.perform(get(host + port + getEventsUrl))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void UserCanAccessWithTokenTest() throws Exception {
        mockMvc.perform(post(host + port + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        String token = mockMvc.perform(post(host + port + loginUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getHeader("Authorization");

        mockMvc.perform(get(host + port + getEventsUrl)
                .header("Authorization", token))
                .andExpect(status().is2xxSuccessful());

    }
}