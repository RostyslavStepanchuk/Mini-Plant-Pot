package com.rstepanchuk.miniplantpotstock.integration;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.production.ProductionSupply;
import com.rstepanchuk.miniplantpotstock.repository.ProductionSupplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductionSupplyControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductionSupplyRepository repository;

  @Test
  void getProductionSuppliesShouldReturnStatusOk() throws Exception {
    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").availableQuantity(10).build();

    ProductionSupply productionSupply = ProductionSupply.builder().id(480L)
        .pot(superPuperPot)
        .supplyDate(Calendar.getInstance().getTime())
        .suppliedQuantity(4)
        .wasOrdered(true)
        .build();

    ArrayList<ProductionSupply> productionSupplies = new ArrayList<ProductionSupply>() {{
      add(productionSupply);
    }};

    when(repository.findAll()).thenReturn(productionSupplies);

    mockMvc.perform(get("/api/v1/production-supplies"))
        .andExpect(status().isOk());
  }
}