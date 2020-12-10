/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowRecordQueryRequest {

    private String currency;

    Integer pageSize = 10;

    Integer currentPage = 1;
}
