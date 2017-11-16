package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import com.codecool.fittinder.repository.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


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

//        registration();
//
//
//        mockMvc.perform(get(host + port + prefix +  getEventsUrl)
//                .header("Authorization", getJWTToken()))
//                .andExpect(status().is2xxSuccessful());
//
//        mockMvc.perform(post(host + port + prefix + createEventUrl)
//                .content("{\"interest\":\"eventName\"}")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .header("Authorization", getJWTToken()));
//
//        assertEquals(1, eventRepository.findAll().size());
//        assertEquals("eventName", eventRepository.findAll().get(0).getName());
    }
}