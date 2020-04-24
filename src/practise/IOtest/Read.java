/**
 * 
 */
package practise.IOtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 上午9:45:35
* Description:
*/
public class Read {

	// disadvantage: if IOException happened, the resources can not be closed
	public void readFileNaive() throws IOException{
		InputStream is = new FileInputStream("E:\\address");
		for(;;) {
			int n = is.read();
			if(n != -1) {
				break;
			}
			System.out.println(n);
		}
		is.close();
	}
	
	public void readFileI() throws IOException{
		InputStream is = null;
		try {
			is = new FileInputStream("E:\\address");
			int n;
			while((n = is.read()) != -1) {
				System.out.println(n);
			}
		} catch (Exception e) {
			
		} finally {
			if(is != null) {
				is.close();
			}
		}
	}
	
	public void readFileII() throws IOException{
		try(InputStream is = new FileInputStream("E:\\address")){
			int n ;
			while((n = is.read()) != -1) {
				System.out.println(n);
			}
		} //	 编译器在此自动为我们写入finally并调用close()
	}
}
