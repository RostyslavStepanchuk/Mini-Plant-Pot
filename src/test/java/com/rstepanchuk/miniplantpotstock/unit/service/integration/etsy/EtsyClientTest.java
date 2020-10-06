package com.rstepanchuk.miniplantpotstock.unit.service.integration.etsy;

import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyAuthMgr;
import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyClient;
import com.rstepanchuk.miniplantpotstock.util.FormDataParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EtsyClientTest {

  private EtsyClient etsyClient;

  @Mock
  private EtsyAuthMgr etsyAuthMgr;

  @Mock
  private WebClient webClient;
  @Mock
  private WebClient.RequestHeadersSpec requestHeadersMock;
  @Mock
  private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
  @Mock
  private WebClient.RequestBodySpec requestBodyMock;
  @Mock
  private WebClient.RequestBodyUriSpec requestBodyUriMock;
  @Mock
  private WebClient.ResponseSpec responseMock;

  @Captor
  private ArgumentCaptor<String> stringCaptor;

  @Captor
  private ArgumentCaptor<Map<String,String>> paramsCaptor;

  @BeforeEach
  void beforeEach() {
    MockitoAnnotations.initMocks(this);
    EtsyClient testedClient = new EtsyClient(etsyAuthMgr, webClient, new FormDataParser());
    etsyClient = Mockito.spy(testedClient);
  }

  @Test
  void accessToken_shouldResetTokenAndTokenSecretToNewValuesFromEtsy() {
    String tempToken = "tempToken";
    String tempVerifier = "tempVerifier";

    when(etsyAuthMgr.provideAuthentication("GET",
        "https://openapi.etsy.com/v2/oauth/access_token",
        new HashMap<>()))
          .thenReturn(headers -> {});

    when(webClient.get()).thenReturn(requestHeadersUriMock);
    when(requestHeadersUriMock.uri(any(Function.class))).thenReturn(requestHeadersMock);
    when(requestHeadersMock.headers(any(Consumer.class))).thenReturn(requestHeadersMock);
    when(requestHeadersMock.retrieve()).thenReturn(responseMock);
    when(responseMock.onStatus(any(), any())).thenReturn(responseMock);
    when(responseMock.bodyToMono(String.class)).thenReturn(Mono.just("oauth_token=finalToken&oauth_token_secret=finalTokenSecret"));

    etsyClient.accessToken(tempToken, tempVerifier);

    Mockito.verify(etsyAuthMgr).setToken(tempToken);
    Mockito.verify(etsyAuthMgr).setVerifier(tempVerifier);
    Mockito.verify(etsyAuthMgr).setToken("finalToken");
    Mockito.verify(etsyAuthMgr).setTokenSecret("finalTokenSecret");
  }

}
