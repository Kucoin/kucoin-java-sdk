/*
 * Copyright (c) 2019 Mek Global Limited
 */

package com.kucoin.sdk.rest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


@EqualsAndHashCode(callSuper = true)
@Data
public class EarnHoldAssetQueryRequest extends PageRequest {

    /**
     * Product ID
     */
    private String productId;
    /**
     * Product category
     */
    private String productCategory;
    /**
     * Subscription currency
     */
    private String currency;

    private EarnHoldAssetQueryRequest(Builder builder) {
        setCurrentPage(builder.currentPage);
        setPageSize(builder.pageSize);
        setProductId(builder.productId);
        setProductCategory(builder.productCategory);
        setCurrency(builder.currency);
    }

    public static Builder builder(){
        return new Builder();
    }

    public Map<String, Object> getMapParams() {
        Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(productId)) {
            params.put("productId", productId);
        }
        if (StringUtils.isNotBlank(productCategory)) {
            params.put("productCategory", productCategory);
        }
        if (StringUtils.isNotBlank(currency)) {
            params.put("currency", currency);
        }
        params.put("currentPage", this.getCurrentPage());
        params.put("pageSize", this.getPageSize());
        return params;
    }

    public static final class Builder {
        private int currentPage = 1;
        private int pageSize = 10;
        private String productId;
        private String productCategory;
        private String currency;

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

        public Builder productId(String val) {
            productId = val;
            return this;
        }

        public Builder productCategory(String val) {
            productCategory = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public EarnHoldAssetQueryRequest build() {
            return new EarnHoldAssetQueryRequest(this);
        }
    }
}
