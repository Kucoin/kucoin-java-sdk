package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName IsolatedBorrowRepaidResponse.java
 * @Description
 * @createTime 2023/05/17日 11:54:00
 */
@Data
public class IsolatedBorrowRepaidResponse {

    private String loanId;

    private String symbol;

    private String currency;

    private String principalTotal;

    private String interestBalance;

    private String repaidSize;

    private String createdAt;

    private String period;

    private String dailyInterestRate;

    private String repayFinishAt;

}
