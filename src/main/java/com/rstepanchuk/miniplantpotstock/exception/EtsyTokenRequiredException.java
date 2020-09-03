package com.rstepanchuk.miniplantpotstock.exception;

public class EtsyTokenRequiredException extends RuntimeException {

  public EtsyTokenRequiredException(String message) {
    super(message);
  }
}
