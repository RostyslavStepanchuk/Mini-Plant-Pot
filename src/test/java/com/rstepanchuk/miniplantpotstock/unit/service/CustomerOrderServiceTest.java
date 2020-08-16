package com.rstepanchuk.miniplantpotstock.unit.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import com.rstepanchuk.miniplantpotstock.service.CustomerOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerOrderServiceTest {

  @InjectMocks
  private CustomerOrderService customerOrderService;

  @Mock
  private CustomerOrderRepository customerOrderRepository;

  @Test
  void getCustomerOrdersShouldReturnListOfCustomerOrders() {
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

    when(customerOrderRepository.findAll()).thenReturn(customerOrders);

    List<CustomerOrder> result = customerOrderService.getCustomerOrders();

    assertEquals(result.size(), customerOrders.size());
    assertIterableEquals(customerOrders, result);
  }
}