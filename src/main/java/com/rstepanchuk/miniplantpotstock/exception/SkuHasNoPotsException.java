package com.rstepanchuk.miniplantpotstock.exception;

import java.security.InvalidParameterException;

public class SkuHasNoPotsException extends InvalidParameterException {
  public SkuHasNoPotsException(String message) {
    super(message);
  }
}
