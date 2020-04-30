/**
 * 
 */
package practise.IOtest;

import java.io.File;
import java.io.IOException;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午10:20:06
* Description:
*/
public class TestReading {

	public static void main(String[] args) throws IOException {
		File file = new File("E:\\address.txt");
		System.out.println(file);
		
		File f = new File("..");
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getCanonicalPath());
        
        System.out.println(File.separator); // 根据当前平台打印"\"或"/"

        File file2 = File.createTempFile("tmp-", ".txt");
        f.deleteOnExit();
        System.out.println(file2.isFile());
        System.out.println(file2.getAbsolutePath());
	}
}
