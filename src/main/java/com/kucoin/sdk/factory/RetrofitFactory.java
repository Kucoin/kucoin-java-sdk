/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.factory;

import com.kucoin.sdk.KucoinObjectMapper;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public class RetrofitFactory {

    private static volatile Retrofit publicRetorfit;

    private static volatile Retrofit authRetorfit;

    private static final Converter.Factory jacksonConverterFactory = JacksonConverterFactory.create(KucoinObjectMapper.INSTANCE);

    public static Retrofit getPublicRetorfit(String baseUrl) {
        synchronized (RetrofitFactory.class) {
            if (publicRetorfit == null) {
                publicRetorfit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(jacksonConverterFactory)
                        .client(HttpClientFactory.getPublicClient())
                        .build();
            }
            return publicRetorfit;
        }
    }

    public static Retrofit getAuthRetorfit(String baseUrl, String apiKey, String secret, String passPhrase) {
        synchronized (RetrofitFactory.class) {
            if (authRetorfit == null) {
                authRetorfit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(jacksonConverterFactory)
                        .client(HttpClientFactory.getAuthClient(apiKey, secret, passPhrase))
                        .build();
            }
            return authRetorfit;
        }
    }

}
