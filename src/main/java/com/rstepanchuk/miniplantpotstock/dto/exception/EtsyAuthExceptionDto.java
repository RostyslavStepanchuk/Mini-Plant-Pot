package com.rstepanchuk.miniplantpotstock.dto.exception;

import lombok.Value;

@Value
public class EtsyAuthExceptionDto {
  final String type = "etsy_auth_error";
  final String message = "Error while authenticating Etsy API service";
  final String redirectUrl;

  public EtsyAuthExceptionDto(String url) {
    this.redirectUrl = url;
  }
}
