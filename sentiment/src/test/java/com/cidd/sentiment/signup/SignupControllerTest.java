package com.cidd.sentiment.signup;

import org.junit.Test;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cidd.sentiment.config.WebAppConfigurationAware;

public class SignupControllerTest extends WebAppConfigurationAware {
    @Test
    public void displaysSignup() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(model().attributeExists("signup"))
                .andExpect(view().name("signup/signup"))
                .andExpect(content().string(
                        allOf(
                                containsString("<title>Signup</title>"),
                                containsString("<legend>Please Sign Up</legend>")
                        ))
                );
    }
}