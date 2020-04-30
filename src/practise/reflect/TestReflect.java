/**
 * 
 */
package practise.reflect;

import java.lang.reflect.Field;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月24日 下午3:06:59
* Description:
*/
public class TestReflect {

	
	public static void main(String[] args) throws Exception {
		Object p = new Person1("Yimin");
		Class c = p.getClass();
		Field f = c.getDeclaredField("name");
		f.setAccessible(true);
		Object value = f.get(p);
		System.out.println(value);
		
		
		Person1 p1 = new Person1("Yimin Huang");
		System.out.println(p1.getName());
		Class clz = p.getClass();
		Field field = clz.getDeclaredField("name");
		field.setAccessible(true);
		field.set(p1, "QinQin");
		System.out.println(p1.getName());
		
	}
}
class Person1{
	private String name;
	private int age;
	
	public Person1(String name) {
		this.name = name;
	}
	
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
