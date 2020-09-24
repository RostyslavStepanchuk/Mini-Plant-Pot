package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.production.ProductionOrder;
import com.rstepanchuk.miniplantpotstock.repository.ProductionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ProductionOrderService {

  private final ProductionOrderRepository repository;

  public List<ProductionOrder> getProductionOrders() {
    return repository.findAll();
  }
}
