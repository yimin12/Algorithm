/**
 * 
 */
package practise.encriyption;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月25日 下午7:38:57
* Description:
*/

public class TestPassWordBasedEncryption {

	public static void main(String[] args) {
//        Security.addProvider(new BouncyCastleProvider());
//        String message = "Hello, world";
//        String password = "hello123456";
//        byte[] salt = SecureRandom.getInstanceStrong().generateSeed(16); // should keep the seed number
//        System.out.printf("salt: %32x\n", new BigInteger(1, salt));
//        
//        byte[] data = message.getBytes("UTF-8");
//        byte[] encrypted = encrypt(password, salt, data);
//        System.out.println("encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        
	}
	
	public static byte[] encrypt(String password, byte[] salt, byte[] input) throws Exception{
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory sKeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
		SecretKey skey = sKeyFactory.generateSecret(keySpec);
		PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
		Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
		cipher.init(Cipher.ENCRYPT_MODE, skey, pbeps);
		return cipher.doFinal(input);
		
	}
	
	public static byte[] decrypt(String password, byte[] salt, byte[] input) throws Exception{
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory sKeyFactory = SecretKeyFactory.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
		SecretKey skey = sKeyFactory.generateSecret(keySpec);
		PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
		Cipher cipher = Cipher.getInstance("PBEwithSHA1and128bitAES-CBC-BC");
		cipher.init(Cipher.DECRYPT_MODE, skey,pbeps);
		return cipher.doFinal(input);
	}
}
