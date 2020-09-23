package com.rstepanchuk.miniplantpotstock.integration;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Calendar;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
    Pot superPuperPot = Pot.builder().id(345L).name("SuperPuperPot").availableQuantity(10).build();
    Pot wonderfulPot = Pot.builder().id(123L).name("wonderfulPot").availableQuantity(7).build();

    ArrayList<Pot> pots1 = new ArrayList<Pot>() {{
      add(superPuperPot);
    }};

    ArrayList<Pot> pots2 = new ArrayList<Pot>() {{
      add(wonderfulPot);
    }};

    CustomerOrder order1 = CustomerOrder.builder()
        .id(1L)
        .pots(pots1)
        .deadlineToSend(Calendar.getInstance().getTime())
        .etsyOrderId("fwdewf3244")
        .isClosed(false)
        .build();

    CustomerOrder order2 = CustomerOrder.builder()
        .id(2L)
        .pots(pots2)
        .deadlineToSend(Calendar.getInstance().getTime())
        .etsyOrderId("encosy0382")
        .isClosed(false)
        .build();

    ArrayList<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>() {{
      add(order1);
      add(order2);
    }};

    when(repository.findAll()).thenReturn(customerOrders);

    mockMvc.perform(get("/api/v1/sale/orders"))
        .andExpect(status().isOk());

  }
}