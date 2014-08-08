package lt.spectrofinance.spectrocoin.merchantclient.domain;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */
public class ApiError extends MerchantAPIResponse {

	private Long code;
	private String message;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApiError{" + "code=" + code + ", message='" + message + '\'' + '}';
	}
}
