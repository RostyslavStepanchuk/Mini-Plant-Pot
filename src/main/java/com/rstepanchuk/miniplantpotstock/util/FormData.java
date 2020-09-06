package com.rstepanchuk.miniplantpotstock.util;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FormData {

  Map<String, String> data = new HashMap<>();

  private FormData() {
  }

  public void put(String key, String value) {
    data.put(key, value);
  }

  public String get(String key) {
    return data.get(key);
  }

  public boolean isEmpty() {
    return data.isEmpty();
  }

  public static FormData digest(String urlForm) {
    FormData formData = new FormData();
    if (urlForm == null || urlForm.equals("")) {
      return formData;
    }

    String[] responseParams = urlForm.split("&");
    for (String param: responseParams) {
      String[] dataPair = param.split("=");

      formData.put(decoded(dataPair[0]), decoded(dataPair[1]));
    }

    return formData;
  }

  private static String decoded(String value) {
    return java.net.URLDecoder.decode(value, UTF_8);
  }
}
