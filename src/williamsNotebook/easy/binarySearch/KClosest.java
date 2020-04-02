/**
 * 
 */
package williamsNotebook.easy.binarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��8�� ����2:02:26
* Description: Find k closest value
*/

/**
 * @author 61771
 *
 */
public class KClosest {
	public int[] kClosest(int[] array, int target, int k) {
		if(array == null || array.length == 0) {
			return array;
		}
		if(k == 0) {
			return new int[0];
		}
//		First step: find the index of largest smaller or equal element
//		right = left + 1
//		These two should the closest or equal element
		int left = largestSmallerEqual(array, target);
		int right = left + 1;
		int[] result = new int[k];
//		linear scan k times to find the solution
		for(int i = 0; i < k; i++) { // left might return -1, if nothing found
			if(right >= array.length || left >= 0 && target - array[left] < target - array[right]) {
//				we can advance the left pointer when 
//				1.right pointer is already out of bound
//				2.neither right pointer and nor left pointer are out of bound, and 
//				  array[left] is closer than array[right]
				result[i] = array[left--];
			} else {
//				we can advance the right pointer when 
//				1. left pointer is out of bound, which means all value are larger than target
//				2.neither right pointer and nor left pointer are out of bound, and 
//				  array[right] is closer than array[left]
				result[i] = array[right++];
			}
		}
		return result;
	}
	private int largestSmallerEqual(int[] array, int target) {
//		Find the largest smaller or the first equal element's index in the array
		int left = 0;
		int right = array.length - 1;
		while(left < right - 1) {
			int mid = left + (right - left)/2;
			if(array[mid] <= target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if(array[right] <= target) {
			return right;
		} else if(array[left] <= target) {
			return left;
		}
		return -1;
	}
}
