package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
@Data
public class HFMarginTransactionRecord {
    /**
     * Id of transaction detail
     */
    private Long id;
    /**
     * Trading pair
     */
    private String symbol;
    /**
     * Trade Id
     */
    private Long tradeId;
    /**
     * Order Id
     */
    private String orderId;
    /**
     * Counterparty order Id
     */
    private String counterOrderId;
    /**
     * Buy or sell
     */
    private String side;
    /**
     * Liquidity type: taker or maker
     */
    private String liquidity;
    /**
     * Whether or not to forcefully process as taker
     */
    private Boolean forceTaker;
    /**
     * Order price
     */
    private String price;
    /**
     * Order size
     */
    private String size;
    /**
     * Turnover
     */
    private String funds;
    /**
     * Service fee
     */
    private String fee;
    /**
     * Fee rate
     */
    private String feeRate;
    /**
     * currency used to calculate fees
     */
    private String feeCurrency;
    /**
     * Take Profit and Stop Loss type, currently HFT does not support the Take Profit and Stop Loss type, so it is empty
     */
    private String stop;
    /**
     * Trade type: MARGIN_TRADE - cross margin trade, MARGIN_ISOLATED_TRADE - isolated margin trade
     */
    private String tradeType;
    /**
     * Order type: limit or market
     */
    private String type;
    /**
     * Transaction(Creation) time
     */
    private Long createdAt;
}
