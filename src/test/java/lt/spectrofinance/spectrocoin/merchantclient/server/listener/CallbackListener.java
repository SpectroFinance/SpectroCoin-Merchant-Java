package lt.spectrofinance.spectrocoin.merchantclient.server.listener;

import lt.spectrofinance.spectrocoin.merchantclient.server.event.ApplicationEvent;

import java.util.EventListener;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */

public interface CallbackListener<E extends ApplicationEvent> extends EventListener {

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	void onEvent(E event) throws Exception;

}
