package lt.spectrofinance.spectrocoin.merchantclient;

import lt.spectrofinance.spectrocoin.merchantclient.domain.*;
import lt.spectrofinance.spectrocoin.merchantclient.utils.SignUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.1 JAVA client
 *
 */
public class SCMerchantClient {

	private String URL = "https://spectrocoin.com/api/merchant/1/createOrder";
	private String CHARSET_NAME = "UTF-8";

	private PrivateKey privateKey;
	private Long apiId;
	private Long merchantId;

	/**
	 * Constructor for Spectro coin merchant client with base parameters
	 * @param merchantId merchant api id
	 * @param apiId API id
	 * @param privateCertFileLoc private key file location
	 */
	public SCMerchantClient(Long merchantId, Long apiId, String privateCertFileLoc) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		this(merchantId, apiId, new FileInputStream(new File(privateCertFileLoc)));
	}

	/**
	 * Constructor for Spectro coin merchant client with base parameters
	 * @param merchantId merchant api id
	 * @param apiId API id
	 * @param privateCert private key file input stream
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 */
	public SCMerchantClient(Long merchantId, Long apiId, InputStream privateCert) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
		this(merchantId, apiId, SignUtil.loadPKC8EncodedPrivateKey(privateCert));
	}

	/**
	 * Constructor for Spectro coin merchant client with base parameters and URL for testing purposes
	 * @param merchantId merchant api id
	 * @param apiId API id
	 * @param privateCert private key file input stream
	 * @param URL Location of service
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 */
	public SCMerchantClient(Long merchantId, Long apiId, InputStream privateCert, String URL) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
		this(merchantId, apiId, SignUtil.loadPKC8EncodedPrivateKey(privateCert), URL);
	}

	/**
	 * Constructor for Spectro coin merchant client with base parameters
	 * @param merchantId merchant api id
	 * @param apiId API id
	 * @param privateKey private key
	 */
	public SCMerchantClient(Long merchantId, Long apiId, PrivateKey privateKey) {
		this.merchantId = merchantId;
		this.apiId = apiId;
		this.privateKey = privateKey;
	}

	/**
	 * Constructor for Spectro coin merchant client with base parameters and URL for testing purposes
	 * @param merchantId merchant api id
	 * @param apiId API id
	 * @param privateKey private key
	 * @param URL
	 */
	public SCMerchantClient(Long merchantId, Long apiId, PrivateKey privateKey, String URL) {
		this.merchantId = merchantId;
		this.apiId = apiId;
		this.privateKey = privateKey;
		this.URL = URL;
	}

	/**
	 * Create order for Spectro coin
	 *
	 * @param orderRequest request data for Spectro coin merchant order
	 * @return CreateOrderErrorResponse or CreateOrderResponse
	 */
	public MerchantAPIResponse createOrder(CreateOrderRequest orderRequest) throws Exception {

		orderRequest.setMerchantId(merchantId);
		orderRequest.setApiId(apiId);

		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(URL);

		ArrayList<BasicNameValuePair> parameters = orderRequest.getParameters();

		String signature = SignUtil.sign(getValueForSign(parameters), this.privateKey);
		if (signature != null) {
			parameters.add(new BasicNameValuePair("sign", signature));
		}

		post.setEntity(new UrlEncodedFormEntity(parameters));
		HttpResponse response = client.execute(post);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuilder result = new StringBuilder();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return convertToResponseObject(result.toString());
	}

	private String getValueForSign(ArrayList<BasicNameValuePair> parameters){
		return URLEncodedUtils.format(parameters, CHARSET_NAME);
	}

	private MerchantAPIResponse convertToResponseObject(String responseText) throws ParseException {

		CreateOrderResponse response = new CreateOrderResponse();
		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject  = (JSONObject)parser.parse(responseText);

			response.setOrderRequestId((Long) jsonObject.get("orderRequestId"));
			response.setOrderId((String) jsonObject.get("orderId"));
			response.setPayCurrency((String) jsonObject.get("payCurrency"));
			response.setPayAmount(new BigDecimal(jsonObject.get("payAmount").toString()).setScale(8, RoundingMode.HALF_UP));
			response.setReceiveCurrency((String) jsonObject.get("receiveCurrency"));
			response.setReceiveAmount(new BigDecimal(jsonObject.get("receiveAmount").toString()).setScale(8, RoundingMode.HALF_DOWN));
			response.setDepositAddress((String) jsonObject.get("depositAddress"));
			response.setValidUntil((Long) jsonObject.get("validUntil"));
			response.setRedirectUrl((String) jsonObject.get("redirectUrl"));

		} catch (ClassCastException e){
			return parseError(responseText);
		}

		return  response;
	}

	private CreateOrderErrorResponse parseError(String responseText) {

		JSONParser parser = new JSONParser();
		CreateOrderErrorResponse response = new CreateOrderErrorResponse();

		ArrayList<ApiError> list = new ArrayList<>();

		try {
			JSONArray jsonArray = (JSONArray) parser.parse(responseText);
			Iterator i = jsonArray.iterator();

			// take each value from the json array separately
			while (i.hasNext()) {
				ApiError apiError = new ApiError();
				JSONObject innerObj = (JSONObject) i.next();

				apiError.setCode((Long) innerObj.get("code"));
				apiError.setMessage((String) innerObj.get("message"));

				list.add(apiError);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		response.setErrorsList(list);
		return response;
	}
}