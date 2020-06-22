package net.symbiosis.common.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypter {

	public static String decryptData(String key, String encData) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encData.getBytes()));
			return new String(original).trim();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String encryptData(String key, String data) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] original = Base64.encodeBase64(cipher.doFinal(data.getBytes()));
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}