

package com.kucoin.sdk.rest.response;

import com.kucoin.sdk.model.enums.DepositStatusEnum;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by zicong.lu on 2018/12/21.
 */
@Data
public class DepositResponse {

    private String currency;

    private DepositStatusEnum status;

    private String address;

    private String memo;

    private Boolean isInner;

    private BigDecimal amount;

    private BigDecimal fee;

    private String walletTxId;

    private Long createdAt;

    private Long updatedAt;
}
