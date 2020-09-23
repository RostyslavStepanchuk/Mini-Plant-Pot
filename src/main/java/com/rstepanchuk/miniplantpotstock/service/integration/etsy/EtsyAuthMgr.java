package com.rstepanchuk.miniplantpotstock.service.integration.etsy;

import com.rstepanchuk.miniplantpotstock.exception.Auth1SignatureEncodingError;
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

  private static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
  static final String OAUTH_TOKEN = "oauth_token";
  static final String OAUTH_TOKEN_SECRET = "oauth_token_secret";
  private static final String OAUTH_SIGNATURE_METHOD = "oauth_signature_method";
  private static final String OAUTH_TIMESTAMP = "oauth_timestamp";
  private static final String OAUTH_NONCE = "oauth_nonce";
  private static final String OAUTH_VERSION = "oauth_version";
  private static final String OAUTH_SIGNATURE = "oauth_signature";
  private static final String OAUTH_CALLBACK = "oauth_callback";
  private static final String OAUTH_VERIFIER = "oauth_verifier";
  private static final String SIGNATURE_METHOD = "HMAC-SHA1";

  private static final String ENCODING_METHOD = "HmacSHA1";
  private static final String AUTH_HEADER_NAME = "Authorization";
  private static final String AUTH_CALLBACK_URL = "http://localhost:8080/api/v1/etsy/access_token"; // TODO: remove localhost reference

  @Value("${etsyProperties.credentials.key}")
  private String authKey;
  @Value("${etsyProperties.credentials.secret}")
  private String secret;
  @Value("${etsyProperties.credentials.token}")
  private String token;
  @Value("${etsyProperties.credentials.tokenSecret}")
  private String tokenSecret;
  private String verifier;
  private Random random = new Random();

  void setTokenSecret(String tokenSecret) {
    this.tokenSecret = tokenSecret;
  }

  void setVerifier(String verifier) {
    this.verifier = verifier;
  }

  void setToken(String oauthToken) {
    this.token = oauthToken;
  }


  Consumer<HttpHeaders> provideAuthentication(String method, String url, Map<String, String> params) {
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
    header.append(headerParam(OAUTH_SIGNATURE, signature));
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
      throw new Auth1SignatureEncodingError(String.format("Unable to encode string %s", baseString.toString()));
    }
  }

  private Map<String, String> getOauthParameters( String token) {
    Map<String, String> params = new HashMap<>();
    if (token != null) {
      params.put(OAUTH_TOKEN, token);
    } else {
      params.put(OAUTH_CALLBACK, AUTH_CALLBACK_URL);
    }
    if (verifier != null) {
      params.put(OAUTH_VERIFIER, verifier);
    }
    params.put(OAUTH_CONSUMER_KEY, authKey);
    params.put(OAUTH_NONCE, getNonce());
    params.put(OAUTH_SIGNATURE_METHOD, SIGNATURE_METHOD);
    params.put(OAUTH_TIMESTAMP, String.valueOf(System.currentTimeMillis() / 1000));
    params.put(OAUTH_VERSION, "1.0");

    return params;
  }

  private String encoded(String value) {
    return URLEncoder.encode(value, UTF_8);
  }

  private String getNonce() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;

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

