/**
 * Copyright 2019 Mek Global Limited.
 */
package com.kucoin.sdk;

import com.kucoin.sdk.model.enums.PrivateChannelEnum;
import com.kucoin.sdk.websocket.KucoinAPICallback;
import com.kucoin.sdk.websocket.event.*;

import java.io.IOException;

/**
 * Created by chenshiwei on 2019/1/10.
 */
public interface KucoinPrivateWSClient {

    /**
     * When a stop-limit order is triggered, you would receive an activate message which means that this order started the matching life cycle.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     * @deprecated instead use the method {@code onStopOrderChange(KucoinAPICallback<KucoinEvent<OrderChangeEvent>> callback, String... symbols)}
     */
    @Deprecated
    String onOrderActivate(KucoinAPICallback<KucoinEvent<OrderActivateEvent>> callback, String... symbols);

    /**
     * You will receive this message when an account balance changes. The message contains the details of the change.
     *
     * @param callback
     * @return The subscription UUID, or null if sending failed.
     */
    String onAccountBalance(KucoinAPICallback<KucoinEvent<AccountChangeEvent>> callback);

    /**
     * You will receive the message when order changes. The message contains the details of the change.
     *
     * @param callback
     * @return The subscription UUID, or null if sending failed.
     */
    String onOrderChange(KucoinAPICallback<KucoinEvent<OrderChangeEvent>> callback);

    /**
     * You will receive the message when order changes. The message contains the details of the change.
     *
     * @param callback
     * @return The subscription UUID, or null if sending failed.
     */
    String onOrderV2Change(KucoinAPICallback<KucoinEvent<OrderChangeEvent>> callback);

    /**
     * When the position status changes, a status change event will be pushed.
     * When there is a liability, the system will push the current liability information at regular intervals.
     *
     * @param callback
     * @return
     */
    String onMarginPosition(KucoinAPICallback<KucoinEvent<MarginPositionEvent>> callback);

    /**
     * Isolated Margin Position Event
     * <a href="https://www.kucoin.com/docs/websocket/margin-trading/private-channels/isoleted-margin-position-event">ApiDoc</a>
     *
     * @param callback callback
     * @return String
     */
    String onMarginIsolatedPosition(KucoinAPICallback<KucoinEvent<MarginIsolatedPositionEvent>> callback);

    /**
     * Borrowing change message push
     *
     * @param callback
     * @param symbol
     * @return
     */
    String onMarginLoan(KucoinAPICallback<KucoinEvent<MarginLoanEvent>> callback, String symbol);

    /**
     * You will receive the message when the status of advanced order changes. The message contains the details of the change.
     *
     * @param callback
     * @param symbols
     * @return The subscription UUID, or null if sending failed.
     */
    String onAdvancedOrder(KucoinAPICallback<KucoinEvent<? extends AdvancedOrderEvent>> callback, String... symbols);

    /**
     * To prevent the TCP link being disconnected by the server, the client side needs to send ping messages to the server to keep alive the link.
     * After the ping message is sent to the server, the system would return a pong message to the client side.
     *
     * @param requestId
     * @return The requestId back, or null if sending failed.
     */
    String ping(String requestId);

    /**
     * Unsubscribe from topics you have subscribed to.
     *
     * @param channelEnum
     * @param symbols
     * @return The ID for the unsubscribe request, or null if sending failed.
     */
    String unsubscribe(PrivateChannelEnum channelEnum, String... symbols);

    /**
     * Close client
     */
    void close() throws IOException;
}
