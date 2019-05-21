package com.kucoin.sdk.rest.interfaces;

import com.kucoin.sdk.exception.KucoinApiException;
import com.kucoin.sdk.rest.response.SubUserInfoResponse;

import java.io.IOException;
import java.util.List;

/**
 * Created by Reeta on 2019-05-20
 */
public interface UserAPI {

    /**
     * Get a list of sub-users.
     * <p>
     * You can create sub-users on web end.
     *
     * @return The sub-users.
     * @throws IOException        on socket errors.
     * @throws KucoinApiException when errors are returned from the exchange.
     */
    List<SubUserInfoResponse> listSubUsers() throws IOException;
}
