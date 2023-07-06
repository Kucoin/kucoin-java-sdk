package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
@Builder
public class HFOrderCreateRequest {
    private String clientOid;
    private String symbol;
    private String type;
    private String side;
    private String stp;
    private String tags;
    private String remark;
    private BigDecimal price;
    private BigDecimal size;
    private String timeInForce;
    private Long cancelAfter;
    private Boolean postOnly;
    private Boolean hidden;
    private Boolean iceberg;
    private String visibleSize;
    private String funds;
}
