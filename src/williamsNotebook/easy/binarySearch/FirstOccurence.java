/**
 * 
 */
package williamsNotebook.easy.binarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��8�� ����10:21:00
* Description: 
* 	Return the index of the first occurrence of a specific value, if it is not exist, return -1
*/

/**
 * @author 61771
 *
 */
public class FirstOccurence {

	public int firstOccur(int[] array, int target) {
		if(array == null || array.length == 0) {
			return -1;
		}
		int left = 0, right = array.length - 1;
//		We need to use left<right -1 to ensure that there is no infinite loop,
//		Think about the case of left == right - 1; then mid always equal to left,
//		it will possible picking [mid, right] for the next round, so it will become infinite loop
		while(left < right - 1) {
			int mid = left + (right - left)/2;
			if (array[mid] >= target) {
				right = mid;
			} else {
				left = mid;
			}
		}
//		Check whether it is exist, the candidates will be reduced to two, check left first, then right, then gg
		if(array[left] == target) {
			return left;
		} else if (array[right] == target) {
			return right;
		}
		return -1;
	}
}
