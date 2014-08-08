package lt.spectrofinance.spectrocoin.merchantclient.server;

import lt.spectrofinance.spectrocoin.merchantclient.domain.OrderCallback;
import lt.spectrofinance.spectrocoin.merchantclient.server.event.CallbackEvent;
import lt.spectrofinance.spectrocoin.merchantclient.server.listener.CallbackListener;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */
class CallbackPublisher {

	private CallbackListener<CallbackEvent> event;

	public void addCallbackListener(CallbackListener<CallbackEvent> event) {
		this.event = event;
	}

	public void sendResponse(OrderCallback orderCallback) throws Exception {
		// Notify event listener.

		if(event != null){
			event.onEvent(new CallbackEvent(orderCallback));
		}
	}

}