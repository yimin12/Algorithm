/**
 * 
 */
package practise.IOtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午12:50:08
* Description:
*/

public class TestZip {

	// read the zip
	public static void zip() throws FileNotFoundException, IOException {
		try(ZipInputStream zip = new ZipInputStream(new FileInputStream("I love you so much"))){
			ZipEntry entry = null;
			while((entry = zip.getNextEntry())!= null) {
				String name = entry.getName();
				if(!entry.isDirectory()) {
					int n;
					while((n = zip.read()) != -1) {
						System.out.println((char)n);
					}
				}
			}
		}
	}
	
	// write the zip
	public static void unzip() throws IOException{
		File f = new File("E:\\learning material");
		File[] files = f.listFiles();
		try(ZipOutputStream zip = new ZipOutputStream(new FileOutputStream("E:\\my"))){
			for(File file:files) {
				zip.putNextEntry(new ZipEntry(file.getName()));
				// zip.write(getFiledata);
				zip.closeEntry();
			}
					
		}
	}
	
	
}
