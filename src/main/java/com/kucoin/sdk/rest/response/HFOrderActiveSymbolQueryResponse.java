package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class HFOrderActiveSymbolQueryResponse {
    private List<String> symbols;
}
