package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class IsolatedSymbolResponse {
    private String symbol;

    private String symbolName;

    private String baseCurrency;

    private String quoteCurrency;

    private Integer maxLeverage;

    private String flDebtRatio;

    private Boolean tradeEnable;

    private String autoRenewMaxDebtRatio;

    private Boolean baseBorrowEnable;

    private Boolean quoteBorrowEnable;

    private Boolean baseTransferInEnable;

    private Boolean quoteTransferInEnable;
}
