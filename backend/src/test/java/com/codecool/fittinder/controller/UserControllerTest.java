package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

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

//    @Test
//    @WithMockUser(username = "user@user.com", password = "123456789")
//    public void UserCanAccessWithTokenTest() throws Exception {
////        registration();
////        System.out.println(getJWTToken());
//////        confirm();
//////
////////         todo create get request to confirm the user
////        System.out.println(userService.findByUsername("user@user.com").getConfirmed());
////        confirmGet();
////
////        userService.findByUsername("user@user.com").setConfirmed(true);
////        System.out.println(userService.findByUsername("user@user.com").getConfirmed());
////        System.out.println(getJWTToken());
//////
////
////
//        mockMvc.perform(get(host + port + prefix + getEventsUrl)
//                .header("Authorization", getJWTToken()))
//                .andExpect(status().is2xxSuccessful());
//
//    }
}