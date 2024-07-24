package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Colt Han
 * @since 2024/7/23
 */
@Data
public class OtcLoanAccountResponse {

    /**
     * Account UID
     */
    private String uid;
    /**
     * Margin Currency
     */
    private String marginCcy;
    /**
     * Maintenance Quantity (Calculated with Margin Coefficient)
     */
    private String marginQty;
    /**
     * Margin Coefficient
     */
    private String marginFactor;
    /**
     * Account Type: TRADE - Trading Account TRADE_HF - High-Frequency Trading Account CONTRACT - Futures Account (for Total Futures Equity)
     */
    private String accountType;
    /**
     * If It Is Master Account
     */
    private Boolean isParent;
}
