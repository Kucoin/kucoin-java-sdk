/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import java.io.IOException;

import com.kucoin.sdk.model.enums.PublicChannelEnum;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.event.KucoinEvent;
import com.kucoin.sdk.websocket.event.Level2ChangeEvent;
import com.kucoin.sdk.websocket.event.Level3ChangeEvent;
import com.kucoin.sdk.websocket.event.MatchExcutionChangeEvent;
import com.kucoin.sdk.websocket.event.TickerChangeEvent;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KucoinPublicWSClient {

    /**
     * Subscribe this topic to get the realtime push of BBO changes.
     * The ticker channel provides real-time price updates every time a match happens.
     * It batches updates in case of cascading matches, greatly reducing bandwidth requirements.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onTicker(KucoinAPICallback<KucoinEvent<TickerChangeEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to get Level2 order book data.
     * After the conducts, the system would send the increment change data pushed by websocket to you.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onLevel2Data(KucoinAPICallback<KucoinEvent<Level2ChangeEvent>> callback, String... symbols);

    /**
     * Subscribe this topic to fully get the updata data for orders and trades.
     * The full channel provides real-time updates on orders and trades.
     * These updates can be applied on to a level 3 order book snapshot to maintain an accurate and up-to-date copy of the exchange order book.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onMatchExecutionData(KucoinAPICallback<KucoinEvent<MatchExcutionChangeEvent>> callback, String... symbols);

    /**
     * Subsribe this topic to fully get the updata data for orders and trades.
     * The full channel provides real-time updates on orders and trades.
     * These updates can be applied on to a level 3 order book snapshot to maintain an accurate and up-to-date copy of the exchange order book.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onLevel3Data(KucoinAPICallback<KucoinEvent<Level3ChangeEvent>> callback, String... symbols);

    /**
     * To prevent the TCP link being disconnected by the server, the client side needs to send ping messages to the server to keep alive the link.
     * After the ping message is sent to the server, the system would return a pong message to the client side.
     *
     * @param requestId
     * @return The original request id, or null if sending failed.
     */
    String ping(String requestId);

    /**
     * Unsubscribe from topics you have subscribed to.
     *
     * @param channelEnum
     * @param symbols
     * @return The unsubscribe request UUID, or null if sending failed.
     */
    String unsubscribe(PublicChannelEnum channelEnum, String... symbols);

    /**
     * Close client
     *
     */
    void close() throws IOException;
}
