package com.rstepanchuk.miniplantpotstock.unit.util;

import com.rstepanchuk.miniplantpotstock.util.FormDataParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FormDataParserTest {

  FormDataParser formDataParser;

  @BeforeEach
  void getDataParser(){
    formDataParser = new FormDataParser();
  }

  @Test
  void parseShouldReturnEmptyMapIfFormIsEmpty(){
    String emptyForm = "";
    Map<String, String> data = formDataParser.parse(emptyForm);
    assertTrue(data.isEmpty());
  }

  @Test
  void parseShouldReturnEmptyMapIfFormIsNull(){
    Map<String, String> data = formDataParser.parse(null);
    assertTrue(data.isEmpty());
  }

  @Test
  void parseOutputShouldHaveAllSourceKeysAndValues(){
    HashMap<String, String> formData = new HashMap<>();
    String testKey1 = "key_test";
    String testKey2 = "token";
    String testKey3 = "next_key";
    String testValue1 = "testValue";
    String testValue2 = "few320fahewfads2124";
    String testValue3 = "true";
    formData.put(testKey1, testValue1);
    formData.put(testKey2, testValue2);
    formData.put(testKey3, testValue3);

    String form = createForm(formData);

    Map<String, String> output = formDataParser.parse(form);
    assertArrayEquals(formData.keySet().toArray(), output.keySet().toArray());
    assertArrayEquals(formData.values().toArray(), output.values().toArray());
  }

  @Test
  void parseShouldWorkWithSingleFieldForm(){
    HashMap<String, String> formData = new HashMap<>();
    String testKey1 = "key_test";
    String testValue1 = "testValue";
    formData.put(testKey1, testValue1);

    String form = createForm(formData);

    Map<String, String> output = formDataParser.parse(form);
    assertTrue(output.containsKey(testKey1));
    assertEquals(testValue1, output.get(testKey1));
    assertEquals(1, output.size());
  }

  @Test
  void parseShouldDecodeKeysAndValues(){
    HashMap<String, String> formData = new HashMap<>();
    String testKey1 = "key-with$-symbols%";
    String testKey2 = "key*_with@_symbols2";

    String testValue1 = "https://some/url.com";
    String testValue2 = "!@#$%^&*()_+-=<>?\";:{}[]";

    formData.put(testKey1, testValue1);
    formData.put(testKey2, testValue2);

    String form = createForm(formData);

    Map<String, String> output = formDataParser.parse(form);
    assertArrayEquals(formData.keySet().toArray(), output.keySet().toArray());
    assertArrayEquals(formData.values().toArray(), output.values().toArray());
  }

  private String encode(String input){
    return java.net.URLEncoder.encode(input, UTF_8);
  }

  private String createForm (Map<String, String> data) {
    return data.entrySet().stream()
        .map(entrySet -> String.format("%s=%s", encode(entrySet.getKey()), encode(entrySet.getValue())))
        .collect(Collectors.joining("&"));
  }

}
