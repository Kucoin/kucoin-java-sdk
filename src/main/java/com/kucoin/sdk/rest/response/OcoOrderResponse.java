/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OcoOrderResponse {

    private String orderId;

    private String symbol;

    private String clientOid;

    /**
     * @see com.kucoin.sdk.model.enums.OcoOrderStatusEnum
     */
    private String status;

    private Date orderTime;

}
