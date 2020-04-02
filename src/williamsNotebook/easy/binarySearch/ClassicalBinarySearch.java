/**
 * 
 */
package williamsNotebook.easy.binarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��7�� ����11:12:12
* Description: Classical Binary Search
*/

/**
 * @author 61771
 *
 */
public class ClassicalBinarySearch {
	public int binarySearch(int[] array, int target) {
		// Check the corner case, if it is invalid, return -1
		if(array == null || array.length == 0) return -1;
		int left = 0, right = array.length - 1;
		while(left<=right) {
			int mid = (right-left)/2 + left; // In case of overflow
			if(array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid+1;
			} else {
				right = mid - 1;
			}
		}
		return -1;			
	}
}
