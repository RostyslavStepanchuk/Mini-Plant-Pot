package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.dto.pot.PotDtoOut;
import com.rstepanchuk.miniplantpotstock.dto.pot.SkuDtoOut;
import com.rstepanchuk.miniplantpotstock.mapper.PotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/pots")
public class PotController {

  private PotMapper potMapper;

  @Autowired
  public PotController(PotMapper potMapper) {
    this.potMapper = potMapper;
  }

  @GetMapping
  public ResponseEntity<List<PotDtoOut>> getAllPots() {
    return ResponseEntity.ok(potMapper.getAllPots());
  }

  @GetMapping("/all")
  public ResponseEntity<List<SkuDtoOut>> getAllSku() {
    return ResponseEntity.ok(potMapper.getAllSku());
  }


}
