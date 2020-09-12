package com.rstepanchuk.miniplantpotstock.dto.exception;

import lombok.Data;

@Data
public class EtsyAuthExceptionDto {
  String type = "etsy_auth_error";
  String message = "Error while authenticating Etsy API service";
  String redirectUrl;

  public EtsyAuthExceptionDto(String url) {
    this.redirectUrl = url;
  }
}
