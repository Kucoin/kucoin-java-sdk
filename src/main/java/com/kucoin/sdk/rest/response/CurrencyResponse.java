/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * Created by devin@kucoin.com on 2018-12-27.
 */
@Data
public class CurrencyResponse {
    private String fullName;

    private String currency;

    private String name;

    private int precision;
}
