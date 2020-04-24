/**
 * 
 */
package practise.IOtest;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月22日 下午10:43:07
* Description:
*/
public class IterateAllFiles {

	public static void main(String[] args) {
		File f = new File("E:\\learning material");
		File[] f1 = f.listFiles();
		printFiles(f1);
		File[] f2 = f.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".exe");
			}
		});
		
		// Test 2
		Path p1 = Paths.get(".", "project", "study");
		System.out.println(p1);
		Path p2 = p1.toAbsolutePath();
		System.out.println(p2);
		Path p3 = p2.normalize(); 
		System.out.println(p3);
		File f11 = p3.toFile(); // 转换为File对象
		System.out.println(f11);
		for (Path p : Paths.get("..").toAbsolutePath()) { // 可以直接遍历Path
            System.out.println("  " + p);
        }
	}
	static void printFiles(File[] files) {
		System.out.println("==============");
		if(files!= null) {
			for(File file : files) {
				System.out.println(file);
			}
		}
		System.out.println("==================");
	}
}
