package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.dto.catalog.PotDtoOut;
import com.rstepanchuk.miniplantpotstock.dto.catalog.SkuDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.SkuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/catalog")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class CatalogController {

  private final SkuMapper skuMapper;

  @GetMapping(value = "/pot")
  public ResponseEntity<List<PotDtoOut>> getPots() {
    return ResponseEntity.ok(skuMapper.getPots());
  }

  @GetMapping(value = "/sku")
  public ResponseEntity<List<SkuDtoOut>> getSkus() {
    return ResponseEntity.ok(skuMapper.getSkus());
  }

}
