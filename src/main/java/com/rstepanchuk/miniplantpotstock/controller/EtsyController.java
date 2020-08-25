package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.service.EtsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: remove this controller after etsy service can be embedded by other services
@RestController
@RequestMapping(value = "api/v1/etsy")
public class EtsyController {

  private EtsyService service;

  @Autowired
  public EtsyController(EtsyService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<String> getAllPots() {
    service.getListings();
    return ResponseEntity.ok("Ok");
  }

}
