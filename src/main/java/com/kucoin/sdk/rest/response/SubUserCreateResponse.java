package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class SubUserCreateResponse {
    private String uid;

    private String subName;

    private String remarks;

    private String access;
}
