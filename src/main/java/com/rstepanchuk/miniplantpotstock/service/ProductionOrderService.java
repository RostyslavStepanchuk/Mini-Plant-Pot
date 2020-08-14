package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.production.ProductionOrder;
import com.rstepanchuk.miniplantpotstock.repository.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionOrderService {

  private ProductionOrderRepository repository;

  @Autowired
  public ProductionOrderService(ProductionOrderRepository repository) {
    this.repository = repository;
  }


  public List<ProductionOrder> getProductionOrders() {
    return repository.findAll();
  }
}
