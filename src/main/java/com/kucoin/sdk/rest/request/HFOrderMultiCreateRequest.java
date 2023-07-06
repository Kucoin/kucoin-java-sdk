package com.kucoin.sdk.rest.request;

import lombok.Data;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFOrderMultiCreateRequest {
    private List<HFOrderCreateRequest> orderList;
}
