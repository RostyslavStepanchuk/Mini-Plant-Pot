package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.dto.order.CustomerOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.CustomerOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/sale/orders")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CustomerOrderController {

  private final CustomerOrderMapper customerOrderMapper;

  @GetMapping
  public ResponseEntity<List<CustomerOrderDtoOut>> getCustomerOrders() {
    return ResponseEntity.ok(customerOrderMapper.getCustomerOrders());
  }
}
