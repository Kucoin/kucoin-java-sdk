package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Colt Han
 * @since 2024/7/23
 */
@Data
public class OtcLoanLoanResponse {
    /**
     * Master account UID
     */
    private String parentUid;
    /**
     * Loan Orders
     */
    private List<Order> orders;
    /**
     * LTV (Loan-to-Value Ratio)
     */
    private Ltv ltv;
    /**
     * Total Margin Amount (USDT)
     */
    private String totalMarginAmount;
    /**
     * Total Maintenance Margin for Restricted Transfers (USDT)
     */
    private String transferMarginAmount;
    /**
     * Margin
     */
    private List<Margin> margins;

    @Data
    public static class Order {
        /**
         * Loan Order ID
         */
        private String orderId;
        /**
         * Loan Currency Type
         */
        private String currency;
        /**
         * Principal to Be Repaid
         */
        private String principal;
        /**
         * Interest to Be Repaid
         */
        private String interest;
    }

    @Data
    public static class Ltv {
        /**
         * LTV of Restricted Transfers to Funding Account
         */
        private String transferLtv;
        /**
         * LTV of Reduce Only (Restricted Open Positions)
         */
        private String onlyClosePosLtv;
        /**
         * LTV of Delayed Liquidation
         */
        private String delayedLiquidationLtv;
        /**
         * LTV of Instant Liquidation
         */
        private String instantLiquidationLtv;
        /**
         * Current LTV
         */
        private String currentLtv;
    }

    @Data
    public static class Margin {
        /**
         * Margin Currency
         */
        private String marginCcy;
        /**
         * Maintenance Quantity (Calculated with Margin Coefficient)
         */
        private String marginQty;
        /**
         * Margin Coefficient return real time margin discount rate to USDT
         */
        private String marginFactor;
    }
}
