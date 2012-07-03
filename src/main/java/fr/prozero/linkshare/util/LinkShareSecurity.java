package fr.prozero.linkshare.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public enum LinkShareSecurity {
	INSTANCE;
	private final SecretKey secretLsKey;
	
	private LinkShareSecurity() {
		String privateKey = null;
		try {
			Properties prop = new Properties();
			prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ls_security.properties"));
			privateKey = prop.getProperty("linkshare.private.key");			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		secretLsKey = new SecretKeySpec(Base64.decodeBase64(privateKey), "AES");		
	}

	public SecretKey getSecretLsKey() {
		return secretLsKey;
	}	
}
