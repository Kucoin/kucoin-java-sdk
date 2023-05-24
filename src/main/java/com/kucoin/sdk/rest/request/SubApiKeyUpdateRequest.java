package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 * @ClassName SubApiKeyUpdateRequest.java
 * @Description
 * @createTime 2023/05/16日 11:58:00
 */
@Data
public class SubApiKeyUpdateRequest {
    String subName;

    String apiKey;

    String passphrase;

    String permission;

    String ipWhitelist;

    String expire;
}
