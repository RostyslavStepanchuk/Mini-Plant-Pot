package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {

  private CustomerOrderRepository repository;

  @Autowired
  public CustomerOrderService(CustomerOrderRepository repository) {
    this.repository = repository;
  }

  public List<CustomerOrder> getCustomerOrders() {
    return repository.findAll();
  }
}
