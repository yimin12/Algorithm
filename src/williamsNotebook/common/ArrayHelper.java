/**
 * 
 */
package williamsNotebook.common;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月23日 下午5:25:54
* Description:
*/
// can generate variable 
public class ArrayHelper {

	@SafeVarargs
	static <T> T[] asArrays(T... objs) {
		return objs;
	}
	
	public static void main(String[] args) {
		String[] ss = ArrayHelper.asArrays("a", "b", "c");
		Integer[] ns = ArrayHelper.asArrays(1, 2, 3);
		System.out.println(Arrays.toString(ss));
		System.out.println(Arrays.toString(ns));
	}
}
