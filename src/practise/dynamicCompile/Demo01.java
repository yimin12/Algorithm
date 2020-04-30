/**
 * 
 */
package practise.dynamicCompile;


import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午10:05:30
* Description:
*/

public class Demo01 {

	public static void main(String[] args) {
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			int result = compiler.run(null, null, null, "C:/test/myHelloWorld");
			//Runtime runtime = Runtime.getRuntime();
			//Process process = runtime.exec("java -cp C:/test/myHelloWorld");
			System.out.println(result == 0 ? "seccessed" : "failed");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
