package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by Reeta on 2019-05-20
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubUserInfoResponse {

    private String userId;

    private String subName;

    private String remarks;
}
