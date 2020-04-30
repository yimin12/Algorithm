/**
 * 
 */
package practise.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午3:19:50
* Description:
*/


public class Demo02 {

	public static void main(String[] args) {
		String path = "practise.reflect.bean.User";
		try {
			Class clz = Class.forName(path);
			System.out.println(clz.getName());
			System.out.println(clz.getSimpleName());
			// test field
			Field[] fields = clz.getFields(); // can only get public field
			Field[] fields1 = clz.getDeclaredFields(); // can get every single field
			System.out.println(fields1.length);
			Field field = clz.getDeclaredField("username");
			System.out.println(field);
			
			// test method
			Method[] methods = clz.getDeclaredMethods(); // 
			Method method1 = clz.getDeclaredMethod("getUsername", null);
			Method method2 = clz.getDeclaredMethod("setUsername", String.class);
			for(Method m : methods) {
				System.out.println("The method is : " + m);
			}
			for(Field temp : fields1) {
				System.out.println("field : " + temp);
			}
			
			// test constructor
			Constructor[] constructors = clz.getConstructors();
			Constructor c = clz.getConstructor(int.class, int.class, String.class);
			System.out.println("The constructor is " + c);
			for(Constructor ct : constructors) {
				System.out.println("The temp constructor is : " + ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
