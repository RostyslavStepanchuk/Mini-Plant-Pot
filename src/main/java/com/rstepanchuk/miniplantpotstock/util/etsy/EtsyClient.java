package com.rstepanchuk.miniplantpotstock.util.etsy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EtsyClient {

  private EtsyAuthenticationManager authMgr;

  @Autowired
  public EtsyClient(EtsyAuthenticationManager authMgr) {
    this.authMgr = authMgr;
  }

  public void getListings() {
    String initialToken = authMgr.getInitialToken();
    int useless = 0;
  }

}
