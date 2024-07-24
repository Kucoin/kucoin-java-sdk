package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Colt Han
 * @since 2024/7/26
 */
@Data
public class HFMarginOrderResponse {
    /**
     * Order id，a unique identifier pertaining to the order
     */
    private String id;
    /**
     * Trading pair
     */
    private String symbol;
    /**
     * Operation type: DEAL
     */
    private String opType;
    /**
     * Order type
     */
    private String type;
    /**
     * Buy or sell
     */
    private String side;
    /**
     * Order price
     */
    private String price;
    /**
     * Order size
     */
    private String size;
    /**
     * Order amount
     */
    private String funds;
    /**
     * Number of filled funds
     */
    private String dealFunds;
    /**
     * Number of filled transactions
     */
    private String dealSize;
    /**
     * Service fee
     */
    private String fee;
    /**
     * currency used to calculate fees
     */
    private String feeCurrency;
    /**
     * self trade prevention
     */
    private String stp;
    /**
     * Time in force
     */
    private String timeInForce;
    /**
     * Is it post only?
     */
    private Boolean postOnly;
    /**
     * Is it a hidden order?
     */
    private Boolean hidden;
    /**
     * Is it an iceberg order?
     */
    private Boolean iceberg;
    /**
     * Visible size of iceberg order in order book.
     */
    private String visibleSize;
    /**
     * A GTT timeInForce that expires in n seconds
     */
    private Long cancelAfter;
    /**
     * Source of orders
     */
    private String channel;
    /**
     * Identifier created by the client
     */
    private String clientOid;
    /**
     * Order description
     */
    private String remark;
    /**
     * Order identifier
     */
    private String tags;
    /**
     * Order status: true-The status of the order isactive; false-The status of the order isdone
     */
    private Boolean active;
    /**
     * Whether to enter the orderbook: true: enter the orderbook; false: not enter the orderbook
     */
    private Boolean inOrderBook;
    /**
     * Are there any cancellation records pertaining to the order?
     */
    private Boolean cancelExist;
    /**
     * order creation time
     */
    private Long createdAt;
    /**
     * Last update time of order
     */
    private Long lastUpdatedAt;
    /**
     * Transaction type: MARGIN_TRADE - cross margin trading order, MARGIN_ISOLATED_TRADE - isolated margin trading order
     */
    private String tradeType;
}
