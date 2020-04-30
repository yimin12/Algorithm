/**
 * 
 */
package practise.encriyption;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月25日 下午4:41:04
* Description:
*/

public class TestHash {

	public static void main(String[] args) throws Exception{
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update("Hello".getBytes("UTF-8"));
		md.update("World".getBytes("UTF-8"));
		byte[] result = md.digest();
		System.out.println(new BigInteger(1, result).toString(16));
	}
}
