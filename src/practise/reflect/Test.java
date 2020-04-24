/**
 * 
 */
package practise.reflect;

import java.lang.reflect.Field;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月24日 下午3:50:40
* Description:
*/

public class Test {

	public static void main(String[] args) throws Exception {
		String name = "ReBa";
		int age = 29;
		
		LaoPo wife = new LaoPo();
		
		// use reflection
		
		Class clz = wife.getClass();
		Field wname = clz.getDeclaredField("name");
		Field wAge = clz.getDeclaredField("age");
		
		wname.setAccessible(true);
		wAge.setAccessible(true);
		
		wname.set(wife, "Qinqin");
		wAge.set(wife, 31);
		
		System.out.println(wife.getName());
		System.out.println(wife.getAge());
	}
}


