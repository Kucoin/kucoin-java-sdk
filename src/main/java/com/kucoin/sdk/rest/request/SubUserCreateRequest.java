package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName SubUserCreateRequest.java
 * @Description
 * @createTime 2023/05/16日 11:51:00
 */
@Data
public class SubUserCreateRequest {
    String subName;

    String password;

    String access;

    String remarks;
}
