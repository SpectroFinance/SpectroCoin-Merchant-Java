package lt.spectrofinance.spectrocoin.merchantclient.domain;

/**
 *
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 *
 */
public enum OrderStatusEnum {

		NEW((short)1, "New"),
		PENDING((short)2, "Pending"),
		PAID((short)3, "Paid"),
		FAILED((short)4, "Failed"),
		EXPIRED((short)5, "Expired"),
		TEST((short)6, "Test");

		private short code;
		private String name;

		private OrderStatusEnum(short code, String name) {
			this.code = code;
			this.name = name;

		}

		public short getCode() {
			return this.code;
		}
		public String getName() {
			return this.name;
		}

		public static OrderStatusEnum getEnum(short code) throws Exception {
			if (code == NEW.getCode()) {
				return NEW;
			} else if (code == PENDING.getCode()) {
				return PENDING;
			} else if (code == PAID.getCode()) {
				return PAID;
			} else if (code == FAILED.getCode()) {
				return FAILED;
			} else if (code == EXPIRED.getCode()) {
				return EXPIRED;
			} else if (code == TEST.getCode()) {
				return TEST;
			} else {
				throw new Exception("Unknown Order Status code: " + code);
			}
		}
}
