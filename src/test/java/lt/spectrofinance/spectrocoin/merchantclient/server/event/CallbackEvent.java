package lt.spectrofinance.spectrocoin.merchantclient.server.event;

import lt.spectrofinance.spectrocoin.merchantclient.domain.OrderCallback;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */

public class CallbackEvent extends ApplicationEvent<OrderCallback> {

	public CallbackEvent(OrderCallback source) {
		super(source);
	}
}