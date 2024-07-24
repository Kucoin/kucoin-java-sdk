package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author Colt Han
 * @since 2024/7/25
 */
@Data
@Builder
public class HFMarginOrderCreateRequest {
    /**
     * Client Order Id，unique identifier created by the user, the use of UUID is recommended
     */
    private String clientOid;
    /**
     * buy or sell
     */
    private String side;
    /**
     * symbol
     */
    private String symbol;
    /**
     * type
     */
    private String type;
    /**
     * self trade prevention is divided into four strategies: CN, CO, CB , and DC
     */
    private String stp;
    /**
     * true-isolated margin ,false-cross margin. defult as false
     */
    private Boolean isIsolated;
    /**
     * When Margin HFTrading Account has inefficient balance,
     * our system autoborrows inefficient assets and opens positions according to the lowest market interest rate.
     */
    private Boolean autoBorrow;
    /**
     * AutoPay allows returning borrowed assets when you close a position.
     * Our system automatically triggers the repayment and the maximum repayment amount equals to the filled-order amount.
     */
    private Boolean autoRepay;
    /**
     * Specify quantity for currency
     * For limit order: Specify quantity for currency
     * For market order: (Select one out of two: size or funds)
     */
    private String size;

    /* Additional Request Parameters Required by limit Orders */
    /**
     * Specify price for currency
     */
    private String price;
    /**
     * Order timing strategy GTC, GTT, IOC, FOK (The default is GTC)
     */
    private String timeInForce;
    /**
     * Cancel after n seconds，the order timing strategy is GTT
     */
    private Long cancelAfter;
    /**
     * passive order labels, this is disabled when the order timing strategy is IOC or FOK
     */
    private Boolean postOnly;
    /**
     * Hidden or not (not shown in order book)
     */
    private Boolean hidden;
    /**
     * Whether or not only visible portions of orders are shown in iceberg orders
     */
    private Boolean iceberg;
    /**
     * Maximum visible quantity in iceberg orders
     */
    private String visibleSize;

    /* Additional request parameters required by market orders */
    /**
     * (Select one out of two: size or funds)
     */
    private String funds;

}
