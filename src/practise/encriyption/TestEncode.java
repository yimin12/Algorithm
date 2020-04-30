/**
 * 
 */
package practise.encriyption;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月25日 下午1:12:46
* Description:
*/

public class TestEncode {

	public static void main(String[] args) {
		String encode = URLEncoder.encode("我是黄益敏", StandardCharsets.UTF_8);
		System.out.println(encode);
		
		String decode = URLDecoder.decode("%E6%88%91%E6%98%AF%E9%BB%84%E7%9B%8A%E6%95%8F", StandardCharsets.UTF_8);
		System.out.println(decode);
	}
}
