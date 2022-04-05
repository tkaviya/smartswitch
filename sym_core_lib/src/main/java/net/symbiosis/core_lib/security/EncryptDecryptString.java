/**
 * 
 */
package net.symbiosis.core_lib.security;

import net.symbiosis.core_lib.structure.Pair;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author andrejus
 *
 */
public class EncryptDecryptString {
	
	private static final Logger log = Logger.getLogger(EncryptDecryptString.class.getSimpleName());
	
	private SecretKeySpec secretKey;

	private static final String CIPHER_AES_CBC_PKCS5PADDING = "AES/CBC/PKCS5PADDING";
	private static final Integer IV_LENGTH = 16;
	private String charsetName = UTF_8.name();
	private String digestAlgorithm = "SHA-256";
	private String secretKeyAlgorithm = "AES";

	public EncryptDecryptString() {
		init();
	}
	
	private void init() {
		MessageDigest sha = null;
		try {
			byte[] key = getStrKey().getBytes(getCharsetName());
			sha = MessageDigest.getInstance(getDigestAlgorithm());
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			setSecretKey(new SecretKeySpec(key, getSecretKeyAlgorithm()));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			log.severe("Problem initialising Encrypt/Decrypt object");
		}
	}

	public Pair<String, Boolean> encryptWithCheck(String strToEncrypt) {
		// Generate new random salt for encryption
		byte[] initializationVector = new byte[IV_LENGTH];
		new SecureRandom().nextBytes(initializationVector);
		initializationVector = new String(Base64.getEncoder().encode(initializationVector)).substring(0, 16).getBytes();

		String encryptedString = null;
		boolean success = false;
		try {
			// Initialize cipher with IV & secret key
			Cipher cipher = Cipher.getInstance(CIPHER_AES_CBC_PKCS5PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), new IvParameterSpec(initializationVector));

			// Prepend the initializationVector to the encrypted string
			encryptedString = new String(initializationVector)
				+ Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(UTF_8)));

			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.severe("Could not encrypt string " + ex.getMessage());
		}
		return new Pair<>(encryptedString, success);
	}

	public Pair<String, Boolean> decryptWithCheck(String strToDecrypt) {
		String decryptedString = null;
		boolean success = false;
		try {
			// Get the first characters of the string to decrypt and use that for the IV
			String initializationVector = strToDecrypt.substring(0, IV_LENGTH);
			strToDecrypt = strToDecrypt.substring(IV_LENGTH);

			// Initialize cipher with IV & secret key
			Cipher cipher = Cipher.getInstance(CIPHER_AES_CBC_PKCS5PADDING);
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), new IvParameterSpec(initializationVector.getBytes(UTF_8)));

			decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
			success = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			log.severe("Could not decrypt string " + ex.getMessage());
		}
		return new Pair<>(decryptedString, success);
	}

	public EncryptDecryptString(String charsetName, String digestAlgorithm, String secretKeyAlgorithm ) {
		setCharsetName(charsetName);
		setDigestAlgorithm(digestAlgorithm);
		setSecretKeyAlgorithm(secretKeyAlgorithm);
		init();
	}

	private String getStrKey() {
		File keyFile = new File("/opt/smartswitch/import/keystring.dat");
	    try (Scanner sc = new Scanner(keyFile)) {
//			return "qKT_gYB2zWp$8Ur4";
//			return "st80Eyk4Qn$.EN+rrv7^3U10Swf83tI=";
	    	return sc.nextLine().trim();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getStrIV() {
		File keyFile = new File("/opt/smartswitch/import/keystring.dat");
	    try (Scanner sc = new Scanner(keyFile)) {
//			return "qKT_gYB2zWp$8Ur4";
//			return "st80Eyk4Qn$.EN+rrv7^3U10Swf83tI=";
	    	return sc.nextLine().trim();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private SecretKeySpec getSecretKey() {
		return secretKey;
	}

	private void setSecretKey(SecretKeySpec secretKey) {
		this.secretKey = secretKey;
	}

	public String encrypt(String strToEncrypt) {
		return this.encryptWithCheck(strToEncrypt).getLeft();
	}

	public String decrypt(String strToDecrypt) {
		return this.decryptWithCheck(strToDecrypt).getLeft();
	}
	
//	public Pair<String, Boolean> encryptWithCheck(String strToEncrypt) {
//		String encryptedString = null;
//		boolean success = false;
//		try {
//			Cipher cipher = Cipher.getInstance(CIPHER_AES_CBC_PKCS5PADDING);
//			cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(), new IvParameterSpec(getStrIV().getBytes(UTF_8)));
//			encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(UTF_8)));
//			success = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.severe("Error while encrypting string.");
//		}
//
//		return new Pair<>(encryptedString, success);
//	}
//
//	public Pair<String, Boolean> decryptWithCheck(String strToDecrypt) {
//		String decryptedString = null;
//		boolean success = false;
//		try {
//			Cipher cipher = Cipher.getInstance(CIPHER_AES_CBC_PKCS5PADDING);
//			cipher.init(Cipher.DECRYPT_MODE, getSecretKey(), new IvParameterSpec(getStrIV().getBytes(UTF_8)));
//			decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
//			success = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.severe("Could not decrypt string " + e.getMessage());
//		}
//		return new Pair<>(decryptedString, success);
//	}

	public String getCharsetName() {
		return charsetName;
	}

	private void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public String getDigestAlgorithm() {
		return digestAlgorithm;
	}

	private void setDigestAlgorithm(String digestAlgorithm) {
		this.digestAlgorithm = digestAlgorithm;
	}

	public String getSecretKeyAlgorithm() {
		return secretKeyAlgorithm;
	}

	private void setSecretKeyAlgorithm(String secretKeyAlgorithm) {
		this.secretKeyAlgorithm = secretKeyAlgorithm;
	}
}
