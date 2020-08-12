package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.service.EtsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

  @Autowired
  EtsyService etsyService;

  @GetMapping
  public ResponseEntity<String> redirectToMobile() {
    return ResponseEntity.ok("Some day this will be a cool application");
  }

}
