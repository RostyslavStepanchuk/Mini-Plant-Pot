//package com.rstepanchuk.miniplantpotstock.service.external.etsy;
//
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.crypto.Mac;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.digest.HmacAlgorithms;
//import org.apache.commons.codec.digest.HmacUtils;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.utils.URIUtils;
//import org.apache.http.client.utils.URLEncodedUtils;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//
//public class Signatur {
//  private static String key = "someValue";
//  private static String secret = "someSecret";
//
//  private static final String HMAC_SHA1 = "HmacSHA1";
//
//  private static final String ENC = "UTF-8";
//
//  private static Base64 base64 = new Base64();
//
//  /**
//   *
//   * @param url
//   *            the url for "request_token" URLEncoded.
//   * @param params
//   *            parameters string, URLEncoded.
//   * @return
//   * @throws UnsupportedEncodingException
//   * @throws NoSuchAlgorithmException
//   * @throws InvalidKeyException
//   */
//  private static String getSignature(String url, String params)
//      throws UnsupportedEncodingException, NoSuchAlgorithmException,
//      InvalidKeyException {
//    /**
//     * base has three parts, they are connected by "&": 1) protocol 2) URL
//     * (need to be URLEncoded) 3) Parameter List (need to be URLEncoded).
//     */
//    StringBuilder base = new StringBuilder();
//    base.append("GET&");
//    base.append(url);
//    base.append("&");
//    base.append(params);
//    System.out.println("Stirng for oauth_signature generation:" + base);
//    // yea, don't ask me why, it is needed to append a "&" to the end of
//    // secret key.
//    byte[] keyBytes = (secret + "&").getBytes(ENC);
//
//    SecretKey key = new SecretKeySpec(keyBytes, HMAC_SHA1);
//
//    Mac mac = Mac.getInstance(HMAC_SHA1);
//    mac.init(key);
//
//    // encode it, base64 it, change it to string and return.
//    return new String(base64.encode(mac.doFinal(base.toString().getBytes(
//        ENC))), ENC).trim();
//  }
//
//  public static void main(String[] args) throws Exception {
//    HttpClient httpclient = new DefaultHttpClient();
//    List<NameValuePair> qparams = new ArrayList<NameValuePair>();
//    // These params should ordered in key
////    qparams.add(new BasicNameValuePair("oauth_callback", "oob"));
//    qparams.add(new BasicNameValuePair("oauth_consumer_key", key));
//    qparams.add(new BasicNameValuePair("oauth_nonce", "98a80b96"));
//    qparams.add(new BasicNameValuePair("oauth_signature_method",
//        "HMAC-SHA1"));
//    qparams.add(new BasicNameValuePair("oauth_timestamp", "1597747898"));
//    qparams.add(new BasicNameValuePair("oauth_version", "1.0"));
//    qparams.add(new BasicNameValuePair("scope", "transactions_r"));
//    String url = URLEncoder.encode("https://openapi.etsy.com/v2/oauth/request_token", ENC);
//    String params = URLEncodedUtils.format(qparams, ENC);
//    String encodedParams = URLEncoder.encode(params, ENC);
//
//    String signature = getSignature(url, encodedParams);
//
//
//    // add it to params list
//    qparams.add(new BasicNameValuePair("oauth_signature", signature));
//
//    // generate URI which lead to access_token and token_secret.
//    URI uri = URIUtils.createURI("https", "openapi.etsy.com", -1,
//        "/v2/oauth/request_token",
//        URLEncodedUtils.format(qparams, ENC), null);
//
//    System.out.println("Get Token and Token Secrect from:"
//        + uri.toString());
//
//    HttpGet httpget = new HttpGet(uri);
//    // output the response content.
//    System.out.println("oken and Token Secrect:");
//
//    HttpResponse response = httpclient.execute(httpget);
//    HttpEntity entity = response.getEntity();
//    if (entity != null) {
//      InputStream instream = entity.getContent();
//      int len;
//      byte[] tmp = new byte[2048];
//      while ((len = instream.read(tmp)) != -1) {
//        System.out.println(new String(tmp, 0, len, ENC));
//      }
//    }
//
//  }
//}
//
//
//
//
