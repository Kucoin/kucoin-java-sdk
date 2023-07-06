/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import com.kucoin.sdk.model.enums.PublicChannelEnum;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.event.*;

import java.io.IOException;

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
     * Subscribe to this topic to get the K-line data of the specified type for the specified symbol.
     *
     * @param callback
     * @param symbolAndType
     * @return
     */
    String onCandles(KucoinAPICallback<KucoinEvent<CandlesEvent>> callback, String symbolAndType);

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
     * Subscribe this topic to get Level2 order book data.
     * After the conducts, the system would send the order book data pushed by websocket to you every 100ms.
     *
     * @param depth    top N data, right now, only 5 and 50 are available.
     * @param callback
     * @param symbols
     * @return
     */
    String onLevel2Data(int depth, KucoinAPICallback<KucoinEvent<Level2Event>> callback, String... symbols);


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
     * Subscribe this topic to get the updated data for orders and trades.
     * <p>
     * This channel provides real-time updates on orders and trades.
     * These updates can be applied on to a Level 3 order book snapshot for users to maintain an accurate and up-to-date copy of the exchange order book.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onLevel3Data_V2(KucoinAPICallback<KucoinEvent<Level3Event>> callback, String... symbols);

    /**
     * Subsribe this topic to fully get the updata data for orders and trades.
     * The full channel provides real-time updates on orders and trades.
     * These updates can be applied on to a level 3 order book snapshot to maintain an accurate and up-to-date copy of the exchange order book.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     * @deprecated instead use the method <code>onLevel3Data_V2</code>
     */
    @Deprecated
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
     */
    void close() throws IOException;

    /**
     * Subscribe to get snapshot data for a single symbol or a market
     *
     * @param callback
     * @param target   symbol or market
     * @return
     */
    String onSnapshot(KucoinAPICallback<KucoinEvent<SnapshotEvent>> callback, String target);

    /**
     * Subscribe to this topic to get the index prices used for leveraged trading.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onIndicatorIndex(KucoinAPICallback<KucoinEvent<IndicatorEvent>> callback, String... symbols);

    /**
     * Subscribe to this topic to get the mark prices used for leveraged trading.
     *
     * @param callback
     * @param symbols
     * @return
     */
    String onIndicatorMarkPrice(KucoinAPICallback<KucoinEvent<IndicatorEvent>> callback, String... symbols);

    /**
     * Subscribe to this topic to get the changes of leveraged trading borrowing and selling orders.
     *
     * @param callback
     * @param currency
     * @return
     */
    String onMarginFundingBook(KucoinAPICallback<KucoinEvent<FundingBookEvent>> callback, String... currency);

}
