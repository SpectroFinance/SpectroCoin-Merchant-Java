package lt.spectrofinance.spectrocoin.merchantclient;

import lt.spectrofinance.spectrocoin.merchantclient.domain.CreateOrderRequest;
import lt.spectrofinance.spectrocoin.merchantclient.utils.SignUtil;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertNotNull;

public class SignOrderTest {

	private static final String PRIVATE_KEY_FILE_NAME = "private_key.pem";
	private static final String SIGNATURE = "JFPxp4zS680iWjP4L4uqnuV2Hk4HN6cz5yfxfzuHpvLUp5Rn96JZZW5D0CLNUZ8nVr3CH8ihBA3L3cZVodkzb5zz+XWOvuH4gWC9XJv8bHtIyRuX5KlzqaxTMeWvDXy0ozmOdHG0QVnCq44JrjmA/r4HvJYli1H9op6L2nGyy1NWZi1L4JaDsy7yIYu2m20Plb0m7Zjqnss1QuZgeORnZCFncxmUAkKfiFQbf8h9nd+WQqJ8tW15Fcfbtm0wYyj8C/EGvt/27eLCRLv+cAcs2tOW6+VW83PfdUXMRzVkcjLdn4tC8+DybejbBxKfHRYa5D5mymmnF2k933fuUNiAkw==";
	private static final long MERCHANT_ID = 25L;
	private static final long API_ID = 74L;
	private static final String ORDER_ID = "NO-2514";
	private static final String PAY_CURRENCY = "BTC";
	private static final BigDecimal PAY_AMOUNT = new BigDecimal("20.0");
	private static final String RECEIVE_CURRENCY = "EUR";
	private static final String DESCRIPTION = "Super payment";
	private static final String CALLBACK_URL = "http://mySite.com/callback";

	@Test
    public void shouldValidateMerchantOrderSignature() throws URISyntaxException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException, IOException {
        assertNotNull("Private key file missing", getClass().getClassLoader().getResource(PRIVATE_KEY_FILE_NAME));

        URL resourceUrl = getClass().getClassLoader().getResource(PRIVATE_KEY_FILE_NAME);
        Path resourcePath = Paths.get(resourceUrl.toURI());
        System.out.println("resourcePath = " + resourcePath);

		CreateOrderRequest newOrder = new CreateOrderRequest();

		newOrder.setMerchantId(MERCHANT_ID);
		newOrder.setApiId(API_ID);
		newOrder.setOrderId(ORDER_ID);
		newOrder.setPayCurrency(PAY_CURRENCY);
		newOrder.setPayAmount(PAY_AMOUNT);
		newOrder.setReceiveCurrency(RECEIVE_CURRENCY);
		newOrder.setDescription(DESCRIPTION);
		newOrder.setCallbackUrl(CALLBACK_URL);

		String formValue = URLEncodedUtils.format(newOrder.getParameters(), StandardCharsets.UTF_8);
		System.out.println("formValue = " + formValue);

		PrivateKey privateKey = SignUtil.loadPKC8EncodedPrivateKey(resourcePath.toString());
		String result = SignUtil.sign(formValue, privateKey);
		System.out.println("orderSign = " + result);

		Assert.assertEquals("Not equal ! ! !", SIGNATURE, result);
    }

	@Test
	public void shouldFail_whenMerchantOrderSignatureIsIncorrect() throws URISyntaxException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException, IOException {
		assertNotNull("Private key file missing", getClass().getClassLoader().getResource(PRIVATE_KEY_FILE_NAME));

		URL resourceUrl = getClass().getClassLoader().getResource(PRIVATE_KEY_FILE_NAME);
		Path resourcePath = Paths.get(resourceUrl.toURI());
		System.out.println("resourcePath = " + resourcePath);

		CreateOrderRequest newOrder = new CreateOrderRequest();

		newOrder.setMerchantId(MERCHANT_ID);
		newOrder.setApiId(API_ID);
		newOrder.setOrderId(ORDER_ID);
		newOrder.setPayCurrency(PAY_CURRENCY);
		newOrder.setPayAmount(PAY_AMOUNT.add(BigDecimal.ONE));
		newOrder.setReceiveCurrency(RECEIVE_CURRENCY);
		newOrder.setDescription(DESCRIPTION);
		newOrder.setCallbackUrl(CALLBACK_URL);

		String formValue = URLEncodedUtils.format(newOrder.getParameters(), StandardCharsets.UTF_8);
		System.out.println("formValue = " + formValue);

		PrivateKey privateKey = SignUtil.loadPKC8EncodedPrivateKey(resourcePath.toString());
		String result = SignUtil.sign(formValue, privateKey);
		System.out.println("orderSign = " + result);

		Assert.assertNotEquals("Equal ! ! !", SIGNATURE, result);
	}
}
