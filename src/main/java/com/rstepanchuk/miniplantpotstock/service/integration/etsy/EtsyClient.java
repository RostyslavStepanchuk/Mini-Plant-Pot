package com.rstepanchuk.miniplantpotstock.service.integration.etsy;

import com.rstepanchuk.miniplantpotstock.exception.EtsyAuthorizationException;
import com.rstepanchuk.miniplantpotstock.util.FormDataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class EtsyClient {

  private static final String ETSY_ACCESS_TOKEN_URL = "/v2/oauth/access_token";
  private static final String ETSY_REQUEST_TOKEN_URL = "/v2/oauth/request_token";
  private static final String ETSY_GET_TRANSACTIONS_URL = "/v2/shops/%s/transactions";
  private static final String ETSY_HOST = "openapi.etsy.com";
  private static final String ETSY_PROTOCOL = "https";

  private static final String GET = "GET";

  @Value("${etsyProperties.credentials.shopId}")
  private String shopId;

  private EtsyAuthMgr authMgr;
  private FormDataParser formDataParser;


  @Autowired
  public EtsyClient(EtsyAuthMgr authMgr, FormDataParser formDataParser) {
    this.authMgr = authMgr;
    this.formDataParser = formDataParser;
  }

  private String call(String url) {
    return call(url, GET);
  }

  private String call(String url, String method) {
    return call(url, method, new HashMap<>());
  }

  private String call(String url, String method, Map<String, String> params) {
    WebClient client = WebClient.create();
    return client
        .get()
        .uri(uriBuilder -> buildUri(uriBuilder, url, params))
        .headers(authMgr.provideAuthentication(method, ETSY_PROTOCOL + "://" + ETSY_HOST + url, params))
        .retrieve()
        .onStatus(status->status.value() == 401, this::handleNonAuthorized)
        .bodyToMono(String.class).block();

  }

  private String getEtsyAuthorizationUrl() {
    Map<String, String> params = new HashMap<>();
    params.put("scope", "transactions_r");

    String response = call(ETSY_REQUEST_TOKEN_URL, "GET", params);
    Map<String, String> data = formDataParser.parse(response);
    authMgr.setTokenSecret(data.get(EtsyAuthMgr.OAUTH_TOKEN_SECRET));
    return data.get("login_url");
  }

  private Mono<EtsyAuthorizationException> handleNonAuthorized(ClientResponse resp) {
    authMgr.setToken(null);
    authMgr.setTokenSecret("");
    String etsyAuthorizationUrl = getEtsyAuthorizationUrl();
    return Mono.just(new EtsyAuthorizationException(etsyAuthorizationUrl));
  }

  private URI buildUri(UriBuilder uriBuilder, String path, Map<String, String> params) {
    uriBuilder.scheme(ETSY_PROTOCOL).host(ETSY_HOST).path(path);
    params.forEach(uriBuilder::queryParam);
    return uriBuilder.build();
  }

  public void accessToken(String oauthToken, String oauthVerifier) {
    authMgr.setToken(oauthToken);
    authMgr.setVerifier(oauthVerifier);
    String response = call(ETSY_ACCESS_TOKEN_URL);
    Map<String, String> data = formDataParser.parse(response);
    authMgr.setToken(data.get(EtsyAuthMgr.OAUTH_TOKEN));
    authMgr.setTokenSecret(data.get(EtsyAuthMgr.OAUTH_TOKEN_SECRET));
  }

  public String getTransactions() {
    String url = String.format(ETSY_GET_TRANSACTIONS_URL, shopId);
    return call(url);
    //TODO: map response to model
  }
}
