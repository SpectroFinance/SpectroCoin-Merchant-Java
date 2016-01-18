package lt.spectrofinance.spectrocoin.merchantclient;

import lt.spectrofinance.spectrocoin.merchantclient.domain.CreateOrderRequest;
import lt.spectrofinance.spectrocoin.merchantclient.utils.SignUtil;
import org.apache.http.client.utils.URLEncodedUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertNotNull;

public class SignOrderTest {

    private String PRIVATE_KEY_FILE_NAME = "private_key.der";

    @Test
    public void Test() throws URISyntaxException, NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException, IOException {

        assertNotNull("Private key file missing", getClass().getClassLoader().getResource(PRIVATE_KEY_FILE_NAME));

        URL resourceUrl = getClass().getClassLoader().getResource(PRIVATE_KEY_FILE_NAME);
        Path resourcePath = Paths.get(resourceUrl.toURI());
        System.out.println("resourcePath = " + resourcePath);

		CreateOrderRequest newOrder = new CreateOrderRequest();

		newOrder.setMerchantId(25L);
		newOrder.setApiId(74L);
		newOrder.setOrderId("NO-2514");
		newOrder.setPayCurrency("BTC");
		newOrder.setPayAmount(new BigDecimal("20.0"));
		newOrder.setReceiveCurrency("EUR");
		newOrder.setDescription("Super payment");
		newOrder.setCallbackUrl("http://mySite.com/callback");

		String formValue = URLEncodedUtils.format(newOrder.getParameters(), "UTF-8");
		System.out.println("formValue = " + formValue);
		String si = "eOunQbsLmsziZIqJUDeShg7YgVsIghQ+WGbU94bi6/KaUwg82KbEjF0jLlQbe0UH29ImlWj5fG6/vsjl85WoHJ9FTrDnJ1l5gDzs9GYMz9pzFiCmAU4wkcOgaYhsh69WZeT6B4k/DpmfoKv07T0q5FEj3YBwHIZZl87/HBtzMBhno83SlsvICO5OmTu9x6g+iLl/gruDraPabY2yQa+i7gKtDcFFXgi+5TqIQQhebmQ27PqaIimI3gBEDWx3GOkG3ubt0xcAwYsdS2ufMpaXKfO+lUi9YnWXv7kny9/FTZuLAzVtmJoxOo4LgocrTNVv0pLflqtcNcxrwmCRO1vWAA==";

		InputStream inputStream = new FileInputStream(new File(resourcePath.toString()));
		PrivateKey privateKey = SignUtil.loadPKC8EncodedPrivateKey(inputStream);
		String orderSign = SignUtil.sign(formValue, privateKey);
		System.out.println("orderSign = " + orderSign);

		Assert.assertEquals("Not equal ! ! !", si, orderSign);

    }
}
