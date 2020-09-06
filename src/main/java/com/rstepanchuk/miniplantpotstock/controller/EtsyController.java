package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.service.EtsyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

// TODO: remove this controller after etsy service can be embedded by other services
@RestController
@RequestMapping(value = "api/v1/etsy")
public class EtsyController {

  private EtsyService service;

  @Autowired
  public EtsyController(EtsyService service) {
    this.service = service;
  }

  @GetMapping(value = "/listings")
  public ResponseEntity<String> getAllPots() {
    service.getListings();
    return ResponseEntity.ok("Ok");
  }

  @GetMapping(value = "/authorization_url")
  public ResponseEntity<String> getEtsyAuthorizationUrl() {
    return ResponseEntity.ok(service.getEtsyAuthorizationUrl());
  }


  @GetMapping(value = "/access_token")
  public ResponseEntity<String> accessToken(
      @PathParam(value = "oauth_verifier")String oauthVerifier,
      @PathParam(value = "oauth_token")String oauthToken) {
    service.accessToken(oauthToken, oauthVerifier);
    return ResponseEntity.ok("Ok");
  }

}
