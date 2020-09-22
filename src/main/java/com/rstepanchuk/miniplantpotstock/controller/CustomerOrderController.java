package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.dto.order.CustomerOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.CustomerOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/customer-orders")
public class CustomerOrderController {

  private CustomerOrderMapper customerOrderMapper;

  public CustomerOrderController(CustomerOrderMapper customerOrderMapper) {
    this.customerOrderMapper = customerOrderMapper;
  }


  @GetMapping
  public ResponseEntity<List<CustomerOrderDtoOut>> getCustomerOrders() {
    return ResponseEntity.ok(customerOrderMapper.getCustomerOrders());
  }
}
