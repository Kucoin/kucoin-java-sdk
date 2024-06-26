package com.kucoin.sdk.rest.request;

import com.kucoin.sdk.enums.LendingStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author blazetan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RedeemQueryRequest extends PageRequest {

    private String currency;

    /**
     * Redemption order number
     */
    private String redeemOrderNo;

    /**
     * DONE-completed; PENDING-settling
     */
    private LendingStatusEnum status;

    private RedeemQueryRequest(Builder builder) {
        setCurrentPage(builder.currentPage);
        setPageSize(builder.pageSize);
        setCurrency(builder.currency);
        setRedeemOrderNo(builder.redeemOrderNo);
        setStatus(builder.status);
    }

    public Map<String, Object> getMapParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("currency", currency);
        if(redeemOrderNo != null) {
            params.put("redeemOrderNo", redeemOrderNo);
        }
        params.put("status", status.name());
        params.put("currentPage", this.getCurrentPage());
        params.put("pageSize", this.getPageSize());
        return params;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private int currentPage = 1;
        private int pageSize = 10;
        private String currency;
        private String redeemOrderNo;
        private LendingStatusEnum status;

        public Builder() {
        }

        public Builder currentPage(int val) {
            currentPage = val;
            return this;
        }

        public Builder pageSize(int val) {
            pageSize = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public Builder redeemOrderNo(String val) {
            redeemOrderNo = val;
            return this;
        }

        public Builder status(LendingStatusEnum val) {
            status = val;
            return this;
        }

        public RedeemQueryRequest build() {
            return new RedeemQueryRequest(this);
        }
    }
}
