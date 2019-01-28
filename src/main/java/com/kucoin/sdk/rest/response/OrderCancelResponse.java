package com.kucoin.sdk.rest.response;

import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@ToString
public class OrderCancelResponse {

    private Set<String> cancelledOrderIds = new HashSet<>();
}
