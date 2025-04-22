/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.request.WithdrawApplyRequest;
import com.kucoin.sdk.rest.request.WithdrawApplyV3Request;
import com.kucoin.sdk.rest.response.*;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface WithdrawalAPI {

    /**
     * get withdrawal quotas
     *
     * @param currency currency. e.g. BTC
     * @return The withdrawal quotas.
     */
    WithdrawQuotaResponse getWithdrawQuotas(String currency, String chain) throws IOException;

    /**
     * apply withdraw
     *
     * @param request
     * @return A response containing the withdrawal id.
     */
    WithdrawApplyResponse applyWithdraw(WithdrawApplyRequest request) throws IOException;

    /**
     * apply withdraw(v3)
     *
     * @param request
     * @return A response containing the withdrawal id.
     */
    WithdrawApplyV3Response applyWithdrawV3(WithdrawApplyV3Request request) throws IOException;

    /**
     * Only withdrawals in processing status could be cancelled.
     *
     * @param withdrawalId unique identity for withdrawal order
     */
    void cancelWithdraw(String withdrawalId) throws IOException;

    /**
     * check withdrawals list
     *
     * @param currency    Currency code
     * @param status      Status. Available value: PROCESSING, WALLET_PROCESSING, SUCCESS, and FAILURE
     * @param startAt     Start time. unix timestamp calculated in milliseconds, the creation time queried shall posterior to the start time.
     * @param endAt       End time. unix timestamp calculated in milliseconds, the creation time queried shall prior to the end time.
     * @param currentPage
     * @param pageSize
     * @return A page of withdrawals.
     */
    Pagination<WithdrawResponse> getWithdrawList(String currency, String status, long startAt,
                                                 long endAt, int currentPage, int pageSize) throws IOException;

    /**
     * List of KuCoin V1 historical withdrawals.
     *
     * @param currency    [Optional] Currency code
     * @param status      [Optional] Status. Available value: PROCESSING, WALLET_PROCESSING, SUCCESS, and FAILURE
     * @param startAt     [Optional] Start time. unix timestamp calculated in milliseconds, the creation time queried shall posterior to the start time.
     * @param endAt       [Optional] End time. unix timestamp calculated in milliseconds, the creation time queried shall prior to the end time.
     * @param currentPage [Optional]
     * @param pageSize    [Optional]
     * @return A page of withdrawals.
     */
    Pagination<WithdrawResponse> getHistWithdrawPageList(String currency, String status, long startAt,
                                                         long endAt, int currentPage, int pageSize) throws IOException;
}
