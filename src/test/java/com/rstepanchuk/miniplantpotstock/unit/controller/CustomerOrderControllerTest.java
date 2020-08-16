package com.rstepanchuk.miniplantpotstock.unit.controller;

import com.rstepanchuk.miniplantpotstock.controller.CustomerOrderController;
import com.rstepanchuk.miniplantpotstock.dto.order.CustomerOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.CustomerOrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerOrderControllerTest {

  @InjectMocks
  CustomerOrderController customerOrderController;

  @Mock
  CustomerOrderMapper customerOrderMapper;

  @Test
  public void getCustomerOrdersShouldReturnResponseWithListOfCustomerOrders(){
    List<CustomerOrderDtoOut> orders = new ArrayList<>();
    when(customerOrderMapper.getCustomerOrders()).thenReturn(orders);

    ResponseEntity<List<CustomerOrderDtoOut>> response = customerOrderController.getCustomerOrders();
    assertEquals(response.getStatusCodeValue(), 200);
    assertEquals(Objects.requireNonNull(response.getBody()).size(), 0);
  }
}
