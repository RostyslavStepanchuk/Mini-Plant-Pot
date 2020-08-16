package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.dto.production.order.ProductionOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/production-orders")
public class ProductionOrderController {

  private ProductionOrderMapper productionOrderMapper;

  @Autowired
  public ProductionOrderController(ProductionOrderMapper productionOrderMapper) {
    this.productionOrderMapper = productionOrderMapper;
  }

  @GetMapping
  public ResponseEntity<List<ProductionOrderDtoOut>> getProductionOrders() {
    return ResponseEntity.ok(productionOrderMapper.getProductionOrders());
  }
}
