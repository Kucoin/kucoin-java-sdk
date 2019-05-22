/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
public class DepositAddressCreateRequest {

    private String currency;

    private String chain;

}
