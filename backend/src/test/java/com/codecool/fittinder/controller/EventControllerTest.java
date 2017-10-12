package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import com.codecool.fittinder.repository.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EventControllerTest extends TestConfig {


    @Autowired
    private EventRepository eventRepository;


    @Before
    public void setup() {
        initMockMvc();
    }

    @After
    public void tearDown() {
        eventRepository.deleteAll();
        profileRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void createEventSuccessTest() throws Exception {
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

        mockMvc.perform(post(host + port + createEventUrl)
                .content("{\"name\":\"eventName\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", token));

        assertEquals(1, eventRepository.findAll().size());
        assertEquals("eventName", eventRepository.findAll().get(0).getName());
    }
}