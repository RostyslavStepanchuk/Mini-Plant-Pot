package com.rstepanchuk.miniplantpotstock.controller;


import com.rstepanchuk.miniplantpotstock.dto.production.supply.ProductionSupplyDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.ProductionSupplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/production/supplies")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ProductionSupplyController {

  private final ProductionSupplyMapper productionSupplyMapper;

  @GetMapping
  public ResponseEntity<List<ProductionSupplyDtoOut>> getProductionSupplies() {
    return ResponseEntity.ok(productionSupplyMapper.getProductionSupplies());
  }

}
