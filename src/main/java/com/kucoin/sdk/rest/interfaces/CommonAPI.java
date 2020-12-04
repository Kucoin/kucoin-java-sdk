/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.KucoinResponse;
import com.kucoin.sdk.rest.response.ServiceStatusResponse;
import retrofit2.Call;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public interface CommonAPI {

    /**
     * Get the service status
     *
     * @return The current server status.
     */
    ServiceStatusResponse getServerStatus() throws IOException;

}
