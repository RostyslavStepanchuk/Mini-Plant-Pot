package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.production.ProductionSupply;
import com.rstepanchuk.miniplantpotstock.repository.ProductionSupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProductionSupplyService {

  private ProductionSupplyRepository repository;

  @Autowired
  public ProductionSupplyService(ProductionSupplyRepository repository) {
    this.repository = repository;
  }

  public List<ProductionSupply> getProductionSupplies() {
    return repository.findAll();
  }
}
