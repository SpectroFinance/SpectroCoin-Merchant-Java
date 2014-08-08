package lt.spectrofinance.spectrocoin.merchantclient;

import lt.spectrofinance.spectrocoin.merchantclient.domain.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ClientTest {

	private String PRIVATE_KEY_FILE_NAME =	"private_key.der";

	private Long MERCHANT_ID = 87L;
	private Long API_ID = 1L;

	@Test
	public void clientTest() throws Exception {

		//get private key file location
		URL resourceUrl = getClass().getClassLoader().getResource(PRIVATE_KEY_FILE_NAME);
		Path resourcePath = null;
		try {
			resourcePath = Paths.get(resourceUrl.toURI());
			System.out.println("INFO: private key file location path = " + resourcePath);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		SCMerchantClient client = new SCMerchantClient(MERCHANT_ID, API_ID, resourcePath.toString());

		CreateOrderRequest orderRequest = new CreateOrderRequest();

		//settings for SC merchant order
		orderRequest.setCallbackUrl("http://localhost:8000/httphandler");

		//order information
	//	orderRequest.setOrderId("CT-3009");
		orderRequest.setPayAmount(BigDecimal.valueOf(20.0));
		orderRequest.setDescription("Payment for Car-2547");

		MerchantAPIResponse orderResponse = client.createOrder(orderRequest);

		if(orderResponse instanceof CreateOrderResponse){
			CreateOrderResponse response = (CreateOrderResponse) orderResponse;
			System.out.println("orderResponse = " + response);

		} else if(orderResponse instanceof CreateOrderErrorResponse){

			ArrayList<ApiError> errorsList = ((CreateOrderErrorResponse) orderResponse).getErrorsList();

			for (ApiError apiError : errorsList) {
				System.out.println("apiError = " + apiError);
			}
		}
	}
}
