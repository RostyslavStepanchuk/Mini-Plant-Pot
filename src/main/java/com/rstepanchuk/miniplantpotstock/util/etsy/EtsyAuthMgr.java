package com.rstepanchuk.miniplantpotstock.util.etsy;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component("EtsyAuthMgr")
public class EtsyAuthMgr {

  private static final String oauth_consumer_key = "oauth_consumer_key";
  private static final String oauth_token = "oauth_token";
  private static final String oauth_signature_method = "oauth_signature_method";
  private static final String oauth_timestamp = "oauth_timestamp";
  private static final String oauth_nonce = "oauth_nonce";
  private static final String oauth_version = "oauth_version";
  private static final String oauth_signature = "oauth_signature";
  private static final String oauth_callback = "oauth_callback";
  private static final String oauth_verifier = "oauth_verifier";
  private static final String signatureMethod = "HMAC-SHA1";

  private static final String ENCODING_METHOD = "HmacSHA1";
  private static final String AUTH_HEADER_NAME = "Authorization";
  private static final String AUTH_CALLBACK_URL = "http://localhost:8080/api/v1/etsy/access_token"; // TODO: remove localhost reference

  @Value("${etsyProperties.credentials.key}")
  private String key;
  @Value("${etsyProperties.credentials.secret}")
  private String secret;
  @Value("${etsyProperties.credentials.token}")
  private String token;
  @Value("${etsyProperties.credentials.tokenSecret}")
  private String tokenSecret;
  private String verifier;

  public String getKey() {
    return key;
  }

  public void setTokenSecret(String tokenSecret) {
    this.tokenSecret = tokenSecret;
  }

  public void setVerifier(String verifier) {
    this.verifier = verifier;
  }

  public void setToken(String oauthToken) {
    this.token = oauthToken;
  }


  public Consumer<HttpHeaders> provideAuthentication(String method, String url, Map<String, String> params) {
    return headers -> headers.add(AUTH_HEADER_NAME, getAuthHeader(method, url, params, token));
  }

  private String getAuthHeader(String method, String url, Map<String, String> queryParams, String token) {
    Map<String, String> params = new HashMap<>(getOauthParameters(token));
    StringBuilder header = new StringBuilder();
    header.append("OAuth ");
    params.forEach((key, value)-> header.append(headerParam(key, value)));

    params.putAll(queryParams); // while for Authorization header only oauthParams needed, signature requires all params
    params = params.entrySet().stream().sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    String signature = getSignature(method, url, params);
    header.append(headerParam(oauth_signature, signature));
    header.deleteCharAt(header.length() - 1);

    return header.toString();
  }

  private String getSignature(String method, String url, Map<String, String> params) {
    StringBuilder baseString = new StringBuilder();
    baseString.append(method.toUpperCase())
        .append("&")
        .append(encoded(url))
        .append("&");

    StringBuilder query = new StringBuilder();
    params.forEach((key, value) ->
        query.append(queryParam(key, value)));
    query.deleteCharAt(query.length() - 1);
    baseString.append(encoded(query.toString()));
    try {
      byte[] keyBytes = (encoded(secret) + "&" + tokenSecret).getBytes(UTF_8);
      SecretKey key = new SecretKeySpec(keyBytes, ENCODING_METHOD);
      Mac mac = Mac.getInstance(ENCODING_METHOD);
      mac.init(key);

      // encode it, base64 it, change it to string and return.
      return new String(new Base64().encode(mac.doFinal(baseString.toString().getBytes(UTF_8))), UTF_8).trim();
    } catch (NoSuchAlgorithmException | InvalidKeyException algo) {
      algo.printStackTrace();
      throw new RuntimeException(String.format("Unable to encode string %s", baseString.toString()));
    }
  }

  private Map<String, String> getOauthParameters( String token) {
    Map<String, String> params = new HashMap<>();
    if (token != null) {
      params.put(oauth_token, token);
    } else {
      params.put(oauth_callback, AUTH_CALLBACK_URL);
    }
    if (verifier != null) {
      params.put(oauth_verifier, verifier);
    }
    params.put(oauth_consumer_key, key);
    params.put(oauth_nonce, getNonce());
    params.put(oauth_signature_method, signatureMethod);
    params.put(oauth_timestamp, String.valueOf(System.currentTimeMillis() / 1000));
    params.put(oauth_version, "1.0");

    return params;
  }

  private String encoded(String value) {
    return URLEncoder.encode(value, UTF_8);
  }

  private String getNonce() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    return random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
  }

  private String headerParam(String key, String value) {
    return key + "=\"" + encoded(value) + "\",";
  }

  private String queryParam(String key, String value) {
    return key + "=" + encoded(value) + "&";
  }

}

