package com.rstepanchuk.miniplantpotstock.integration;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.production.ProductionOrder;
import com.rstepanchuk.miniplantpotstock.repository.ProductionOrderRepository;
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
class ProductionOrderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductionOrderRepository repository;

  @Test
  void getProductionOrders() throws Exception {
    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").availableQuantity(10).build();
    ProductionOrder prodOrder = ProductionOrder.builder()
        .id(327L)
        .pot(superPuperPot)
        .submitDate(Calendar.getInstance().getTime())
        .initialDeadline(Calendar.getInstance().getTime())
        .deadlineChanges(1)
        .orderedQuantity(4)
        .fulfilledQuantity(4)
        .delivered(Calendar.getInstance().getTime())
        .build();

    ArrayList<ProductionOrder> productionOrders = new ArrayList<ProductionOrder>() {{
      add(prodOrder);
    }};

    when(repository.findAll()).thenReturn(productionOrders);

    mockMvc.perform(get("/api/v1/production/orders"))
        .andExpect(status().isOk());
  }
}