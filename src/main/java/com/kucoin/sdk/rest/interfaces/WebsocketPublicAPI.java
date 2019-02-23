/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.rest.response.WebsocketTokenResponse;

/**
 * Created by chenshiwei on 2019/1/18.
 */
public interface WebsocketPublicAPI {

    /**
     * apply for a public token to create a websocket connection
     *
     * @return A new public API token.
     */
    WebsocketTokenResponse getPublicToken();

}
