/**
 * 
 */
package practise.IOtest;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 上午11:13:39
* Description:
*/

public class TestOutput {
	
	public static void main(String[] args) throws IOException {
		byte[] data;
		try(ByteArrayOutputStream output = new ByteArrayOutputStream()){
			output.write("Hello ".getBytes("UTF-8"));
			output.write("world!".getBytes("UTF-8"));
			data = output.toByteArray();
		}
		System.out.println(new String(data, "UTF-8"));
	}

	public void writeFile() throws IOException{
		try(OutputStream output = new FileOutputStream("E:\\my")) {
			output.write("Hello".getBytes("UTF-8"));
		}
	}
}
