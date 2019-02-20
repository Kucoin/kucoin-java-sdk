/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interceptor;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.kucoin.sdk.constants.APIConstants;
import com.kucoin.sdk.exception.KucoinApiException;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

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
     * @param apiKey The API key.
     * @param secret The API secret.
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
        if (Strings.isNullOrEmpty(this.apiKey))
            throw new KucoinApiException("Missing " + APIConstants.USER_API_KEY + humanMessage);
        if (Strings.isNullOrEmpty(this.secret))
            throw new KucoinApiException("Missing " + APIConstants.USER_API_SECRET + humanMessage);
        if (Strings.isNullOrEmpty(this.passPhrase))
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
     * @param request The HTTP request.
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

        stringBuilder.append((StringUtils.isBlank(requestUriParams) ? "" : "?" + requestUriParams));
        stringBuilder.append((StringUtils.isBlank(requestBody) ? "" : "" + requestBody));
        String originToSign = stringBuilder.toString();

        String signature = Base64.encodeBase64String(HmacUtils.hmacSha256(apiSecret, originToSign));

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
        Charset charset = Charset.forName("UTF-8");
        MediaType contentType = request.body().contentType();
        if (contentType != null) {
            charset = contentType.charset(Charset.forName("UTF-8"));
        }

        return buffer.readString(charset);
    }
}
