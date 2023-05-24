/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.io.IOException;
import java.util.List;

import com.kucoin.sdk.rest.response.DepositAddressResponse;
import com.kucoin.sdk.rest.response.DepositResponse;
import com.kucoin.sdk.rest.response.Pagination;

/**
 * Created by chenshiwei on 2019/1/9.
 */
public interface DepositAPI {

    /**
     * Create deposit address of currency for deposit. You can just create one deposit address.
     *
     * @param currency the code of the currency
     * @param chain    the chain name of currency
     * @return Details of a deposit address.
     */
    DepositAddressResponse createDepositAddress(String currency, String chain) throws IOException;

    /**
     * Get deposit address of currency for deposit.
     * If return data is null , you may need create a deposit address first.
     *
     * @param currency the code of the currency
     * @return Details of a deposit address.
     */
    DepositAddressResponse getDepositAddress(String currency, String chain) throws IOException;

    /**
     * Get deposit page list.
     *
     * @param currency    Currency
     * @param startAt     Start time. unix timestamp calculated in milliseconds, the creation time queried shall posterior to the start time.
     * @param endAt       End time. unix timestamp calculated in milliseconds, the creation time queried shall prior to the end time.
     * @param status      Status. Available value: PROCESSING, SUCCESS, and FAILURE
     * @param currentPage
     * @param pageSize
     * @return A page of deposits.
     */
    Pagination<DepositResponse> getDepositPageList(String currency, long startAt, long endAt,
                                                   String status, int currentPage, int pageSize) throws IOException;

    /**
     * Get all deposit addresses for the currency you intend to deposit.
     * If the returned data is empty, you may need to create a deposit address first.
     *
     * @param currency the code of the currency
     * @return List of deposit addresses.
     */
    List<DepositAddressResponse> getDepositAddresses(String currency) throws IOException;

    /**
     * Request via this endpoint to get the V1 historical deposits list on KuCoin.
     *
     * @param currency    [Optional] Currency
     * @param startAt     [Optional] Start time. unix timestamp calculated in milliseconds, the creation time queried shall posterior to the start time.
     * @param endAt       [Optional] End time. unix timestamp calculated in milliseconds, the creation time queried shall prior to the end time.
     * @param status      [Optional] Status. Available value: PROCESSING, SUCCESS, and FAILURE
     * @param currentPage
     * @param pageSize
     * @return A page of deposits.
     */
    Pagination<DepositResponse> getHistDepositPageList(String currency, String status, long startAt, long endAt, int currentPage, int pageSize) throws IOException;

}
