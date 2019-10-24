/**
 * 
 */
package BinarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月8日 上午10:42:33
* Description:
* 	Return the index of the last occurrence of a specific value, if it is not exist, return -1
*/

/**
 * @author 61771
 *
 */
public class LastOccurrence {
	
	public int lastOcur(int[] array, int target) {
		if(array == null || array.length == 0) {
			return -1;
		}
		int left = 0, right = array.length - 1;
		while(left < right - 1) {
			int mid = left + (right - left)/2;
			if(array[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if(array[right] == target) {
			return right;
		} else if(array[left] == target) {
			return left;
		}
		return -1;
		
	}
}
