/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OcoOrderDetailResponse extends OcoOrderResponse {

    private List<OcoSubOrderInfo> orders;

    @Data
    public static class OcoSubOrderInfo{
        private String id;
        private String symbol;
        private String side;
        private BigDecimal price;
        private BigDecimal stopPrice;
        private BigDecimal size;
        /**
         * @see com.kucoin.sdk.model.enums.StopOrderStatusEnum
         */
        private String status;
    }

}
