/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.constants;

/**
 * Created by zicong.lu on 2018/12/14.
 */
public class APIConstants {

    public static final String API_BASE_URL = "https://openapi-v2.kucoin.com/";

    public static final String USER_API_KEY = "KC-API-KEY";
    public static final String USER_API_SECRET = "KC-API-SECRET";
    public static final String USER_API_PASSPHRASE = "KC-API-PASSPHRASE";

    public static final String API_HEADER_KEY = "KC-API-KEY";
    public static final String API_HEADER_SIGN = "KC-API-SIGN";
    public static final String API_HEADER_PASSPHRASE = "KC-API-PASSPHRASE";
    public static final String API_HEADER_TIMESTAMP = "KC-API-TIMESTAMP";
    public static final String API_HEADER_USER_AGENT = "User-Agent";
    public static final String API_HEADER_KEY_VERSION = "KC-API-KEY-VERSION";

    public static final String API_TICKER_TOPIC_PREFIX = "/market/ticker:";
    public static final String API_LEVEL2_TOPIC_PREFIX = "/market/level2:";
    public static final String API_DEPTH5_LEVEL2_TOPIC_PREFIX = "/spotMarket/level2Depth5:";
    public static final String API_DEPTH50_LEVEL2_TOPIC_PREFIX = "/spotMarket/level2Depth50:";
    public static final String API_MATCH_TOPIC_PREFIX = "/market/match:";
    @Deprecated
    public static final String API_LEVEL3_TOPIC_PREFIX = "/market/level3:";
    public static final String API_LEVEL3_V2_TOPIC_PREFIX = "/spotMarket/level3:";
    @Deprecated
    public static final String API_ACTIVATE_TOPIC_PREFIX = "/market/level3:";
    public static final String API_BALANCE_TOPIC_PREFIX = "/account/balance";
    public static final String API_ADVANCED_ORDER_TOPIC_PREFIX = "/spotMarket/advancedOrders";
    public static final String API_ORDER_TOPIC_PREFIX = "/spotMarket/tradeOrders";
    public static final String API_SNAPSHOT_PREFIX = "/market/snapshot:";
}
