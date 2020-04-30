/**
 * 
 */
package practise.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import practise.reflect.annotation.SxtField;
import practise.reflect.annotation.SxtTable;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午9:10:13
* Description:
*/
public class Demo05 {

	public static void main(String[] args) {
		try {
			Class clz = Class.forName("practise.reflect.annotation.SxtStudent");
			Annotation[] annotations = clz.getAnnotations();
			
			for(Annotation a : annotations) {
				System.out.println(a);
			}
			
			SxtTable st = (SxtTable)clz.getAnnotation(SxtTable.class);
			System.out.println(st.value());
			
			Field f = clz.getDeclaredField("studentName");
			SxtField sxtField = f.getAnnotation(SxtField.class);
			System.out.println(sxtField.columnName() + "--" + sxtField.type() + "--" + sxtField.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
