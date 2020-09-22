package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.order.CustomerOrder;
import com.rstepanchuk.miniplantpotstock.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public class CustomerOrderService {

  private CustomerOrderRepository repository;

  public CustomerOrderService(CustomerOrderRepository repository) {
    this.repository = repository;
  }

  public CustomerOrder saveCustomerOrder(CustomerOrder order) {
    CustomerOrder save = repository.save(order);
    return save;
  }
  public List<CustomerOrder> getCustomerOrders() {
    return repository.findAll();
  }
}
