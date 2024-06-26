package com.kucoin.sdk.rest.response;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class SubApiKeyResponse {

    private String subName;

    private String remark;

    private String apiKey;

    /**
     * returns only on creation
     */
    private String apiSecret;

    private String passphrase;

    private String permission;

    private String ipWhitelist;

    private String createdAt;

    private String apiVersion;
}
