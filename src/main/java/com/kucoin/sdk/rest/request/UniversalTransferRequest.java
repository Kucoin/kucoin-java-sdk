package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author blazetan
 */
@Builder
@Data
public class UniversalTransferRequest {

    /**
     * Unique order id created by users to identify their orders, e.g. UUID, with a maximum length of 128 bits
     */
    private String clientOid;

    private String currency;

    /**
     * Transfer amount, the amount is a positive integer multiple of the currency precision.
     */
    private BigDecimal amount;

    /**
     * Transfer out UserId， This is required when transferring sub-account to master-account. It is optional for internal transfers.
     */
    private String fromUserId;

    /**
     * Account type：MAIN、TRADE、CONTRACT、MARGIN、ISOLATED、TRADE_HF、MARGIN_V2、ISOLATED_V2
     */
    private String fromAccountType;

    /**
     * Symbol, required when the account type is ISOLATED or ISOLATED_V2, for example: BTC-USDT
     */
    private String fromAccountTag;

    /**
     * Transfer type：INTERNAL(Transfer within account)、PARENT_TO_SUB(Transfer from master-account to sub-account)，SUB_TO_PARENT(Transfer from sub-account to master-account)
     */
    private String type;

    /**
     * Transfer in UserId， This is required when transferring master-account to sub-account. It is optional for internal transfers.
     */
    private String toUserId;

    /**
     * Account type：MAIN、TRADE、CONTRACT、MARGIN、ISOLATED、TRADE_HF、MARGIN_V2、ISOLATED_V2
     */
    private String toAccountType;

    /**
     * Symbol, required when the account type is ISOLATED or ISOLATED_V2, for example: BTC-USDT
     */
    private String toAccountTag;

}
