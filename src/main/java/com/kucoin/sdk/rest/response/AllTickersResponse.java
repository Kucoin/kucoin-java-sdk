package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * Created by chenshiwei on 2019/2/22.
 */
@Data
public class AllTickersResponse {

    private Long time;

    private List<MarketTickerResponse> ticker;

}
