package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class SubUserCreateRequest {
    String subName;

    String password;

    String access;

    String remarks;
}
