package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.service.EtsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: remove this controller after etsy service can be embedded by other services
@RestController
@RequestMapping(value = "api/v1/integration/etsy")
public class EtsyController {

  private EtsyService service;

  @Autowired
  public EtsyController(EtsyService service) {
    this.service = service;
  }

  @GetMapping(value = "/transactions")
  public ResponseEntity<String> getTransactions() {
    return ResponseEntity.ok(service.getTransactions());
  }

  @GetMapping(value = "/access_token")
  public ResponseEntity<String> accessToken(
      @RequestParam(value = "oauth_verifier")String oauthVerifier,
      @RequestParam(value = "oauth_token")String oauthToken) {
    service.accessToken(oauthToken, oauthVerifier);
    return ResponseEntity.ok("Ok");
  }

}
