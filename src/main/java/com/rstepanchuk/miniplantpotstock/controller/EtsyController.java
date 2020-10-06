package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.dto.exception.EtsyAuthExceptionDto;
import com.rstepanchuk.miniplantpotstock.dto.integration.etsy.transaction.EtsyTransactionsCollection;
import com.rstepanchuk.miniplantpotstock.service.EtsyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: remove this controller after etsy service can be embedded by other services
@RestController
@RequestMapping(value = "api/v1/integration/etsy")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class EtsyController {

  private final EtsyService service;

  @GetMapping(value = "/transactions")
  public ResponseEntity<EtsyTransactionsCollection> getTransactions() {
    return ResponseEntity.ok(service.getTransactions());
  }

  @GetMapping(value = "/transactions/string")
  public ResponseEntity<String> getStringTransactions() {
    return ResponseEntity.ok(service.getStringTransactions());
  }

  @GetMapping(value = "/access_token")
  public ResponseEntity<String> accessToken(
      @RequestParam(value = "oauth_verifier")String oauthVerifier,
      @RequestParam(value = "oauth_token")String oauthToken) {
    service.accessToken(oauthToken, oauthVerifier);
    return ResponseEntity.ok("Ok");
  }

  @GetMapping(value = "/authorization_url")
  public ResponseEntity<EtsyAuthExceptionDto> getAuthUrl() {
    return ResponseEntity.ok(new EtsyAuthExceptionDto(service.getAuthUrl()));
  }

}
