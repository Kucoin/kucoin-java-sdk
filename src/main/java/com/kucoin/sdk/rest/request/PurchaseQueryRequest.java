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
public class PurchaseQueryRequest extends PageRequest {

    private String currency;

    /**
     * Subscription order number
     */
    private String purchaseOrderNo;

    /**
     * DONE-completed; PENDING-settling
     */
    private LendingStatusEnum status;

    public Map<String, Object> getMapParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("currency", currency);
        if(purchaseOrderNo != null) {
            params.put("purchaseOrderNo", purchaseOrderNo);
        }
        params.put("status", status.name());
        params.put("currentPage", this.getCurrentPage());
        params.put("pageSize", this.getPageSize());
        return params;
    }

    private PurchaseQueryRequest(Builder builder) {
        setCurrentPage(builder.currentPage);
        setPageSize(builder.pageSize);
        setCurrency(builder.currency);
        setPurchaseOrderNo(builder.purchaseOrderNo);
        setStatus(builder.status);
    }

    public static Builder builder(){
        return new Builder();
    }

    public static final class Builder {
        private int currentPage = 1;
        private int pageSize = 10;
        private String currency;
        private String purchaseOrderNo;
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

        public Builder purchaseOrderNo(String val) {
            purchaseOrderNo = val;
            return this;
        }

        public Builder status(LendingStatusEnum val) {
            status = val;
            return this;
        }

        public PurchaseQueryRequest build() {
            return new PurchaseQueryRequest(this);
        }
    }
}
