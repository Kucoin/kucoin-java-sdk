package com.kucoin.sdk.rest.request;

import lombok.Data;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFOrderMultiCreateRequest.java
 * @Description
 * @createTime 2023/05/23日 15:45:00
 */
@Data
public class HFOrderMultiCreateRequest {
    private List<HFOrderCreateRequest> orderList;
}
