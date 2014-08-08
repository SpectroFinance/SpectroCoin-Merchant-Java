package lt.spectrofinance.spectrocoin.merchantclient;

import lt.spectrofinance.spectrocoin.merchantclient.domain.OrderCallback;
import lt.spectrofinance.spectrocoin.merchantclient.server.HttpServerMain;
import lt.spectrofinance.spectrocoin.merchantclient.server.SCMerchantCallbackHandler;
import lt.spectrofinance.spectrocoin.merchantclient.server.event.CallbackEvent;
import lt.spectrofinance.spectrocoin.merchantclient.server.listener.CallbackListener;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class HandlerTest {

	String PUBLIC_KEY_URL = "https://spectrocoin.com/files/merchant.public.pem";

	@Test
	public void test() throws InterruptedException, IOException, URISyntaxException, InvalidKeySpecException, NoSuchAlgorithmException {

		System.out.println("Starting HTTP server and waiting for callback...");

		InputStream is = new URL(PUBLIC_KEY_URL).openStream();
		SCMerchantCallbackHandler scMerchantCallbackHandler = new SCMerchantCallbackHandler(is);
		scMerchantCallbackHandler.addCallbackListener(new EventListener());
		HttpServerMain httpServer = new HttpServerMain(8000, "/httphandler", scMerchantCallbackHandler);
		httpServer.start();

		//waiting 60 minutes
		Thread.sleep(3600000);
	}


	class EventListener implements CallbackListener<CallbackEvent> {

		@Override
		public void onEvent(CallbackEvent event) throws Exception {

			OrderCallback callbackInfo = event.getCallback();


			switch (callbackInfo.getStatus()){
				case NEW: {
					System.out.println("Payment request is registered");
					System.out.println("User should to pay " + callbackInfo.getPayAmount() + " " + callbackInfo.getPayCurrency());
					System.out.println("I should to receive " + callbackInfo.getReceiveAmount() + " " + callbackInfo.getReceiveCurrency());
					break;
				}
				case PENDING : {
					System.out.println("User paid, now need to wait while transaction will be approved");
					System.out.println("User paid for " + callbackInfo.getReceivedAmount() + " " + callbackInfo.getReceiveCurrency());
					System.out.println("I will receive " + callbackInfo.getReceiveAmount() + " " + callbackInfo.getReceiveCurrency());
					break;
				}
				case PAID : {
					System.out.println("CreateOrderRequest is paid and approved");
					System.out.println("User paid for " + callbackInfo.getReceivedAmount() + " " + callbackInfo.getReceiveCurrency());
					System.out.println("I received " + callbackInfo.getReceiveAmount() + " " + callbackInfo.getReceiveCurrency());
					break;
				}
				case FAILED : {
					System.out.println("Failed to pay");
					System.out.println("User should have paid " + callbackInfo.getPayAmount() + " " + callbackInfo.getPayCurrency());
					break;
				}
				case EXPIRED : {
					System.out.println("Payment expired");
					System.out.println("User paid for " + callbackInfo.getReceivedAmount() + " " + callbackInfo.getReceiveCurrency());
					break;
				}
				case TEST : {
					System.out.println("This is test callback");
					System.out.println("Pay amount " + callbackInfo.getPayAmount() + " " + callbackInfo.getPayCurrency());
					System.out.println("Receive amount " + callbackInfo.getReceiveAmount() + " " + callbackInfo.getReceiveCurrency());
					break;
				}
				default:
					throw new Exception("Unknown status: " + callbackInfo.getStatus());
			}
		}
	}
}
