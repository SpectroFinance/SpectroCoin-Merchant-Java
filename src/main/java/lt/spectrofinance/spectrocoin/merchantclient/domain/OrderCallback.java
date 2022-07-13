package lt.spectrofinance.spectrocoin.merchantclient.domain;

import org.apache.http.NameValuePair;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */
public class OrderCallback implements IOrderCallback {

	private Long merchantId;
	private Long merchantApiId;
	private Long userId;
	private Long apiId;
	private String orderId;
	private String payCurrency;
	private BigDecimal payAmount;
	private String receiveCurrency;
	private BigDecimal receiveAmount;
	private BigDecimal receivedAmount;
	private String description;
	private Long orderRequestId;
	private OrderStatusEnum status;
	private String sign;

	public OrderCallback() {
	}

	public OrderCallback(List<NameValuePair> valuePairs) {

		for (NameValuePair pair : valuePairs) {
			try {
				addValue(pair);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void addValue(NameValuePair pair) throws Exception {

		switch (pair.getName()){
			case "merchantId" : merchantId = Long.valueOf(pair.getValue());
				break;
			case "merchantApiId" : merchantApiId = Long.valueOf(pair.getValue());
				break;
			case "userId" : userId = Long.valueOf(pair.getValue());
				break;
			case "apiId" : apiId = Long.valueOf(pair.getValue());
				break;
			case "orderId" : orderId = pair.getValue();
				break;
			case "payCurrency" : payCurrency = pair.getValue();
				break;
			case "payAmount" : payAmount = new BigDecimal(pair.getValue());
				break;
			case "receiveCurrency" : receiveCurrency = pair.getValue();
				break;
			case "receiveAmount" : receiveAmount = new BigDecimal(pair.getValue());
				break;
			case "receivedAmount" : receivedAmount = new BigDecimal(pair.getValue());
				break;
			case "description" : description = pair.getValue();
				break;
			case "orderRequestId" : orderRequestId = Long.valueOf(pair.getValue());
				break;
			case "status" : status = OrderStatusEnum.getEnum(Short.valueOf(pair.getValue()));
				break;
			case "sign" : sign = pair.getValue();
				break;
		}

	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMerchantApiId() {
		return merchantApiId;
	}

	public void setMerchantApiId(Long merchantApiId) {
		this.merchantApiId = merchantApiId;
	}

	public Long getApiId(){
		return apiId;
	}

	public void setApiId(Long apiId) {
		this.apiId = apiId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayCurrency() {
		return payCurrency;
	}

	public void setPayCurrency(String payCurrency) {
		this.payCurrency = payCurrency;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getReceiveCurrency() {
		return receiveCurrency;
	}

	public void setReceiveCurrency(String receiveCurrency) {
		this.receiveCurrency = receiveCurrency;
	}

	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrderRequestId() {
		return orderRequestId;
	}

	public void setOrderRequestId(Long orderRequestId) {
		this.orderRequestId = orderRequestId;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "OrderCallback {" +
				"userId=" + merchantId +
				", merchantApiId=" + apiId +
				", orderId=" + orderId  +
				", payCurrency=" + payCurrency +
				", payAmount=" + payAmount +
				", receiveCurrency=" + receiveCurrency +
				", receiveAmount=" + receiveAmount +
				", receivedAmount=" + receivedAmount +
				", description=" + description +
				", orderRequestId=" + orderRequestId +
				", status=" + status.getName() +
				", sign='" + sign  +
				'}';
	}

}
