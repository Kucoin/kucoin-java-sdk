package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDetailV3Response extends CurrencyResponse {
    private Boolean isMarginEnabled;
    private Boolean isDebitEnabled;
    private List<ApiCurrencyDetailChainPropertyResponseV2> chains;
}
