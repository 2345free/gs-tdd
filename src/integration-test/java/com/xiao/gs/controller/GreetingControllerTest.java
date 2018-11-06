package com.xiao.gs.controller;

import com.xiao.gs.AbstractIntegrationTest;
import com.xiao.gs.service.GreetingService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GreetingControllerTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // 使用@MockBean创建和注入模拟GreetingServic
    @MockBean
    private GreetingService greetingService;

    @Test
    public void greetingShouldReturnMsgFromService() throws Exception {
        when(greetingService.greet()).thenReturn("Hello Mock");
        mockMvc.perform(get("/greeting"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello Mock")));
    }

}
