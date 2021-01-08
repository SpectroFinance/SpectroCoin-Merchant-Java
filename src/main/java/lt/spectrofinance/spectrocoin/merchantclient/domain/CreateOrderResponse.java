package lt.spectrofinance.spectrocoin.merchantclient.domain;

import java.math.BigDecimal;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */
public class CreateOrderResponse extends MerchantAPIResponse {

	private Long orderRequestId;
	private String orderId;
	private String payCurrency;
	private BigDecimal payAmount;
	private String receiveCurrency;
	private BigDecimal receiveAmount;
	private String depositAddress;
	private Long validUntil;
	private String redirectUrl;
	private String memo;

	public Long getOrderRequestId() {
		return orderRequestId;
	}

	public void setOrderRequestId(Long orderRequestId) {
		this.orderRequestId = orderRequestId;
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

	public String getDepositAddress() {
		return depositAddress;
	}

	public void setDepositAddress(String depositAddress) {
		this.depositAddress = depositAddress;
	}

	public Long getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Long validUntil) {
		this.validUntil = validUntil;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getMemo() {return memo;}

	public void setMemo(String memo) {this.memo = memo;}

	@Override
	public String toString() {
		return "CreateOrderResponse{" +
				"orderRequestId=" + orderRequestId +
				", orderId='" + orderId + '\'' +
				", payCurrency='" + payCurrency + '\'' +
				", payAmount=" + payAmount +
				", receiveCurrency='" + receiveCurrency + '\'' +
				", receiveAmount=" + receiveAmount +
				", depositAddress='" + depositAddress + '\'' +
				", validUntil=" + validUntil +
				", redirectUrl='" + redirectUrl + '\'' +
				", memo='" + memo + '\'' +
				'}';
	}
}
