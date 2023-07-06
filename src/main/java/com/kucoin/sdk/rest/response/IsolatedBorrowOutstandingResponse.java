package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class IsolatedBorrowOutstandingResponse {
    private String loanId;

    private String symbol;

    private String currency;

    private String liabilityBalance;

    private String principalTotal;

    private String interestBalance;

    private String createdAt;

    private String maturityTime;

    private String period;

    private String repaidSize;

    private String dailyInterestRate;
}
