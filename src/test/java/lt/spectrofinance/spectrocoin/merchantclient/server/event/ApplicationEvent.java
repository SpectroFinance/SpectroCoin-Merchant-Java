package lt.spectrofinance.spectrocoin.merchantclient.server.event;

import java.util.EventObject;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */

public abstract class ApplicationEvent<OrderCallback> extends EventObject {

	private OrderCallback callback;

	/**
	 * Create a new ApplicationEvent.
	 * @param source the component that published the event (never {@code null})
	 */
	public ApplicationEvent(OrderCallback source) {
		super(source);
		this.callback = source;
	}

	public OrderCallback getCallback() {
		return this.callback;
	}
}
