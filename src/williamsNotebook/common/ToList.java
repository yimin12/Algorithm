/**
 * 
 */
package williamsNotebook.common;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月21日 下午12:48:50
* Description:
*/

/**
 * @author 61771
 *
 */
public class ToList {

	public static List<Integer> toList(int[] array){
		List<Integer> res = new ArrayList<Integer>();
		if(array == null || array.length == 0) return res;
		for(int i : array) {
			res.add(i);
		}
		return res;
	}
}
