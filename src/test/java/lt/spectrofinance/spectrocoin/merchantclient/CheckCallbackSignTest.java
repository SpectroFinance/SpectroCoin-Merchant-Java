package lt.spectrofinance.spectrocoin.merchantclient;

import lt.spectrofinance.spectrocoin.merchantclient.domain.OrderCallback;
import lt.spectrofinance.spectrocoin.merchantclient.domain.OrderStatusEnum;
import lt.spectrofinance.spectrocoin.merchantclient.utils.SignUtil;
import lt.spectrofinance.spectrocoin.merchantclient.utils.SignatureBuilderUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.assertNotNull;

public class CheckCallbackSignTest {

    private String SC_PUBLIC_KEY_FILE_NAME = "sc_public_key.pem";

    @Test
    public void checkCallbackSign() throws InvalidKeySpecException, SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException, URISyntaxException {
		assertNotNull("Public key file missing", getClass().getClassLoader().getResource(SC_PUBLIC_KEY_FILE_NAME));

		URL resourceUrl = getClass().getClassLoader().getResource(SC_PUBLIC_KEY_FILE_NAME);
		Path resourcePath = null;
		resourcePath = Paths.get(resourceUrl.toURI());

		System.out.println("resourcePath = " + resourcePath);

		OrderCallback orderCallback = new OrderCallback();

		orderCallback.setUserId(169L);
		orderCallback.setMerchantApiId(3L);
		orderCallback.setOrderId("275");
		orderCallback.setPayCurrency("BTC");
		orderCallback.setPayAmount(new BigDecimal("20.0"));
		orderCallback.setReceiveCurrency("EUR");
		orderCallback.setReceiveAmount(new BigDecimal("8523.5"));
		orderCallback.setReceivedAmount(new BigDecimal("0"));
		orderCallback.setDescription("Payment for Car-2547");
		orderCallback.setOrderRequestId(275L);
		orderCallback.setStatus(OrderStatusEnum.EXPIRED);
		String value = SignatureBuilderUtil.getValueForSign(orderCallback);
		System.out.println("values = " + value);

		String responseSign = "EtNN9l24CY9JIXEFrAqArxq8kCuzJxI3g19MEXqZIPAEUEgC/Dl5WTXVdYJeQZyvs8Xn8DZf7ikq786g3ZGa293dYILeWC/DcS3Dcs2rI183g9s5l/q/GdkziaH2/hDf8veFjRY/CheKiNDe8+zCTHawvEwf+qRoHKQefjKLUHrmf4r+wsHXLZQZCEvb009XWtDccgRk6NuDqzoodaFYCzjl41bWCxOsC1MfP+y0uemYz/IqU/NRVuGBiYkiibllWgzvL5nYfledEUx+ZrxljwngNqLTR42km7AjDIrTHxmLLqI7qjl/sipfWkQsAQ+rJTFjIdlJ18z4qwpCgUysOA==";
		System.out.println("responseSign = " + responseSign);

		FileInputStream fileInputStream = new FileInputStream(new File(resourcePath.toString()));
		PublicKey publicKey = SignUtil.loadPublicKey(fileInputStream);
		boolean callbackSign = SignUtil.checkSign(value, responseSign, publicKey);
		Assert.assertTrue("Bad sign", callbackSign);
    }

}
