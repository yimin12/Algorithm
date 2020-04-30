/**
 * 
 */
package practise.encriyption;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月25日 下午5:48:42
* Description:
*/

public class TestHmac {

	public static void main(String[] args) throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
		SecretKey key = keyGen.generateKey();
		byte[] skey = key.getEncoded();
		System.out.println(new BigInteger(1, skey).toString(16));
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(key);
		mac.update("HelloWorld".getBytes("UTF-8"));
		byte[] result = mac.doFinal();
		System.out.println(new BigInteger(1, result).toString());
	}
}
