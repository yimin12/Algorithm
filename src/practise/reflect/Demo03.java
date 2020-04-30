/**
 * 
 */
package practise.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


import practise.reflect.bean.User;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午4:51:15
* Description:
*/

public class Demo03 {

	public static void main(String[] args) {
		String path = "practise.reflect.bean.User";
		try {
			// after jdk 9, User u = clazz.newInstance() has been deprecated
			Class<User> clz = (Class<User>) Class.forName(path);
			Constructor<User> constructor = clz.getDeclaredConstructor(int.class, int.class, String.class);
			User user = constructor.newInstance(1001, 16, "Yimin");
			System.out.println(user.toString());
			
			
			Method method = clz.getDeclaredMethod("setUsername", String.class);
			method.invoke(user, "yimin Huang");
			System.out.println(user.toString());
			
			Constructor<User> constructor2 = clz.getConstructor();
			User user2 = constructor2.newInstance();
			
			Field field = clz.getDeclaredField("username");
			field.setAccessible(true);
			field.set(user2, "The shy");
			System.out.println(user.toString());
			System.out.println(user2.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
