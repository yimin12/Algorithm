/**
 * 
 */
package williamsNotebook.easy.binarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��8�� ����10:48:45
* Description: 
* 	Find the Closest Number in sorted array with specific target, if the difference
* 	are the same, return the left one. If the given array is null or empty, return -1
*/

/**
 * @author 61771
 *
 */
public class Closest {
	
	public int closest(int[] array, int target) {
		if(array == null || array.length == 0) {
			return -1;
		}
		int left = 0, right = array.length - 1;
//		Find the closest two value of target
		while(left < right - 1) {
			int mid = (right - left)/2 + left;
			if(array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if(Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
			return left;
		}
		return right;
	}
}
