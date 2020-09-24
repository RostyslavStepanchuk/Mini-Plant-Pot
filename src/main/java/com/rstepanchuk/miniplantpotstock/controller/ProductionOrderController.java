package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.dto.production.order.ProductionOrderDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionOrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/production/orders")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ProductionOrderController {

  private final ProductionOrderMapper productionOrderMapper;

  @GetMapping
  public ResponseEntity<List<ProductionOrderDtoOut>> getProductionOrders() {
    return ResponseEntity.ok(productionOrderMapper.getProductionOrders());
  }
}
