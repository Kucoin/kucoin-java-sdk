/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interceptor;

import com.kucoin.sdk.constants.APIConstants;
import com.kucoin.sdk.exception.KucoinApiException;
import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by zicong.lu on 2018/12/14.
 */
@Getter
@Setter
public class AuthenticationInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    private String apiKey;
    private String secret;
    private String passPhrase;

    /**
     * Constructor of API - keys are loaded from VM options, environment variables, resource files
     *
     * @param apiKey     The API key.
     * @param secret     The API secret.
     * @param passPhrase The API passphrase.
     * @throws KucoinApiException in case of any error
     */
    public AuthenticationInterceptor(String apiKey, String secret, String passPhrase) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.passPhrase = passPhrase;
    }

    /**
     * Validation we have API keys set up
     *
     * @throws KucoinApiException in case of any error
     */
    protected void validateCredentials() throws KucoinApiException {
        String humanMessage = ". Please check environment variables or VM options";
        if (this.apiKey == null || this.apiKey.isEmpty())
            throw new KucoinApiException("Missing " + APIConstants.USER_API_KEY + humanMessage);
        if (this.secret == null || this.secret.isEmpty())
            throw new KucoinApiException("Missing " + APIConstants.USER_API_SECRET + humanMessage);
        if (this.passPhrase == null || this.passPhrase.isEmpty())
            throw new KucoinApiException("Missing " + APIConstants.USER_API_PASSPHRASE + humanMessage);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        validateCredentials();
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();

        String timestamp = String.valueOf(System.currentTimeMillis());
        String signature = genSignature(original, secret, timestamp);

        newRequestBuilder.addHeader(APIConstants.API_HEADER_KEY, apiKey);
        newRequestBuilder.addHeader(APIConstants.API_HEADER_SIGN, signature);
        newRequestBuilder.addHeader(APIConstants.API_HEADER_PASSPHRASE, passPhrase);
        newRequestBuilder.addHeader(APIConstants.API_HEADER_TIMESTAMP, timestamp);
        newRequestBuilder.addHeader("X-VERSION", "default"); // just for dev test

        // Build new request after adding the necessary authentication information
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }

    /**
     * Generates signature info.
     *
     * @param request   The HTTP request.
     * @param apiSecret API secret.
     * @param timestamp Timestamp.
     * @return THe signature.
     */
    public static String genSignature(Request request, String apiSecret, String timestamp) {
        String endpoint = request.url().encodedPath();
        String requestUriParams = request.url().query();
        String requestBody = getRequestBody(request);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timestamp);
        stringBuilder.append(request.method());
        stringBuilder.append(endpoint);

        stringBuilder.append((requestUriParams == null || requestUriParams.isEmpty()) ? "" : "?" + requestUriParams);
        stringBuilder.append((requestBody == null || requestBody.isEmpty() ? "" : "" + requestBody));
        String originToSign = stringBuilder.toString();

        String signature = calculateSignature(apiSecret, originToSign);

        LOGGER.debug("originToSign={}", originToSign);
        LOGGER.debug("method={},endpoint={}", request.method(), endpoint);
        LOGGER.debug("signature={}", signature);

        return signature;
    }

    /**
     * Get http request body info.
     *
     * @param request The request
     * @return The request body.
     */
    public static String getRequestBody(Request request) {
        if (request.body() == null) {
            return null;
        }
        Buffer buffer = new Buffer();
        try {
            request.body().writeTo(buffer);
        } catch (IOException e) {
            throw new RuntimeException("I/O error fetching request body", e);
        }

        //编码设为UTF-8
        Charset charset = StandardCharsets.UTF_8;
        MediaType contentType = request.body().contentType();
        if (contentType != null) {
            charset = contentType.charset(StandardCharsets.UTF_8);
        }

        return buffer.readString(charset);
    }

    private static String calculateSignature(String apiSecret, String digest) {
        SecretKeySpec signingKey = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        try {
            Mac mac = Mac.getInstance(signingKey.getAlgorithm());
            mac.init(signingKey);
            return Base64.getEncoder().encodeToString(mac.doFinal(digest.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            LOGGER.warn("Could not calculate signature", e);

        }
        return null;
    }

}
