package lt.spectrofinance.spectrocoin.merchantclient.domain;

import org.apache.http.message.BasicNameValuePair;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */
public class CreateOrderRequest {

	private Long userId;
	private Long merchantApiId;
	private String orderId;
	private String payCurrency;
	private BigDecimal payAmount;
	private String receiveCurrency;
	private BigDecimal receiveAmount;
	private String description;
	private String culture;
	private String callbackUrl;
	private String successUrl;
	private String failureUrl;

	private static NumberFormat numberFormatter;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	private static NumberFormat getNumberFormatter() {
		if (numberFormatter == null) {
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
			otherSymbols.setDecimalSeparator('.');
			numberFormatter = new DecimalFormat("0.0#######", otherSymbols);
		}
		return numberFormatter;
	}

	public ArrayList<BasicNameValuePair> getParameters(){

		ArrayList<BasicNameValuePair> result = new ArrayList<BasicNameValuePair>();

		//userId
		result.add(new BasicNameValuePair("userId", String.valueOf(userId)));

		//merchantApiId
		result.add(new BasicNameValuePair("merchantApiId", String.valueOf(merchantApiId)));

		//orderId
		result.add(new BasicNameValuePair("orderId", orderId == null ? "" : orderId));

		//payCurrency
		result.add(new BasicNameValuePair("payCurrency", payCurrency == null ? "" : payCurrency));

		//payAmount
		String payAmount = "0.0";
		if(this.payAmount != null){
			payAmount = getNumberFormatter().format(this.payAmount);
		}
		result.add(new BasicNameValuePair("payAmount", payAmount));

		//receiveCurrency
		result.add(new BasicNameValuePair("receiveCurrency", receiveCurrency == null ? "" : receiveCurrency));

		//receiveAmount
		String receiveAmount = "0.0";
		if(this.receiveAmount != null){
			receiveAmount = getNumberFormatter().format(this.receiveAmount);
		}
		result.add(new BasicNameValuePair("receiveAmount", receiveAmount));

		//description
		result.add(new BasicNameValuePair("description", description != null ? description : ""));

		//culture
		result.add(new BasicNameValuePair("culture", culture != null ? culture : ""));

		//callbackUrl
		result.add(new BasicNameValuePair("callbackUrl", callbackUrl != null ? callbackUrl : ""));

		//successUrl
		result.add(new BasicNameValuePair("successUrl", successUrl != null ? successUrl : ""));

		//failureUrl
		result.add(new BasicNameValuePair("failureUrl", failureUrl != null ? failureUrl : ""));

		return result;
	}
}
