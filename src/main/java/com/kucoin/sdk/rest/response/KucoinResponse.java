/**
 * Copyright 2019 Mek Global Limited.
 */

package com.kucoin.sdk.rest.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zicong.lu on 2018/12/14.
 */
@Data
public class KucoinResponse<R> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS_CODE = "200000";
    protected String code;
    protected String msg;

    private R data;

    public boolean isSuccessful() {
        return SUCCESS_CODE.equals(this.code);
    }

}
