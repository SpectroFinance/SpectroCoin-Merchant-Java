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
		newOrder.setPayAmount(BigDecimal.valueOf(20.0));
		newOrder.setDescription("Super payment");
		newOrder.setCallbackUrl("http://mySite.com/callback");

		String formValue = URLEncodedUtils.format(newOrder.getParameters(), "UTF-8");

        String si = "dOXYqS9O8OnjVBGNA/RcPxCn5ELuJ4v6ZGRjD5PuD+fOlWBUuL8TgqoEC8cngHbzFdNs43PUYL0nu/2EtAE6eZAFvp4Y9wXrVVh+/nuRI62V3GvzM8kBHBtYNax8o7bTsxsL6NB2sxrd6H8hD109toLKbdp7Ukn+VEzQ+wfB4oK+Cjx2t/lsU1nXNX8sROh7aT8eMZOxVOMMJZwH/UbbOEl2obgb+mYZ7o6agffNBT77dgQ+3r4NJBq19Bhl9VVnTIw3mWuQGJtTSbIpd16q543EFWIVnlev7DU4CD/7T8+n8tIRfs7f/6BmqmirCFNOEZ3QqDYqNakgEbGo3yLRqA==";

		InputStream inputStream = new FileInputStream(new File(resourcePath.toString()));
		PrivateKey privateKey = SignUtil.loadPKC8EncodedPrivateKey(inputStream);
		String orderSign = SignUtil.sign(formValue, privateKey);
		System.out.println("orderSign = " + orderSign);

		Assert.assertEquals("Not equal ! ! !", si, orderSign);

    }
}
