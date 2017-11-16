package com.codecool.fittinder.config;

import com.codecool.fittinder.model.User;
import com.codecool.fittinder.repository.ProfileRepository;
import com.codecool.fittinder.repository.UserRepository;
import com.codecool.fittinder.service.user.UserService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public abstract class TestConfig  {

    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Resource
    private WebApplicationContext webApplicationContext;

    @Autowired
    protected UserService userService;

    @Autowired
    protected ProfileRepository profileRepository;

    @Autowired
    protected UserRepository userRepository;

    protected User mockUser = new User("user@user.com", "123456789");


    protected MockMvc mockMvc;
    protected String host = "http://localhost";
    protected String port = ":8080";
    protected String prefix = "/api";
    protected String regUrl = "/registration";
    protected String loginUrl = "/login";
    protected String createEventUrl = "/u/event/create";
    protected String getEventsUrl = "/u/events";
    private String confirmUrl = "/confirm";
    private String username = "user@user.com";


    @Before
    public void initMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .addFilters(springSecurityFilterChain)
                .build();
    }


    protected void confirmGet() throws Exception {
        mockMvc.perform(get(host+port+prefix+confirmUrl)
                .param("code", userService.findByUsername(username).getConfirmation().getToken())
                .param("name", username));
    }


    protected void confirm() throws Exception {
        userService.findByUsername(username).setConfirmed(true);
    }

    protected void registration() throws Exception {
        mockMvc.perform(post(host  + port + prefix + regUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    protected String getJWTToken() throws Exception {
        return mockMvc.perform(post(host + port + prefix + loginUrl)
                .content("{\"username\":\"user@user.com\", \"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
    }
}
