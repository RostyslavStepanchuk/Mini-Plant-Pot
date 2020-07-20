package com.rstepanchuk.miniplantpotstock.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RootControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  public void accessTokenWithValidCredentialsShouldReturnToken() throws Exception {

    RequestBuilder requestBuilder = get("/");

    mockMvc.perform(requestBuilder)
        .andExpect(status().isOk());
 }
}