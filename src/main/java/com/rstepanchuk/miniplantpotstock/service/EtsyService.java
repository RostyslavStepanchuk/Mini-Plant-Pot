package com.rstepanchuk.miniplantpotstock.service;

import com.rstepanchuk.miniplantpotstock.util.etsy.EtsyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtsyService {

  private EtsyClient etsyClient;

  @Autowired
  public EtsyService(EtsyClient etsyClient) {
    this.etsyClient = etsyClient;
  }

  public void getListings() {
    etsyClient.getListings();
  }
}
