/**
 * 
 */
package practise.IOtest;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午12:30:41
* Description:
*/

/**
 * @author 61771
 *
 */
public class MyCountInputStream {

	public static void main(String[] args) throws IOException {
		byte[] data = "hello, world!".getBytes("UTF-8");
		try(CountInputStream input = new CountInputStream(new ByteArrayInputStream(data))){
			int n;
			while((n = input.read()) != -1) {
				System.out.println((char)n);
			}
			System.out.println("Total read " + input.getBytesRead() + " bytes");
		}
	}
}

class CountInputStream extends FilterInputStream{
	
	private int count = 0;
	/**
	 * 
	 */
	public CountInputStream(InputStream in) {
		super(in);
	}
	
	public int getBytesRead() {
		return this.count;
	}
	
	public int read() throws IOException{
		int n = in.read();
		if(n!=-1) {
			this.count++;
		}
		return n;
	}
	
	public int read(byte[] b, int off, int len) throws IOException{
		int n = in.read(b, off, len);
		this.count += n;
		return n;
	}
}
