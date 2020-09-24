package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class EtsyService {

  private final EtsyClient etsyClient;

  public void accessToken(String oauthToken, String oauthVerifier) {
    etsyClient.accessToken(oauthToken, oauthVerifier);
  }

  public String getTransactions() {
    return etsyClient.getTransactions();
  }
}
