package com.kucoin.sdk.rest.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author blazetan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InterestQueryV3Request extends PageRequest{

    /**
     * true-isolated, false-cross; default is false
     */
    private boolean isIsolated;

    /**
     * trading pair, mandatory for isolated margin account
     */
    private String symbol;

    private String currency;

    private Long startTime;

    private Long endTime;

    private InterestQueryV3Request(Builder builder) {
        setCurrentPage(builder.currentPage);
        setPageSize(builder.pageSize);
        setIsolated(builder.isIsolated);
        setSymbol(builder.symbol);
        setCurrency(builder.currency);
        setStartTime(builder.startTime);
        setEndTime(builder.endTime);
    }

    public static Builder builder(){
        return new Builder();
    }

    public Map<String, Object> getMapParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("isolated", isIsolated);
        if (symbol != null) {
            params.put("symbol", symbol);
        }
        if (currency != null) {
            params.put("currency", currency);
        }
        if (startTime != null) {
            params.put("startTime", startTime);
        }
        if (endTime != null) {
            params.put("endTime", endTime);
        }
        params.put("currentPage", this.getCurrentPage());
        params.put("pageSize", this.getPageSize());
        return params;
    }


    public static final class Builder {
        private int currentPage = 1;
        private int pageSize = 10;
        private boolean isIsolated;
        private String symbol;
        private String currency;
        private Long startTime;
        private Long endTime;

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

        public Builder isIsolated(boolean val) {
            isIsolated = val;
            return this;
        }

        public Builder symbol(String val) {
            symbol = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public Builder startTime(Long val) {
            startTime = val;
            return this;
        }

        public Builder endTime(Long val) {
            endTime = val;
            return this;
        }

        public InterestQueryV3Request build() {
            return new InterestQueryV3Request(this);
        }
    }
}
