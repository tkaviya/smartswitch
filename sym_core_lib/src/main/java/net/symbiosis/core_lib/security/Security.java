package net.symbiosis.core_lib.security;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.RijndaelEngine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.paddings.ZeroBytePadding;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;
import static net.symbiosis.core_lib.utilities.CommonUtilities.isNullOrEmpty;

/**
 * Created with IntelliJ IDEA.
 * User: tkaviya
 * Date: 12/8/13
 * Time: 7:05 PM
 */
public class Security {
    private static SecureRandom secureRandom;

    public static String generateIV() {
        int n = 16;
        char[] iv = new char[n];
        int c = 'A', r1;

        for (int i = 0; i < n; i++) {
            r1 = (int) (Math.random() * 3);
            switch (r1) {
                case 0:
                    c = '0' + (int) (Math.random() * 10);
                    break;
                case 1:
                    c = 'a' + (int) (Math.random() * 26);
                    break;
                case 2:
                    c = 'A' + (int) (Math.random() * 26);
                    break;
            }
            iv[i] = (char) c;
        }
        return new String(iv);
    }

    public static byte[] generateSecureRandomBytes() {

        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }

        byte[] randomBytes = new byte[SymbiosisSecurityEncryption.DEFAULT_SALT_LENGTH];

        secureRandom.nextBytes(randomBytes);

        return randomBytes;
    }

    public static String hashWithSalt(final String unencryptedStr, final byte[] salt) {
        return hashWithSalt(unencryptedStr, SymbiosisSecurityEncryption.DEFAULT_SECURITY_HASH, salt);
    }

    public static String hashWithSalt(final String input, final String encryptMode, final byte[] salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(encryptMode);
            messageDigest.reset();

            if (!isNullOrEmpty(Arrays.toString(salt))) {
                messageDigest.update(salt);
            }

            byte[] digested = messageDigest.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte aDigested : digested) {
                sb.append(Integer.toHexString(0xff & aDigested));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    public static String encryptAESCBC(String key, String initVector, String value) {
        try {
	        IvParameterSpec iv = null;
	        if (initVector != null) {
		        iv = new IvParameterSpec(initVector.getBytes(UTF_8));
	        }
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decryptAESCBC(String key, String initVector, String encrypted) {
        try {
	        IvParameterSpec iv = null;
	        if (initVector != null) {
		        iv = new IvParameterSpec(initVector.getBytes(UTF_8));
	        }
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static KeyParameter prepareKey(String encryptionKey ) {
        var givenKey = encryptionKey.getBytes(Charset.forName("ASCII"));

        final int keysize;
        if (givenKey.length <= 128 / Byte.SIZE) {
            keysize = 128;
        } else if (givenKey.length <= 192 / Byte.SIZE) {
            keysize = 192;
        } else {
            keysize = 256;
        }

        // create a 256 bit key by adding zero bytes to the decoded key
        byte[] keyData = new byte[keysize / Byte.SIZE];
        System.arraycopy(givenKey, 0, keyData, 0, Math.min(givenKey.length, keyData.length));
        return new KeyParameter(keyData);
    }

    public static String doAESECBMcryptCompatible(String encryptionKey, String data, boolean encrypt) {
        try {

            BlockCipher rijndael = new RijndaelEngine(128);
            ZeroBytePadding c = new ZeroBytePadding();
            PaddedBufferedBlockCipher pbbc = new PaddedBufferedBlockCipher(rijndael, c);
            pbbc.init(encrypt, prepareKey(encryptionKey));

            if (encrypt) {
                byte[] text = data.getBytes(Charset.forName("UTF8"));
                byte[] ciphertext = new byte[pbbc.getOutputSize(text.length)];
                int offset = 0;
                offset += pbbc.processBytes(text, 0, text.length, ciphertext, offset);
                pbbc.doFinal(ciphertext, offset);
                return Base64.encodeBase64String(ciphertext);
            } else {
                var ciphertext = Base64.decodeBase64(data.getBytes());
                pbbc.init(false, prepareKey(encryptionKey));
                byte[] decrypted = new byte[pbbc.getOutputSize(ciphertext.length)];
                int offset = 0;
                offset += pbbc.processBytes(ciphertext, 0, ciphertext.length, decrypted, offset);
                pbbc.doFinal(decrypted, offset);
                return new String(decrypted, Charset.forName("UTF8")).replaceAll("\\x00+$", "");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
