package com.kucoin.sdk.rest.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author Colt Han
 * @since 2024/7/24
 */
@Data
@Builder
public class UserLeverageUpdateRequest {
    /**
     * Trading pair. Leave empty for cross margin, or specify for isolated margin
     */
    private String symbol;
    /**
     * New leverage multiplier.
     * Must be greater than 1 and up to two decimal places,
     * and cannot be less than the user's current debt leverage or greater than the system's maximum leverage
     */
    private String leverage;
    /**
     * Whether it is isolated margin. true: yes, false: cross margin. Default: false
     */
    private Boolean isIsolated;

}
