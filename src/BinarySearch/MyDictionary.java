/**
 * 
 */
package BinarySearch;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月8日 下午4:07:11
* Description:
*/

/**
 * @author 61771
 *
 */
public class MyDictionary implements Dictionary{
	
	private int[] array;
	
	/**
	 * 
	 */
	public MyDictionary(int[] array) {
		this.array = array;
	}

	@Override
	public Integer get(int index) {
		if(array == null || index >= array.length) {
			return null;
		}
		return array[index];
	}
	
//	Good format to printOut
	@Override
	public String toString() {
		if(array == null) {
			return String.valueOf(null);
		}
		if(array.length <= 10) {
			return Arrays.toString(array);
		}
//		Truncate output if array is too long
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i<5; i++) {
			sb.append(array[i]).append(", ");
		}
		sb.append("......");
		for(int i = array.length - 4; i < array.length; i++) {
			sb.append(array[i]);
			if(i != array.length - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
