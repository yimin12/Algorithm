/**
 * 
 */
package practise.reflect;


import java.lang.reflect.Method;

import practise.reflect.bean.User;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午9:23:22
* Description:
*/

public class Demo06 {

	public static void test01() {
		User user = new User();
		long startTime = System.currentTimeMillis();
		
		for(int i = 0; i < 1000000000L; i++) {
			user.getUsername();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("test01's consuming time is " + (endTime - startTime));
	}
	
	public static void test02() throws Exception{
		User user = new User();
		Class<User> clz = (Class<User>) user.getClass();
		Method method = clz.getDeclaredMethod("getUsername", null);
		// if it is private 
		method.setAccessible(true);
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 1000000000L; i++){
			method.invoke(user, null);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("test02's consuming time is " + (endTime - startTime));
	}
	
	
	public static void main(String[] args) throws Exception{
		test01();
		test02();
	}
	
	/**
	 *		 test01's consuming time is 491   test02's consuming time is 1325
	 */
}
