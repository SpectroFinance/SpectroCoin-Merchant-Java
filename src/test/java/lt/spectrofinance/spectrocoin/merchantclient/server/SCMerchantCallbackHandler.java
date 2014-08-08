package lt.spectrofinance.spectrocoin.merchantclient.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lt.spectrofinance.spectrocoin.merchantclient.server.event.CallbackEvent;
import lt.spectrofinance.spectrocoin.merchantclient.server.listener.CallbackListener;
import lt.spectrofinance.spectrocoin.merchantclient.domain.OrderCallback;
import lt.spectrofinance.spectrocoin.merchantclient.utils.SignUtil;
import lt.spectrofinance.spectrocoin.merchantclient.utils.SignatureBuilderUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.List;


/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 * Simple HTTP Server Handler example that demonstrates how easy it is to apply
 * the Http Server built-in to Sun's Java SE 6 JVM.
 */

public class SCMerchantCallbackHandler implements HttpHandler {


	private String CHARSET_NAME = "UTF-8";
	private PublicKey publicKey;

	private CallbackPublisher publisher = new CallbackPublisher();

	public SCMerchantCallbackHandler(String privateCertFileLoc) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		this(new FileInputStream(new File(privateCertFileLoc)));
	}

	public SCMerchantCallbackHandler(InputStream privateCert) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeySpecException, NoSuchAlgorithmException {
		this(SignUtil.loadPublicKey(privateCert));
	}

	public SCMerchantCallbackHandler(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		final String requestMethod = httpExchange.getRequestMethod();
		System.out.println("*** HTTP Request Method -> " + requestMethod + " ***");

		// Request body.
		InputStreamReader isr =	new InputStreamReader(httpExchange.getRequestBody(), CHARSET_NAME);
		BufferedReader br = new BufferedReader(isr);

		String sCurrentLine = br.readLine();
		System.out.println("Request -> " + sCurrentLine);

		List<NameValuePair>	parse = URLEncodedUtils.parse(sCurrentLine, Charset.forName(CHARSET_NAME));

		OrderCallback callbackInfo = new OrderCallback(parse);


		String response = null;
		try {

			boolean callbackSign = SignUtil.checkSign(SignatureBuilderUtil.getValueForSign(callbackInfo), callbackInfo.getSign(), this.publicKey);
			if(callbackSign){
				System.out.println("Correct sign ! ! !");
				publisher.sendResponse(callbackInfo);
				response = "*ok*";
			} else {
				System.out.println("Bad sign ! ! !");
				response = "error";
			}
		} catch (Exception e) {
			response = "error";
			System.out.println(e);
		}

		httpExchange.sendResponseHeaders(200, response.length());

		//info for response
		OutputStream os = httpExchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

	public void addCallbackListener(CallbackListener<CallbackEvent> paidListener) {
		publisher.addCallbackListener(paidListener);
	}
}