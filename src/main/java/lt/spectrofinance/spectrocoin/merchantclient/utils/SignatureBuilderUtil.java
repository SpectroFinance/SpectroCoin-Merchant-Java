package lt.spectrofinance.spectrocoin.merchantclient.utils;

import lt.spectrofinance.spectrocoin.merchantclient.domain.IOrderCallback;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

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
public class SignatureBuilderUtil {

	private static NumberFormat numberFormatter;

	public static String getValueForSign(IOrderCallback orderCallback){

		ArrayList<BasicNameValuePair> result = new ArrayList<BasicNameValuePair>();

		//merchantId
		result.add(new BasicNameValuePair("merchantId", String.valueOf(orderCallback.getMerchantId())));

		//apiId
		result.add(new BasicNameValuePair("apiId", String.valueOf(orderCallback.getApiId())));

		//orderId
		result.add(new BasicNameValuePair("orderId", orderCallback.getOrderId()));

		//currency
		result.add(new BasicNameValuePair("payCurrency", orderCallback.getPayCurrency()));

		//payAmount
		String payAmount = "";
		if(orderCallback.getPayAmount() != null){
			payAmount = getNumberFormatter().format(orderCallback.getPayAmount());
		}
		result.add(new BasicNameValuePair("payAmount", payAmount));

		//receiveCurrency
		result.add(new BasicNameValuePair("receiveCurrency", orderCallback.getReceiveCurrency()));

		//receiveAmount
		String receiveAmount = "";
		if(orderCallback.getReceiveAmount() != null){
			receiveAmount = getNumberFormatter().format(orderCallback.getReceiveAmount());
		}
		result.add(new BasicNameValuePair("receiveAmount", receiveAmount));

		//receivedAmount
		String receivedAmount = "";
		if(orderCallback.getReceivedAmount() != null){
			receivedAmount = getNumberFormatter().format(orderCallback.getReceivedAmount());
		}
		result.add(new BasicNameValuePair("receivedAmount", receivedAmount));

		//description
		result.add(new BasicNameValuePair("description", orderCallback.getDescription()));

		//orderRequestId
		result.add(new BasicNameValuePair("orderRequestId", String.valueOf(orderCallback.getOrderRequestId())));

		//status
		result.add(new BasicNameValuePair("status", String.valueOf(orderCallback.getStatus().getCode())));

		return URLEncodedUtils.format(result, "UTF-8");
	}

	private static NumberFormat getNumberFormatter() {
		if (numberFormatter == null) {
			DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
			otherSymbols.setDecimalSeparator('.');
			numberFormatter = new DecimalFormat("0.0#######", otherSymbols);
		}
		return numberFormatter;
	}
}
