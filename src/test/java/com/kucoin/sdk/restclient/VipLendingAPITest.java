package com.kucoin.sdk.restclient;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;
import com.kucoin.sdk.model.enums.ApiKeyVersionEnum;
import com.kucoin.sdk.rest.response.OtcLoanAccountResponse;
import com.kucoin.sdk.rest.response.OtcLoanLoanResponse;
import org.junit.BeforeClass;
import org.junit.Test;

public class VipLendingAPITest {
    private static KucoinRestClient liveKucoinRestClient;

    @BeforeClass
    public static void setUpClass() {
        liveKucoinRestClient = new KucoinClientBuilder().withBaseUrl("https://openapi-v2.kucoin.com")
                .withApiKey("", "", "")
                // Version number of api-key
                .withApiKeyVersion(ApiKeyVersionEnum.V2.getVersion())
                .buildRestClient();
    }

    @Test
    public void vipLendingAPI_() throws Exception {
        OtcLoanLoanResponse otcLoanLoanResponse = liveKucoinRestClient.vipLendingAPI().getOtcLoanLoan();

        OtcLoanAccountResponse otcLoanAccountResponse = liveKucoinRestClient.vipLendingAPI().getOtcLoanAccounts();
    }
}