package com.rstepanchuk.miniplantpotstock.integration;

import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerOrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CustomerOrderRepository repository;


  @Test
  void getCustomerOrdersShouldReturnObjectWithSuccessResponseCode() throws Exception {
    when(repository.findAll()).thenReturn(new ArrayList<>());

    mockMvc.perform(get("/api/v1/customer-orders"))
        .andExpect(status().isOk());
  }
}