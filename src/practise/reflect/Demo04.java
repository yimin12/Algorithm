/**
 * 
 */
package practise.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import practise.reflect.bean.User;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午7:37:58
* Description:
* 
* 	
*/
public class Demo04 {

	public void test01(Map<String, User> map, List<User> list) {
		System.out.println("Demo04.test01()");
	}
	public Map<String, User> test02(){
		System.out.println("Demo04.test02()");
		return null;
	}
	public static void main(String[] args) {
		
		try {
			Method m = Demo04.class.getMethod("test01", Map.class, List.class);
			Type[] t = m.getGenericParameterTypes();
			for(Type paramType : t) {
				System.out.println("#" + paramType);
				if(paramType instanceof ParameterizedType) {
					Type[] genericTypes = ((ParameterizedType) paramType).getActualTypeArguments();
					for(Type genericType : genericTypes) {
						System.out.println("GenericType is " + genericType);
					}
				}
			}
			
			Method m2 = Demo04.class.getMethod("test02", null);
			Type returnType = m2.getGenericReturnType();
			if(returnType instanceof ParameterizedType) {
				Type[] genericTypes = ((ParameterizedType)returnType).getActualTypeArguments();
				for(Type genericType : genericTypes) {
					System.out.println("The return type : " + genericType);
				}
			}
		} catch (Exception e) {
			
		}
	}
}
