/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.OtcLoanAccountResponse;
import com.kucoin.sdk.rest.response.OtcLoanLoanResponse;

import java.io.IOException;

/**
 * Institutional Loans are loans provided by KuCoin to Institutional and VIP users.
 * Clients have the freedom to choose their desired borrowing leverage, with access to even larger borrowing amounts.
 * Both margin and loaned assets can be used for trading, providing a greater degree of flexibility.
 * <a href="https://www.kucoin.com/docs/rest/vip-lending/introduction">ApiDoc</a>
 * <p>
 *
 * @author Colt Han
 * @since 2024/7/23
 */
public interface VipLendingAPI {

    /**
     * Get information on off-exchange funding and loans
     * <a href="https://www.kucoin.com/docs/rest/vip-lending/get-information-on-off-exchange-funding-and-loans">ApiDoc</a>
     *
     * @return Accounts that are currently involved in loans.
     * @throws IOException IOException
     */
    OtcLoanLoanResponse getOtcLoanLoan() throws IOException;

    /**
     * Get information on accounts involved in off-exchange loans
     * <a href="https://www.kucoin.com/docs/rest/vip-lending/get-information-on-accounts-involved-in-off-exchange-loans">ApiDoc</a>
     *
     * @return Accounts that are currently involved in off-exchange funding and loans.
     * @throws IOException IOException
     */
    OtcLoanAccountResponse getOtcLoanAccounts() throws IOException;

}
