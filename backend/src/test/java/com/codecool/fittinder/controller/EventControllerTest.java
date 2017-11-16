package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import com.codecool.fittinder.repository.EventRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;


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
    @WithMockUser(username = "user@user.com", password = "123456789")
    public void createEventSuccessTest() throws Exception {

    }
}