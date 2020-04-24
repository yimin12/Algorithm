/**
 * 
 */
package practise.IOtest;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午2:03:32
* Description:
*/

public class Reader {

	// naive one
	public void readFile() throws IOException{
		FileReader reader = new FileReader("E:\\my");
		for(;;) {
			int n = reader.read();
			if(n == -1) {
				break;
			}
			System.out.println((char)n);
		}
		reader.close();
	}
	
//	public void readFile() throws IOException {
//	    try (Reader reader = new FileReader("src/readme.txt", StandardCharsets.UTF_8)) {
//	        char[] buffer = new char[1000];
//	        int n;
//	        while ((n = reader.read(buffer)) != -1) {
//	            System.out.println("read " + n + " chars.");
//	        }
//	    }
//	}
}
