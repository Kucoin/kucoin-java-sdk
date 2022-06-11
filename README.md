# JAVA SDK for KuCoin API
The detailed document [https://docs.kucoin.com](https://docs.kucoin.com).

[![Latest Version](https://img.shields.io/github/release/Kucoin/kucoin-java-sdk.svg?style=flat-square)](https://github.com/Kucoin/kucoin-java-sdk/releases)

[![Build Status](https://travis-ci.org/Kucoin/kucoin-java-sdk.svg?branch=master)](https://travis-ci.org/Kucoin/kucoin-java-sdk)

## Installation
1. Install library into your Maven's local repository by running `mvn clean install`
2. Add the following **Maven dependency** to your project's pom.xml:

```java
<dependency>
    <groupId>com.kucoin</groupId>
    <artifactId>kucoin-java-sdk</artifactId>
    <version>1.0.6</version>
</dependency>
```
## Usage
### Build Client
```java
KucoinClientBuilder builder = new KucoinClientBuilder().withBaseUrl("https://openapi-sandbox.kucoin.com").withApiKey("YOUR_API_KEY", "YOUR_SECRET", "YOUR_PASS_PHRASE");
KucoinRestClient kucoinRestClient = builder.buildRestClient();
KucoinPrivateWSClient kucoinPrivateWSClient = builder.buildPrivateWSClient();
KucoinPublicWSClient kucoinPublicWSClient = builder.buildPublicWSClient();

```
You can use `withBaseUrl` method to change evironment.

| **Environment** | **BaseUri** |
| -------- | -------- |
| *Production* `DEFAULT` | https://openapi-v2.kucoin.com |
| *Sandbox* | https://openapi-sandbox.kucoin.com |

If you only need to use the public web socket client or REST client public method, you can igonre `withApiKey` method. To customize your own API implementation, you may use the `with*API` method we provided for you.

## Example

### REST API

#### User
You need to sign the request to use the private user API.
##### Accounts
```java
kucoinRestClient.accountAPI().listAccounts(currency, type);
kucoinRestClient.accountAPI().getAccount(mainAccountId);
kucoinRestClient.accountAPI().getAccountHistory(mainAccountId, startAt, endAt, pageNo, pageSize);
kucoinRestClient.accountAPI().innerTransfer(clientOid, fromAccountId, size, toAccountId);
kucoinRestClient.accountAPI().createAccount(currency, type);             
```
##### Deposits
```java
kucoinRestClient.depositAPI().createDepositAddress(currency);
kucoinRestClient.depositAPI().getDepositAddress(currency);
kucoinRestClient.depositAPI().getDepositPageList(currency, startAt, endAt, status, pageNo, pageSize);
```
##### Withdrawals
```java
kucoinRestClient.withdrawalAPI().getWithdrawList(currency, status, startAt, endAt, pageNo, pageSize);
kucoinRestClient.withdrawalAPI().getWithdrawQuotas(currency);
WithdrawApplyRequest withdrawApplyRequest = WithdrawApplyRequest.builder().address("address")
                .amount(BigDecimal.valueOf(amount)).currency("currency").build();
kucoinRestClient.withdrawalAPI().applyWithdraw(withdrawApplyRequest);
kucoinRestClient.withdrawalAPI().cancelWithdraw(withdrawalId);
```
#### Trade
You need to sign the request to use the private trade API.
##### Orders
```java
OrderCreateApiRequest request = OrderCreateApiRequest.builder()
                .price(BigDecimal.valueOf(price)).size(BigDecimal.ONE).side("buy")
                .symbol("ETH-BTC").type("limit").clientOid(UUID.randomUUID().toString()).build();
kucoinRestClient.orderAPI().createOrder(request);
kucoinRestClient.orderAPI().listOrders(symbol, side, type, status, startAt, endAt, pageNo, pageSize);
kucoinRestClient.orderAPI().getOrder(orderId);
kucoinRestClient.orderAPI().cancelOrder(orderId);
kucoinRestClient.orderAPI().cancelAllOrders(symbol);
```
##### Fills
```java
kucoinRestClient.fillAPI().listFills(symbol, orderId, side, type, startAt, endAt, pageNo, pageSize);
```
#### Market Date
Market data is public and can be used without a signed request.
##### Symbols & Ticker
```java
kucoinRestClient.symbolAPI().getTicker(symbol);
kucoinRestClient.symbolAPI().getSymbols();
kucoinRestClient.symbolAPI().getAllTickers();
kucoinRestClient.symbolAPI().get24hrStats(symbol);
kucoinRestClient.symbolAPI().getMarketList();
```
##### Order Book
```java
kucoinRestClient.orderBookAPI().getFullOrderBookAggregated(symbol);
kucoinRestClient.orderBookAPI().getFullOrderBookAtomic(symbol);
kucoinRestClient.orderBookAPI().getPartOrderBookAggregated(symbol);
```
##### Histories
```java
kucoinRestClient.historyAPI().getTradeHistories(symbol)
kucoinRestClient.historyAPI().getHistoricRates(symbol, startAt, endAt, type);
```
##### Currencies
```java
kucoinRestClient.currencyAPI().getCurrencies();
kucoinRestClient.currencyAPI().getCurrencyDetail(currency);
kucoinRestClient.currencyAPI().getFiatPrice(base, currencies);
```
##### Time
```java
kucoinRestClient.timeAPI().getServerTimeStamp();
```
### Websocket Feed
Use `KucoinClientBuilder` to build an instance of the websocket client. Private channel client need to pass the API Key + Secret + Pass Phreas.

The Websocket client uses `ChooseServerStrategy` to choose server for connection. If you don't plan to use `builder.withChooseServerStrategy` to set your own strategy, you may use the strategy we provided by random.

#### Ping
```java
wsClient.ping(requestId)
```
#### Unsubscribe
```java
wsClient.unsubscribe(channelEnum, symbols);
```
#### Close Client
```java
wsClient.close();
```
#### Public Channels

##### Listen for changes to the order boock for ETH-BTC and KCS-BTC

```java
kucoinPublicWSClient.onTicker(new KucoinAPICallback<KucoinEvent<TickerChangeEvent>>() {

	@Override
	public void onResponse(KucoinEvent<TickerChangeEvent> response) throws KucoinApiException {
		System.out.println(response);
	}

	@Override
	public void onFailure(Throwable cause) {
		System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());
		//reinitializeWSConnection();	//implement this method
	}
        	
}, "ETH-BTC", "KCS-BTC");

kucoinPublicWSClient.onLevel2Data(new KucoinAPICallback<KucoinEvent<Level2ChangeEvent>>() {

	@Override
	public void onResponse(KucoinEvent<Level2ChangeEvent> response) throws KucoinApiException {
		System.out.println(response);
	}

	@Override
	public void onFailure(Throwable cause) {
		System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());
		//reinitializeWSConnection();	//implement this method
	}
        	
}, "ETH-BTC", "KCS-BTC");


kucoinPublicWSClient.onMatchExecutionData(new KucoinAPICallback<KucoinEvent<MatchExcutionChangeEvent>>() {

	@Override
	public void onResponse(KucoinEvent<MatchExcutionChangeEvent> response) throws KucoinApiException {
		System.out.println(response);
	}

	@Override
	public void onFailure(Throwable cause) {
		System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());
		//reinitializeWSConnection();	//implement this method
	}
        	
}, "ETH-BTC", "KCS-BTC");


kucoinPublicWSClient.onLevel3Data(new KucoinAPICallback<KucoinEvent<Level3ChangeEvent>>() {

	@Override
	public void onResponse(KucoinEvent<Level3ChangeEvent> response) throws KucoinApiException {
		System.out.println(response);
	}

	@Override
	public void onFailure(Throwable cause) {
		System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());
		//reinitializeWSConnection();	//implement this method
	}

}, "ETH-BTC", "KCS-BTC");

```

#### Private Channels

##### Listen for account balance changes

```java

kucoinPrivateWSClient.onAccountBalance(new KucoinAPICallback<KucoinEvent<AccountChangeEvent>>() {

	@Override
	public void onResponse(KucoinEvent<AccountChangeEvent> response) throws KucoinApiException {
		System.out.println(response);
	}

	@Override
	public void onFailure(Throwable cause) {
		System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());
		//reinitializeWSConnection();	//implement this method
	}
        	
});

```

##### Listen for order activate message

```java

kucoinPrivateWSClient.onOrderActivate(new KucoinAPICallback<KucoinEvent<OrderActivateEvent>>() {

	@Override
	public void onResponse(KucoinEvent<OrderActivateEvent> response) throws KucoinApiException {
		System.out.println(response);
	}

	@Override
	public void onFailure(Throwable cause) {
		System.out.println("WS connection failed. Reconnecting. cause:" + cause.getMessage());
		//reinitializeWSConnection();	//implement this method
	}

}, "ETH-BTC", "KCS-BTC");

```

## Testing
To contribute code or modify the library, you may enter the following command to run the test suite:

```java
mvn clean test
```
## License
[MIT](LICENSE)
