/**
 * 
 */
package practise.reflect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月28日 下午8:59:04
* Description:
*/

@Target(value={ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SxtTable {
	String value();
}
