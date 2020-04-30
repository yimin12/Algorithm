/**
 * 
 */
package practise.encriyption;

import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月25日 下午5:56:42
* Description:
*/

public class AECEncrypt {

	public static void main(String[] args) throws Exception{
		 // 	原文:
        String message = "Hello, world!";
        System.out.println("Message: " + message);
        // 	128位密钥 = 16 bytes Key:
        byte[] key = "1234567890abcdef".getBytes("UTF-8");
        //	 加密:
        byte[] data = message.getBytes("UTF-8");
        byte[] encrypted = encrypt(key, data);
        System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
        // 	解密:
        byte[] decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted: " + new String(decrypted, "UTF-8"));
	}
	
	// encrypt
	public static byte[] encrypt(byte[] key, byte[] input) throws GeneralSecurityException{
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKey keySpec = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		return cipher.doFinal(input);
	}
	// decrypt
	public static byte[] decrypt(byte[] key, byte[] input) throws GeneralSecurityException{
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		SecretKey keySpec = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		return cipher.doFinal(input);
	}
	
}
