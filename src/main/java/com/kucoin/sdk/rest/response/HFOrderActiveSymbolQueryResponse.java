package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.util.List;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName HFOrderActiveSymbolQueryResponse.java
 * @Description
 * @createTime 2023/05/23日 16:29:00
 */
@Data
public class HFOrderActiveSymbolQueryResponse {
    private List<String> symbols;
}
