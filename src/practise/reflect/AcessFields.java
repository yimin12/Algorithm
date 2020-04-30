/**
 * 
 */
package practise.reflect;

import java.lang.reflect.Field;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月24日 下午2:58:23
* Description:
*/
public class AcessFields {

	public static void main(String[] args) throws Exception {
		Class stdClz = Student.class;
		System.out.println(stdClz.getField("score"));
        System.out.println(stdClz.getField("name"));
        System.out.println(stdClz.getDeclaredField("grade"));
       
        Field field = String.class.getDeclaredField("value");
        System.out.println(field.getName());
        System.out.println(field.getType());
        int m = field.getModifiers();
        System.out.println(m);
	}
}

class Student extends Person{
	public int score;
	private int grade;
}

class Person{
	public String name;
}