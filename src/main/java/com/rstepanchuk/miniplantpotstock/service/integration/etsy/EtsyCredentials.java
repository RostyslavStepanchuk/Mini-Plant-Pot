package com.rstepanchuk.miniplantpotstock.service.integration.etsy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "etsy.credentials")
public class EtsyCredentials {

  private String authKey;
  private String secret;
  private String token;
  private String tokenSecret;
}
