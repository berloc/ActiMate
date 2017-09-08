package com.codecool.fittinder.controller;

import com.codecool.fittinder.config.TestConfig;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class ApiControllerTest extends TestConfig {


    @Before
    public void setup() {
        initMockMvc();
    }

    @Test
    public void statusReturnJSONTest() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(content().string(containsString("\"status\":\"server is running\"")));
    }
}