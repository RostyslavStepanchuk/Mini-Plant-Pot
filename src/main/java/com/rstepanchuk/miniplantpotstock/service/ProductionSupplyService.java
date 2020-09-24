package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.entity.production.ProductionSupply;
import com.rstepanchuk.miniplantpotstock.repository.ProductionSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ProductionSupplyService {

  private final ProductionSupplyRepository repository;

  public List<ProductionSupply> getProductionSupplies() {
    return repository.findAll();
  }
}
