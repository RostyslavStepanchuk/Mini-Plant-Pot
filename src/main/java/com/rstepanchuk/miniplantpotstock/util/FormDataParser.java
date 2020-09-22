package com.rstepanchuk.miniplantpotstock.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class FormDataParser {

  public Map<String, String> parse (String urlForm) {
    Map<String, String> data = new HashMap<>();
    if (urlForm == null || urlForm.equals("")) {
      return data;
    }
    String[] responseParams = urlForm.split("&");
    for (String param: responseParams) {
      String[] dataPair = param.split("=");

      data.put(decoded(dataPair[0]), decoded(dataPair[1]));
    }
    return data;
  }

  private String decoded(String value) {
    return java.net.URLDecoder.decode(value, UTF_8);
  }
}
