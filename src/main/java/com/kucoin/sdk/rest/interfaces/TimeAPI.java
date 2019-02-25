/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/15.
 */
public interface TimeAPI {

    /**
     * Get the API server time.
     *
     * @return The current server time.
     */
    Long getServerTimeStamp() throws IOException;

}
