/**
 * 
 */
package practise.Annotation;

import java.lang.reflect.Field;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午5:54:26
* Description:
*/

public class Person {

	@Range(min = 1, max = 20)
	public String name;
	
	@Range(max = 10)
	public String city;
	
	@Range(min = 1, max = 100)
	public int age;
	
	public Person(String name, String city, int age) {
		this.name = name;
		this.city = city;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return String.format("{Person: name=%s, city=%s, age=%d}", name, city, age);
	}
	
	static void check(Person person) throws IllegalArgumentException, ReflectiveOperationException{
		for (Field field : person.getClass().getFields()) {
	        //	 获取Field定义的@Range:
	        Range range = field.getAnnotation(Range.class);
	        // 	如果@Range存在:
	        if (range != null) {
	            // 	获取Field的值:
	            Object value = field.get(person);
	            // 	如果值是String:
	            if (value instanceof String) {
	                String s = (String) value;
	                //	 判断值是否满足@Range的min/max:
	                if (s.length() < range.min() || s.length() > range.max()) {
	                    throw new IllegalArgumentException("Invalid field: " + field.getName());
	                }
	            }
	        }
	    }
	}
}
