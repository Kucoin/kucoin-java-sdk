/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kucoin.sdk.model.OrderBook;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by chenshiwei on 2019/1/18.
 */
@Data
@EqualsAndHashCode(callSuper=true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookResponse extends OrderBook {

    private String sequence;

    private long time;

}
