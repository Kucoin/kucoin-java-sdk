package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyDetailV3Response {
    /**
     * A unique currency code that will never change
     */
    private String currency;
    /**
     * Currency name, will change after renaming
     */
    private String name;
    /**
     * Full name of a currency, will change after renaming
     */
    private String fullName;
    /**
     * Currency precision
     */
    private Long precision;
    /**
     * Number of block confirmations
     */
    private Long confirms;
    /**
     * Contract address
     */
    private String contractAddress;
    /**
     * Support margin or not
     */
    private Boolean isMarginEnabled;
    /**
     * Support debit or not
     */
    private Boolean isDebitEnabled;
    /**
     * chain list
     */
    private List<Chain> chains;

    @Data
    public static class Chain {
        /**
         * chain of currency
         */
        private String chainId;
        /**
         * chain name of currency
         */
        private String chainName;
        /**
         * Minimum deposit amount
         */
        private String depositMinSize;
        /**
         * deposit fee rate (some currencies have this param, the default is empty)
         */
        private String depositFeeRate;
        /**
         * Minimum withdrawal amount
         */
        private String withdrawalMinSize;
        /**
         * Minimum fees charged for withdrawal
         */
        private String withdrawalMinFee;
        /**
         * withdraw fee rate
         */
        private String withdrawFeeRate;
        /**
         * withdraw max fee(some currencies have this param, the default is empty)
         */
        private String withdrawMaxFee;
        /**
         * Support withdrawal or not
         */
        private Boolean isWithdrawEnabled;
        /**
         * Support deposit or not
         */
        private Boolean isDepositEnabled;
        /**
         * The number of blocks (confirmations) for advance on-chain verification
         */
        private Long preConfirms;
        /**
         * Number of block confirmations
         */
        private Long confirms;
        /**
         * Contract address
         */
        private String contractAddress;
    }
}
