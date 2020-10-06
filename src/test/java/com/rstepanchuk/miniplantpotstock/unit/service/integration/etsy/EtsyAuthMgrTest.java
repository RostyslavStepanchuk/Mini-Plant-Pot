package com.rstepanchuk.miniplantpotstock.unit.service.integration.etsy;

import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyAuthMgr;
import com.rstepanchuk.miniplantpotstock.service.integration.etsy.EtsyCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;



import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EtsyAuthMgrTest {

  @InjectMocks
  private EtsyAuthMgr etsyAuthMgr;

  @Mock
  private EtsyCredentials etsyCredentials;


  @BeforeEach
  void beforeEach(){
    when(etsyCredentials.getAuthKey()).thenReturn("AUTH_KEY");
    when(etsyCredentials.getSecret()).thenReturn("SECRET");
    when(etsyCredentials.getToken()).thenReturn("TOKEN");
    when(etsyCredentials.getTokenSecret()).thenReturn("TOKEN_SECRET");
    when(etsyCredentials.getVerifier()).thenReturn("VERIFIER");
  }

  @Test
  void shouldPutCredentialsDataInAuthorizationHeaderInValidFormatAndProvideSignature() {
    Consumer<HttpHeaders> updateHeaders = etsyAuthMgr.provideAuthentication("GET", "https://some.valid.address.com", new HashMap<>());
    HttpHeaders testHeaders = new HttpHeaders();
    updateHeaders.accept(testHeaders);

    assertTrue(testHeaders.containsKey("Authorization"));
    assertEquals(1, testHeaders.size());
    String authData = testHeaders.getFirst("Authorization");
    assertNotNull(authData);
    assertEquals("OAuth ", authData.substring(0,6));

    Map<String, String> authValues = collectParams(authData);

    assertTrue(authValues.containsKey("oauth_nonce"));
    assertTrue(authValues.containsKey("oauth_token"));
    assertTrue(authValues.containsKey("oauth_consumer_key"));
    assertTrue(authValues.containsKey("oauth_signature_method"));
    assertTrue(authValues.containsKey("oauth_timestamp"));
    assertTrue(authValues.containsKey("oauth_version"));
    assertTrue(authValues.containsKey("oauth_signature"));
    assertTrue(authValues.containsKey("oauth_verifier"));


    assertEquals("\"TOKEN\"", authValues.get("oauth_token"));
    assertEquals("\"AUTH_KEY\"", authValues.get("oauth_consumer_key"));
    assertEquals("\"HMAC-SHA1\"", authValues.get("oauth_signature_method"));
    assertEquals("\"1.0\"", authValues.get("oauth_version"));
    assertEquals("\"VERIFIER\"", authValues.get("oauth_verifier"));
  }

  @Test
  void shouldIgnoreTheValuesButStillProvideAuthorizationIfTokenAndTokenSecretCredentialsAreNull(){
    when(etsyCredentials.getToken()).thenReturn(null);
    when(etsyCredentials.getTokenSecret()).thenReturn(null);

    Consumer<HttpHeaders> updateHeaders = etsyAuthMgr.provideAuthentication("GET", "https://some.valid.address.com", new HashMap<>());
    HttpHeaders testHeaders = new HttpHeaders();
    updateHeaders.accept(testHeaders);

    assertTrue(testHeaders.containsKey("Authorization"));
    assertEquals(1, testHeaders.size());
    String authData = testHeaders.getFirst("Authorization");
    assertNotNull(authData);

    Map<String, String> params = collectParams(authData);

    assertNull(params.get("oauth_token"));
  }

  @Test
  void shouldIgnoreVerifierIfItsNotProvidedButStillPerformAuthorization() {
    when(etsyCredentials.getVerifier()).thenReturn(null);

    Consumer<HttpHeaders> updateHeaders = etsyAuthMgr.provideAuthentication("GET", "https://some.valid.address.com", new HashMap<>());
    HttpHeaders testHeaders = new HttpHeaders();
    updateHeaders.accept(testHeaders);

    assertTrue(testHeaders.containsKey("Authorization"));
    assertEquals(1, testHeaders.size());
    String authData = testHeaders.getFirst("Authorization");
    assertNotNull(authData);

    Map<String, String> params = collectParams(authData);

    assertNull(params.get("oauth_verifier"));
  }

  private Map<String, String> collectParams(String headerValue) {
    HashMap<String, String> authValues = new HashMap<>();
    if (headerValue != null) {
      String authParams = headerValue.substring(6);
      String[] split = authParams.split(",");
      for (String keyValuePair : split) {
        String[] value = keyValuePair.split("=");
        authValues.put(value[0], value[1]);
      }
    }
    return authValues;
  }

}
