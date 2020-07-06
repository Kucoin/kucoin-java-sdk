package com.kucoin.sdk.rest.response;

import lombok.Data;

@Data
public class Level3Response {

    private Object[][] bids;

    private Object[][] asks;

    private long sequence;

    private long time;
}
