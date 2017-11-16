package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
        registration();

        assertEquals("user@user.com",
                userService.findByUsername("user@user.com").getUsername());
    }

    @Test
    public void RegistrationTestNotValidEmail() throws Exception {
        mockMvc.perform(post(host  + port + prefix + regUrl)
                .content("{\"username\":\"useruser.com\", \"password\":\"12345678\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void RegistrationNotValidPasswordTest() throws Exception {
        mockMvc.perform(post(host  + port + prefix + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"12345\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void RegistrationEmailInTheDatabaseTest() throws Exception {
        registration();

        registration();
    }

    @Test
    public void UnRegisteredUserNotFound() throws Exception {
        assertNull(userService.findByUsername("user@user.com"));
    }

    @Test
    public void UserCannotAccessWithoutLoginTest() throws Exception {
        mockMvc.perform(get(host + port + prefix + getEventsUrl))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void UserCannotAccessWithoutTokenTest() throws Exception {
        registration();

        mockMvc.perform(get(host + port + prefix + getEventsUrl))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void UserCannotAccessWithoutConfirmationTest() throws Exception {
        registration();
    }

    @Test
    @WithMockUser
    public void UserCanAccessWithTokenTest() throws Exception {
        mockMvc.perform(get(host + port + prefix + getEventsUrl)
                .header("Authorization", getJWTToken()))
                .andExpect(status().is2xxSuccessful());

    }
}