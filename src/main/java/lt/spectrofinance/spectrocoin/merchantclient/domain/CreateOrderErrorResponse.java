package lt.spectrofinance.spectrocoin.merchantclient.domain;

import java.util.ArrayList;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */
public class CreateOrderErrorResponse extends MerchantAPIResponse {

	private ArrayList<ApiError> errorsList;

	public ArrayList<ApiError> getErrorsList() {
		return errorsList;
	}

	public void setErrorsList(ArrayList<ApiError> errorsList) {
		this.errorsList = errorsList;
	}
}
