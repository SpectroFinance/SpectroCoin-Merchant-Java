package lt.spectrofinance.spectrocoin.merchantclient.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by UAB Spectro Finance.
 * This is a sample SpectroCoin Merchant v1.0 JAVA client
 */
public class SignUtil {

	private static final String TYPE = "SHA1withRSA";

	/**
	 * @param value       UTF-8 URL Encoded and concatenated parameters
	 * @param privateKey private key
	 * @return Base64 encoded signature
	 * @throws InvalidKeySpecException  if the given key specification is inappropriate for this key factory to produce a private key.
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException       if this signature object is not initialized properly, the passed-in signature is improperly encoded or of the wrong type, if this signature algorithm is unable to process the input data provided, etc.
	 */
	public static String sign(String value, PrivateKey privateKey) throws InvalidKeySpecException, IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {

		String sing = signing(value, privateKey);
		return sing.replace("\n", "").replace("\r", "");
	}

	/**
	 * Check callback sign. Return true if the signature was verified, false if not.
	 *
	 * @param formattedValue UTF-8 URL Encoded and concatenated parameters
	 * @param responseSign   sign field value from callback response
	 * @param publicKey     public key
	 * @return true if the signature was verified, false if not
	 * @throws IOException              if an I/O error occurs.
	 * @throws NoSuchAlgorithmException if no Provider supports a Signature implementation for the SHA1withRSA or RSA algorithm.
	 * @throws InvalidKeyException      if the given key specification is inappropriate for this key factory to produce a public key.
	 * @throws SignatureException       if this signature object is not initialized properly, the passed-in signature is improperly encoded or of the wrong type, if this signature algorithm is unable to process the input data provided, etc.
	 * @throws InvalidKeySpecException  if the given key specification is inappropriate for this key factory to produce a public key.
	 */
	public static boolean checkSign(String formattedValue, String responseSign, PublicKey publicKey) throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {

		Signature verifier = Signature.getInstance(TYPE);
		verifier.initVerify(publicKey);
		verifier.update(formattedValue.getBytes());

		byte[] bytes = new BASE64Decoder().decodeBuffer(responseSign);

		return verifier.verify(bytes);
	}

	/**
	 * @param formattedValue
	 * @param privateKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 */
	private static String signing(String formattedValue, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, IOException, InvalidKeySpecException {

		Signature ourSign = Signature.getInstance(TYPE);
		ourSign.initSign(privateKey);
		ourSign.update(formattedValue.getBytes());

		return new BASE64Encoder().encode(ourSign.sign());
	}

	/**
	 * Load PKC8 encoded private key from privateKeyStream input stream.
	 *
	 * @param privateKeyStream
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PrivateKey loadPKC8EncodedPrivateKey(InputStream privateKeyStream) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] keyBytes = IOUtils.toByteArray(privateKeyStream);
		// decode private key
		PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey ourKey = keyFactory.generatePrivate(privSpec);

		return ourKey;
	}

	/**
	 * Load .pem format file stream of public key
	 * @param publicKeyStream
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static PublicKey loadPublicKey(InputStream publicKeyStream) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

		byte[] bytes = IOUtils.toByteArray(publicKeyStream);
		String keyValue = new String(bytes);

		keyValue = keyValue.replace("-----BEGIN PUBLIC KEY-----", "");
		keyValue = keyValue.replace("-----END PUBLIC KEY-----", "");

		byte[] keyBytes = new BASE64Decoder().decodeBuffer(keyValue);

		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}
}
