package com.kucoin.sdk.rest.response;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author blazetan
 */
@Data
public class MarginAccountResponse {

    private BigDecimal debtRatio;
    private BigDecimal totalLiabilityOfQuoteCurrency;
    private BigDecimal totalAssetOfQuoteCurrency;
    private List<Account> accounts;
    private String status;

    @Data
    public static class Account {
        private String currency;
        private BigDecimal total;
        private BigDecimal liability;
        private BigDecimal available;
        private BigDecimal maxBorrowSize;
        private BigDecimal hold;
        private boolean transferInEnabled;
        private boolean borrowEnabled;
    }
}
