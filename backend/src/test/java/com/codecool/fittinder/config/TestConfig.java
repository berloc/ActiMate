package com.codecool.fittinder.config;

import com.codecool.fittinder.service.security.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class TestConfig  {

    @Resource
    FilterChainProxy springSecurityFilterChain;

    @Resource
    WebApplicationContext webApplicationContext;

    @Autowired
    protected UserService userService;

    protected MockMvc mockMvc;
    protected String host = "http://localhost";
    protected String port = ":8080";
    protected String regUrl = "/registration";
    protected String loginUrl = "/login";
    protected String createEventUrl = "/u/event/create";
    protected String getEventsUrl = "/u/events";


    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .addFilters(springSecurityFilterChain)
                .build();
    }
}
