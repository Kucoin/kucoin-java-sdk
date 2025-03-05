/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Builder
public class WithdrawApplyV3Request {

    /**
     * Currency
     */
    private String currency;

    /**
     * Withdrawal address, ADDRESS (withdrawal address), UID, MAIL (email), PHONE (mobile phone number).
     */
    private String toAddress;

    /**
     * Withdrawal amount, a positive number which is a multiple of the amount precision (fees excluded)
     */
    private BigDecimal amount;

    /**
     * [optional] Address remark. If there’s no remark, it is empty. When you withdraw from other platforms to the KuCoin,
     * you need to fill in memo(tag). If you do not fill memo (tag), your deposit may not be available, please be cautious.
     */
    private String memo;

    /**
     * [optional] Internal withdrawal or not. Default setup: false
     */
    @Builder.Default
    private Boolean isInner = false;

    /**
     * [optional] Remark
     */
    private String remark;

    /**
     * The chain of currency. For a currency with multiple chains, it is recommended to specify chain parameter instead
     * of using the default chain; you can query the chainId through the response of the GET /api/v3/currencies/{currency} interface.
     */
    private String chain;

    /**
     * Withdrawal fee deduction type: INTERNAL or EXTERNAL or not specified
     * 1. INTERNAL- deduct the transaction fees from your withdrawal amount
     * 2. EXTERNAL- deduct the transaction fees from your main account
     * 3. If you don't specify the feeDeductType parameter, when the balance in your main account is sufficient to support the withdrawal,
     * the system will initially deduct the transaction fees from your main account. But if the balance in your main account is
     * not sufficient to support the withdrawal, the system will deduct the fees from your withdrawal amount.
     * For example: Suppose you are going to withdraw 1 BTC from the KuCoin platform (transaction fee: 0.0001BTC),
     * if the balance in your main account is insufficient, the system will deduct the transaction fees from your withdrawal amount.
     * 1In this case, you will be receiving 0.9999BTC.
     * 4. Lightning Network can only deduct internally
     */
    private String feeDeductType;

    /**
     * Withdrawal type, ADDRESS (withdrawal address), UID, MAIL (email), PHONE (mobile phone number). Note: If you withdraw by uid/mail/phone,
     * there will have rate limited: 3 times/10 seconds, 50 times/24 hours (calculated on a rolling basis based on the first request time)
     */
    private String withdrawType;
}
