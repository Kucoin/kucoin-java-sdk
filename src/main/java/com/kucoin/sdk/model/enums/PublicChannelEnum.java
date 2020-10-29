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

    LEVEL2(APIConstants.API_LEVEL2_TOPIC_PREFIX),

    LEVEL2_DEPTH5(APIConstants.API_DEPTH5_LEVEL2_TOPIC_PREFIX),

    LEVEL2_DEPTH50(APIConstants.API_DEPTH50_LEVEL2_TOPIC_PREFIX),

    MATCH(APIConstants.API_MATCH_TOPIC_PREFIX),

    @Deprecated
    LEVEL3(APIConstants.API_LEVEL3_TOPIC_PREFIX),

    LEVEL3_V2(APIConstants.API_LEVEL3_V2_TOPIC_PREFIX),

    SNAPSHOT(APIConstants.API_SNAPSHOT_PREFIX);


    private String topicPrefix;

    PublicChannelEnum(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
}
