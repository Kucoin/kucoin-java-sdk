package com.kucoin.sdk.model.enums;

import lombok.Getter;

/**
 * Created by tao.mao on 2021/3/4.
 */
@Getter
public enum ApiKeyVersionEnum {

    V1(1), V2(2);

    private Integer version;

    ApiKeyVersionEnum(Integer version) {
        this.version = version;
    }
}
