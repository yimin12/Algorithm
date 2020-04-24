/**
 * 
 */
package practise.IOtest;

import java.io.CharArrayWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午2:16:49
* Description:
*/

public class TestWrite {

	public static void main(String[] args) throws IOException {
		
		try(Writer writer = new FileWriter("readme.txt")){
			writer.write("H"); // char
			writer.write("Hello".toCharArray()); // char[] 
			writer.write("Hello"); // String
		}
		
		try(CharArrayWriter writer = new CharArrayWriter()){
			writer.write(65);
			writer.write(66);
			writer.write(67);
			char[] data = writer.toCharArray();
			System.out.println(Arrays.toString(data));
		}
		
		StringWriter buffer = new StringWriter();
		try(PrintWriter pw = new PrintWriter(buffer)){
			pw.println("Hello");
			pw.println(12345);
			pw.println(true);
		}
		System.out.println(buffer.toString());
	}
}
