/**
 * 
 */
package practise.encriyption;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月25日 下午10:05:02
* Description:
*/
public class RSA {

	
	public static void main(String[] args) throws Exception{
		byte[] plain = "Hello, encrpty use RSA".getBytes();
		Person alice = new RSA().new Person("Alice");
		byte[] pk = alice.getPublicKey();
		System.out.println(String.format("public key: %x", new BigInteger(1, pk)));
		byte[] encrypted = alice.encrypt(plain);
		System.out.println(String.format("encrypted: %x", new BigInteger(1, encrypted)));
		byte[] sk = alice.getPrivateKey();
        System.out.println(String.format("private key: %x", new BigInteger(1, sk)));
        byte[] decrypted = alice.decrypt(encrypted);
        System.out.println(new String(decrypted, "UTF-8"));
	}
	
	class Person{
		String name;
		PrivateKey sk;
		PublicKey pk;
		
		public Person(String name) throws GeneralSecurityException{
			this.name = name;
			// Generate public key and private from RSA
			KeyPairGenerator kpGen = KeyPairGenerator.getInstance("RSA");
			kpGen.initialize(1024);
			KeyPair kp = kpGen.generateKeyPair();
			this.sk = kp.getPrivate();
			this.pk = kp.getPublic();
		}
		
		byte[] getPrivateKey() {
			return this.sk.getEncoded();
		}
		
		byte[] getPublicKey() {
			return this.pk.getEncoded();
		}
		// encrypt the public key
		byte[] encrypt(byte[] message) throws GeneralSecurityException{
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, this.pk);
			return cipher.doFinal(message);
		}
		// encrypt the private key
		byte[] decrypt(byte[] input) throws GeneralSecurityException{
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, this.sk);
			return cipher.doFinal(input);
		}
		
	}
}
