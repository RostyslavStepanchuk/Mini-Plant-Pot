package com.rstepanchuk.miniplantpotstock.unit.service;

import com.rstepanchuk.miniplantpotstock.entity.catalog.Pot;
import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.entity.catalog.Sku;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import com.rstepanchuk.miniplantpotstock.service.CustomerOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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


    Sku sku1 = Sku.builder().id("SUPER_PUPER_POT").pots(new ArrayList<>(){{add(superPuperPot);}}).build();
    Sku sku2 = Sku.builder().id("WONDERFUL_POT").pots(new ArrayList<>(){{add(wonderfulPot);}}).build();

    CustomerOrder order1 = CustomerOrder.builder()
        .id(1L)
        .sku(sku1)
        .deadlineToSend(Calendar.getInstance().getTime())
        .etsyOrderId("fwdewf3244")
        .isClosed(false)
        .build();

    CustomerOrder order2 = CustomerOrder.builder()
        .id(2L)
        .sku(sku2)
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