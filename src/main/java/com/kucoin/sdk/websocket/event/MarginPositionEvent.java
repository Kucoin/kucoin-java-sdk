package com.kucoin.sdk.websocket.event;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author blaze.tan
 */
@Data
public class MarginPositionEvent {

    // =====debt.ratio=====
    private BigDecimal debtRatio;
    private BigDecimal totalDebt;
    private Map<String,BigDecimal> debtList;

    // =====position.status=====
    private String type;


    // =====Public Parameters=====
    private long timestamp;

}
