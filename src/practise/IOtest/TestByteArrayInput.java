/**
 * 
 */
package practise.IOtest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 上午10:16:04
* Description:
*/

public class TestByteArrayInput {

	public static void main(String[] args) throws IOException {
		byte[] data = {72, 101, 108, 108, 111, 33};
		try(InputStream input = new ByteArrayInputStream(data)){
			int n;
			while((n = input.read()) != -1) {
				System.out.println((char)n);
			}
		}
		// read stream
		
		try(InputStream input = new ByteArrayInputStream(data)){
			String string = readString(input);
			System.out.println(string);
		}
	}
	
	public static String readString(InputStream input) throws IOException{
		int n;
		StringBuilder sb = new StringBuilder();
		while((n = input.read()) != -1) {
			sb.append((char)n);
		}
		return sb.toString();
	}
}
