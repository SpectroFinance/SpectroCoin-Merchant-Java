package lt.spectrofinance.spectrocoin.merchantclient.domain;

import java.math.BigDecimal;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */
public interface IOrderCallback {

	Long userId = null;
	Long merchantApiId = null;
	String orderId = null;
	String payCurrency = null;
	BigDecimal payAmount = null;
	String receiveCurrency = null;
	BigDecimal receiveAmount = null;
	BigDecimal receivedAmount = null;
	String description = null;
	Long orderRequestId = null;
	OrderStatusEnum status = null;
	String sign = null;

	Long getUserId();
	Long getMerchantApiId();
	String getOrderId();
	String getPayCurrency();
	BigDecimal getPayAmount();
	String getReceiveCurrency();
	BigDecimal getReceiveAmount();
	BigDecimal getReceivedAmount();
	String getDescription();
	Long getOrderRequestId();
	OrderStatusEnum getStatus();
	String getSign();
}
