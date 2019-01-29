/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * Created by tao.mao on 2019/1/2.
 */
@Data
public class AccountCreateRequest {

    /**
     * Currency code
     */
    private String currency;
    /**
     * Account type，"main" or "trade"
     */
    private String type;
}
