package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName IsolatedBorrowResponse.java
 * @Description
 * @createTime 2023/05/17日 11:46:00
 */
@Data
public class IsolatedBorrowResponse {
    private String orderId;

    private String currency;

    private String actualBorrowSize;
}
