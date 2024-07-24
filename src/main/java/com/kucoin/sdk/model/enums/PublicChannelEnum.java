/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.model.enums;

import com.kucoin.sdk.constants.APIConstants;
import lombok.Getter;

/**
 * Created by chenshiwei on 2019/1/19.
 */
@Getter
public enum PublicChannelEnum {

    TICKER(APIConstants.API_TICKER_TOPIC_PREFIX),

    CANDLES(APIConstants.API_CANDLES_TOPIC_PREFIX),

    LEVEL2(APIConstants.API_LEVEL2_TOPIC_PREFIX),

    LEVEL2_DEPTH5(APIConstants.API_DEPTH5_LEVEL2_TOPIC_PREFIX),

    LEVEL2_DEPTH50(APIConstants.API_DEPTH50_LEVEL2_TOPIC_PREFIX),

    MATCH(APIConstants.API_MATCH_TOPIC_PREFIX),

    @Deprecated
    LEVEL3(APIConstants.API_LEVEL3_TOPIC_PREFIX),

    LEVEL3_V2(APIConstants.API_LEVEL3_V2_TOPIC_PREFIX),

    SNAPSHOT(APIConstants.API_SNAPSHOT_TOPIC_PREFIX),

    INDICATOR_INDEX(APIConstants.API_INDICATOR_INDEX_TOPIC_PREFIX),

    INDICATOR_MARKPRICE(APIConstants.API_INDICATOR_MARKPRICE_TOPIC_PREFIX),

    @Deprecated
    MARGIN_FUNDINGBOOK(APIConstants.API_MARGIN_FUNDINGBOOK_TOPIC_PREFIX);

    private String topicPrefix;

    PublicChannelEnum(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
}
