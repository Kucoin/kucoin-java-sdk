package com.kucoin.sdk.websocket.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class StopOrderEvent extends AdvancedOrderEvent {

    private String stop;

    private BigDecimal stopPrice;

    private Boolean triggerSuccess;
}
