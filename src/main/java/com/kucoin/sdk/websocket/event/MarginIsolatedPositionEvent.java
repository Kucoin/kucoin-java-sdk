package com.kucoin.sdk.websocket.event;

import lombok.Data;

import java.util.Map;

/**
 *
 */
@Data
public class MarginIsolatedPositionEvent {
    /**
     * symbol
     */
    private String tag;
    /**
     * Position status
     */
    private String status;
    /**
     * Accumulated principal
     */
    private String accumulatedPrincipal;
    /**
     * Timestamp (milliseconds)
     */
    private Long timestamp;
    /**
     * changeAssets
     */
    Map<String, ChangeAssetEvent> changeAssets;

    @Data
    public static class ChangeAssetEvent {
        /**
         * Total assets
         */
        private String total;
        /**
         * Freeze assets
         */
        private String hold;
        /**
         * Liability principal
         */
        private String liabilityPrincipal;
        /**
         * Debt interest
         */
        private String liabilityInterest;
    }
}
