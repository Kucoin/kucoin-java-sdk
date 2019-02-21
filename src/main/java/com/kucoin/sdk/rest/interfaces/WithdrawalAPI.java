/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.WithdrawApplyRequest;
import com.kucoin.sdk.rest.response.Pagination;
import com.kucoin.sdk.rest.response.WithdrawApplyResponse;
import com.kucoin.sdk.rest.response.WithdrawQuotaResponse;
import com.kucoin.sdk.rest.response.WithdrawResponse;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface WithdrawalAPI {

    /**
     * get withdrawal quotas
     *
     * @param currency currency. e.g. BTC
     * @return
     */
    WithdrawQuotaResponse getWithdrawQuotas(String currency);

    /**
     * apply withdraw
     *
     * @param request
     * @return
     */
    WithdrawApplyResponse applyWithdraw(WithdrawApplyRequest request);

    /**
     * Only withdrawals in processing status could be cancelled.
     *
     * @param withdrawalId unique identity for withdrawal order
     */
    void cancelWithdraw(String  withdrawalId);

    /**
     * check withdrawals list
     *
     * @param currency    Currency code
     * @param status      Status. Available value: PROCESSING, WALLET_PROCESSING, SUCCESS, and FAILURE
     * @param startAt     Start time. unix timestamp calculated in milliseconds, the creation time queried shall posterior to the start time.
     * @param endAt       End time. unix timestamp calculated in milliseconds, the creation time queried shall prior to the end time.
     * @param currentPage
     * @param pageSize
     * @return
     */
    Pagination<WithdrawResponse> getWithdrawList(String currency, String status, long startAt,
                                                 long endAt, int currentPage, int pageSize);

}
