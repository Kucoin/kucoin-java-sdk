package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFDoneOrderQueryResponse.java
 * @Description
 * @createTime 2023/05/23日 16:34:00
 */
@Data
public class HFDoneOrderQueryResponse {
    private Long lastId;

    private List<HFOrderResponse> items;

}
