package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CustomerOrderService {

  private final CustomerOrderRepository repository;

  public List<CustomerOrder> getCustomerOrders() {
    return repository.findAll();
  }
}
