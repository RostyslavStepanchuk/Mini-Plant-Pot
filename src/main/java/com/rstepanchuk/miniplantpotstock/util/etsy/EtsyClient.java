package com.rstepanchuk.miniplantpotstock.util.etsy;

import com.rstepanchuk.miniplantpotstock.exception.EtsyAuthorizationException;
import com.rstepanchuk.miniplantpotstock.util.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.HashMap;
import java.util.Map;

@Component
public class EtsyClient {

  private static final String ETSY_ACCESS_TOKEN_URL = "/v2/oauth/access_token";
  private static final String ETSY_REQUEST_TOKEN_URL = "/v2/oauth/request_token";
  private static final String ETSY_GET_LISTINGS_URL = "/v2/shops/%s/listings/active";
  private static final String ETSY_GET_TRANSACTIONS_URL = "/v2/shops/%s/transactions";
  private static final String ETSY_HOST = "openapi.etsy.com";
  private static final String ETSY_PROTOCOL = "https";

  private static final String GET = "GET";

  @Value("${etsyProperties.credentials.shopId}")
  private String shopId;

  private EtsyAuthMgr authMgr;

  @Autowired
  public EtsyClient(EtsyAuthMgr authMgr) {
    this.authMgr = authMgr;
  }

  private String call(String url) {
    return call(url, GET);
  }

  private String call(String url, String method) {
    return call(url, method, new HashMap<>());
  }

  private String call(String url, String method, Map<String, String> params) {
    WebClient client = WebClient.create();
    try {
      return client.get().uri(uriBuilder -> {
        uriBuilder.scheme(ETSY_PROTOCOL).host(ETSY_HOST).path(url);
        params.forEach(uriBuilder::queryParam);
        return uriBuilder.build();
      })
          .headers(authMgr.provideAuthentication(method, ETSY_PROTOCOL + "://" + ETSY_HOST + url, params))
          .retrieve()
          .bodyToMono(String.class).block();
    } catch (WebClientResponseException e) {
      if (e.getRawStatusCode() == 403 || e.getRawStatusCode() == 401) {
        authMgr.setToken(null);
        authMgr.setTokenSecret("");
        String etsyAuthorizationUrl = getEtsyAuthorizationUrl();
        throw new EtsyAuthorizationException(etsyAuthorizationUrl);
      }
      throw new RuntimeException(e.getMessage());
    }
  }

  public String getEtsyAuthorizationUrl() {
    Map<String, String> params = new HashMap<>();
    params.put("scope", "transactions_r");

    String response = call(ETSY_REQUEST_TOKEN_URL, "GET", params);
    FormData data = FormData.digest(response);
    authMgr.setTokenSecret(data.get("oauth_token_secret"));
    return data.get("login_url");
  }

  public void getListings() {
    Map<String, String> params = new HashMap<>();
    params.put("api_key", authMgr.getKey());

    String url = String.format(ETSY_GET_LISTINGS_URL, shopId);

    String response = call(url, GET, params);
    //TODO: map response to model
    int useless = 0;
  }

  public void accessToken(String oauthToken, String oauthVerifier) {
    authMgr.setToken(oauthToken);
    authMgr.setVerifier(oauthVerifier);
    String response = call(ETSY_ACCESS_TOKEN_URL);
    FormData data = FormData.digest(response);
    authMgr.setToken(data.get("oauth_token"));
    authMgr.setTokenSecret(data.get("oauth_token_secret"));
  }

  public String getTransactions() {
    String url = String.format(ETSY_GET_TRANSACTIONS_URL, shopId);
    String response = call(url);
    //TODO: map response to model
    return response;
  }
}
