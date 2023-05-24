package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by Reeta on 2019-05-20
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubAccountBalanceResponse {

    private String subUserId;

    private String subName;

    private List<AccountBalancesResponse> tradeAccounts;

    private List<AccountBalancesResponse> mainAccounts;

    private List<AccountBalancesResponse> marginAccounts;
}
