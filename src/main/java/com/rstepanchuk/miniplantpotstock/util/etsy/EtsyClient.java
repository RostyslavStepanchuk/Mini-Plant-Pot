package com.rstepanchuk.miniplantpotstock.util.etsy;

import com.rstepanchuk.miniplantpotstock.exception.EtsyTokenRequiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class EtsyClient {

  private EtsyAuthMgr authMgr;

  @Autowired
  public EtsyClient(EtsyAuthMgr authMgr) {
    this.authMgr = authMgr;
  }

  public void getListings() {
    try {
      authMgr.provideAuthentication("GET", "somedummyurl.com", new HashMap<>());
    } catch (EtsyTokenRequiredException e) {
      e.printStackTrace();
    }

  }

}
