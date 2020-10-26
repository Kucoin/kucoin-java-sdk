package com.kucoin.sdk.websocket.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class StopOrderEvent extends AdvancedOrderEvent {

    private String stop;

    private BigDecimal stopPrice;

    private Boolean triggerSuccess;
}
