package lt.spectrofinance.spectrocoin.merchantclient;

import lt.spectrofinance.spectrocoin.merchantclient.domain.CreateOrderRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class URLEncoderTest {

    private static final String EXPECTED_RESULT = "merchantId=25&apiId=74&orderId=NO-2514&payCurrency=BTC&payAmount=20.0&receiveCurrency=EUR&receiveAmount=0.0&description=Super+payment&culture=&callbackUrl=http%3A%2F%2FmySite.com%2Fcallback&successUrl=&failureUrl=";

    @Test
    public void shouldUrlEncodeRequestParams() {
        CreateOrderRequest newOrder = new CreateOrderRequest();
        newOrder.setMerchantId(25L);
        newOrder.setApiId(74L);
        newOrder.setOrderId("NO-2514");
        newOrder.setPayCurrency("BTC");
        newOrder.setPayAmount(new BigDecimal("20.0"));
        newOrder.setReceiveCurrency("EUR");
        newOrder.setDescription("Super payment");
        newOrder.setCallbackUrl("http://mySite.com/callback");

        String result = URLEncodedUtils.format(newOrder.getParameters(), "UTF-8");
        System.out.println("result = " + result);

        Assert.assertEquals(EXPECTED_RESULT, result);
    }
}
