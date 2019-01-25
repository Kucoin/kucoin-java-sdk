

package com.kucoin.sdk.exception;

/**
 * Created by zicong.lu on 2018/12/14.
 */
public class KucoinApiException extends RuntimeException {

    private String code;

    public KucoinApiException(String message) {
        super(message);
    }

    public KucoinApiException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "KucoinApiException{" +
                "code='" + getCode() + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}
