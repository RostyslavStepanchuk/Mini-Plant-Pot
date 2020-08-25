package com.rstepanchuk.miniplantpotstock.util.etsy;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class EtsyAuthenticationManager {

  @Value("${etsyProperties.credentials.key}")
  public String key;
  @Value("${etsyProperties.credentials.secret}")
  public String secret;
  private final String hmacSha1 = "HmacSHA1";


  public String getInitialToken() {
    WebClient webClient = WebClient.builder().filter((req, next)-> {
      ClientRequest newReq = signRequest(req);
      return next.exchange(newReq);
    })
        .build();

    return webClient.get().uri(uriBuilder -> uriBuilder
        .scheme("https")
        .host("openapi.etsy.com")
        .path("/v2/oauth/request_token")
        .queryParam("oauth_consumer_key", key)
        .queryParam("oauth_nonce", "9fwef80b96")
        .queryParam("oauth_signature_method", "HMAC-SHA1")
        .queryParam("oauth_timestamp", System.currentTimeMillis() / 1000)
        .queryParam("oauth_version", "1.0")
        .queryParam("scope", "transactions_r")
        .build())
        .retrieve().bodyToMono(String.class).block();
  }

  private String getRequestSignature(ClientRequest req) {

    String method = req.method().toString();
    URI url = req.url();
    String urlPath = URLEncoder.encode(String.format("%s://%s%s", url.getScheme(), url.getHost(), url.getPath()), UTF_8);
    String params = URLEncoder.encode(url.getQuery(), UTF_8);
    try {
      StringBuilder base = new StringBuilder();
      base.append(method);
      base.append("&");
      base.append(urlPath);
      base.append("&");
      base.append(params);

      // yea, don't ask me why, it is needed to append a "&" to the end of
      // secret key.
      byte[] keyBytes = (secret + "&").getBytes(UTF_8);

      SecretKey key = new SecretKeySpec(keyBytes, hmacSha1);

      Mac mac = Mac.getInstance(hmacSha1);
      mac.init(key);

      // encode it, base64 it, change it to string and return.
      String signature = new String(new Base64().encode(mac.doFinal(base.toString().getBytes(UTF_8))), UTF_8).trim();
      return URLEncoder.encode(signature, UTF_8);
    } catch (NoSuchAlgorithmException | InvalidKeyException algo) {
      algo.printStackTrace();
    }
    return null;
  }

  private ClientRequest signRequest(ClientRequest req) {
    String requestSignature = getRequestSignature(req);
    return ClientRequest.from(req)
        .url(URI.create(req.url().toString() + "&oauth_signature=" + requestSignature))
        .build();
  }
}
