/**
 * 
 */
package practise.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午5:53:11
* Description:
*/

/**
 * @author 61771
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {

	int min() default 0;
	int max() default 255;
}
