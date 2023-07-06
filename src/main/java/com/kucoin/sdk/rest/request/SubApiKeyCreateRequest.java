package com.kucoin.sdk.rest.request;

import lombok.Data;

/**
 * @author Jason Yao
 * @version 1.0.0
 */
@Data
public class SubApiKeyCreateRequest {
    String subName;

    String passphrase;

    String remark;

    String permission;

    String ipWhitelist;

    String expire;
}
