package fr.prozero.linkshare.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

public class AESCrypter {
	private static final String UTF8_CHARSET = "UTF-8";
	private Cipher cipher;
	private SecretKey secretKey;

	public AESCrypter(SecretKey key) {
		try {
			secretKey = key;
			cipher = Cipher.getInstance("AES");
		} catch (javax.crypto.NoSuchPaddingException e) {
		} catch (java.security.NoSuchAlgorithmException e) {
		}
	}

	public String encrypt(String str) {
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			// Encode the string into bytes using utf-8
			byte[] utf8b = str.getBytes(UTF8_CHARSET);

			// Encrypt
			byte[] enc = cipher.doFinal(utf8b);

			// Encode bytes to base64 to get a string
			return Base64.encodeBase64String(enc);
		} catch (javax.crypto.BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (InvalidKeyException e) {
		}
		return null;
	}

	public String decrypt(String str) {
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			// Base64 Decode
			byte[] enc = Base64.decodeBase64(str);

			// Decrypt
			byte[] dec = cipher.doFinal(enc);

			// Decode using utf-8
			return new String(dec, UTF8_CHARSET);
		} catch (javax.crypto.BadPaddingException e) {
		} catch (IllegalBlockSizeException e) {
		} catch (UnsupportedEncodingException e) {
		} catch (InvalidKeyException e) {
		}
		return null;
	}
}
