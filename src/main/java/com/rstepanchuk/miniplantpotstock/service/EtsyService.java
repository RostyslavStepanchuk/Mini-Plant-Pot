package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.dto.integration.etsy.transaction.EtsyTransactionsCollection;
import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class EtsyService {

  private final EtsyClient etsyClient;

  public void accessToken(String oauthToken, String oauthVerifier) {
    etsyClient.accessToken(oauthToken, oauthVerifier);
  }

  public EtsyTransactionsCollection getTransactions() {
    EtsyTransactionsCollection initialTransactions = etsyClient.getTransactions();
    initialTransactions.getResults()
        .forEach(transaction -> transaction.setListing(etsyClient.getListing(transaction.getListing_id())));
    return initialTransactions;
  }

  public String getStringTransactions() {
    return etsyClient.getStringTransactions();
  }

  public String getAuthUrl() {
    return etsyClient.getNewAuthUrl();
  }
}
