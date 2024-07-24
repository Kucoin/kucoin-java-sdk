/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.model.enums;

import com.kucoin.sdk.constants.APIConstants;
import lombok.Getter;

/**
 * Created by chenshiwei on 2019/1/23.
 */
@Getter
public enum PrivateChannelEnum {

    @Deprecated
    ORDER(APIConstants.API_ACTIVATE_TOPIC_PREFIX),

    ORDER_CHANGE(APIConstants.API_ORDER_TOPIC_PREFIX),
    ORDER_V2_CHANGE(APIConstants.API_ORDER_V2_TOPIC_PREFIX),
    MARGIN_POSITION_CHANGE(APIConstants.API_MARGIN_POSITION_TOPIC_PREFIX),
    MARGIN_ISOLATED_POSITION_CHANGE(APIConstants.API_MARGIN_ISOLATED_POSITION_TOPIC_PREFIX),
    MARGIN_LOAN_CHANGE(APIConstants.API_MARGIN_LOAN_TOPIC_PREFIX),

    ACCOUNT(APIConstants.API_BALANCE_TOPIC_PREFIX),

    ADVANCED_ORDER(APIConstants.API_ADVANCED_ORDER_TOPIC_PREFIX);

    private String topicPrefix;

    PrivateChannelEnum(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }
}
