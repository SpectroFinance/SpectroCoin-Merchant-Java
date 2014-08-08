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

	private Long merchantId;
	private Long apiId;
	private String orderId;
	private BigDecimal payAmount;
	private BigDecimal receiveAmount;
	private String description;
	private String culture;
	private String callbackUrl;
	private String successUrl;
	private String failureUrl;

	private static NumberFormat numberFormatter;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getApiId() {
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

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
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

		//merchantId
		result.add(new BasicNameValuePair("merchantId", String.valueOf(merchantId)));

		//apiId
		result.add(new BasicNameValuePair("apiId", String.valueOf(apiId)));

		//orderId
		result.add(new BasicNameValuePair("orderId", orderId == null ? "" : orderId));

		//currency
		result.add(new BasicNameValuePair("payCurrency", "BTC"));

		//payAmount
		String payAmount = "0.0";
		if(this.payAmount != null){
			payAmount = getNumberFormatter().format(this.payAmount);
		}
		result.add(new BasicNameValuePair("payAmount", payAmount));

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
