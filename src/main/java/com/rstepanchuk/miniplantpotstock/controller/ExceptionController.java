package com.rstepanchuk.miniplantpotstock.controller;

import com.rstepanchuk.miniplantpotstock.dto.exception.EtsyAuthExceptionDto;
import com.rstepanchuk.miniplantpotstock.exception.EtsyAuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

  @ExceptionHandler(EtsyAuthorizationException.class)
  public ResponseEntity<EtsyAuthExceptionDto> handleEtsyAuthenticationError(Exception error) {
    EtsyAuthExceptionDto responseDto = new EtsyAuthExceptionDto(error.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(responseDto);
  }

}
