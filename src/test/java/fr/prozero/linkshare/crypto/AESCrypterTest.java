package fr.prozero.linkshare.crypto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.prozero.linkshare.util.LinkShareSecurity;

public class AESCrypterTest {

	AESCrypter crypter;
	private static final String TEST_EMAIL = "toto@gmail.com";
	private static final String TEST_EMAIL_ENCRYPTED = "mV7jlAGFLS8PSN0OovZgJQ==\r\n";
	
	@Before
	public void init() {
		/*KeyGenerator gen = KeyGenerator.getInstance("AES");
		gen.init(128);
		SecretKey key = gen.generateKey();*/	
		
		// Create encrypter/decrypter class
		crypter = new AESCrypter(LinkShareSecurity.INSTANCE.getSecretLsKey());
	}
	
	
	@Test
	public void testEncrypte() {
		Assert.assertEquals(TEST_EMAIL_ENCRYPTED, crypter.encrypt(TEST_EMAIL));
	}
	
	@Test
	public void testDecrypte() {
		Assert.assertEquals(TEST_EMAIL, crypter.decrypt(TEST_EMAIL_ENCRYPTED));
	}
	
}
