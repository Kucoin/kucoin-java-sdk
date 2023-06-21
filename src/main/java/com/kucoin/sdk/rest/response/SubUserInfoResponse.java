package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by Reeta on 2019-05-20
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubUserInfoResponse {

    private String userId;

    private String uid;

    private String subName;

    private String status;

    private String type;

    private String access;

    private String createdAt;

    private String remarks;

    private List<String> tradeTypes;
}
