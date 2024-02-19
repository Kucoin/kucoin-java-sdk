package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EtfInfoResponse {

    private String currency;

    private BigDecimal netAsset;

    private String targetLeverage;

    private String actualLeverage;

    private String assetsUnderManagement;

    private String basket;

}
