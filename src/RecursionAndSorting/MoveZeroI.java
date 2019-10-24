/**
 * 
 */
package RecursionAndSorting;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月8日 下午10:06:53
* Description:
* 	Move 0s to the right end of the array, 
* 	no need to keep the relative order of the elements in the original array
*/

/**
 * @author 61771
 *
 */
public class MoveZeroI {

	public int[] moveZero(int[] array) {
		if(array == null || array.length == 0) return array;
		int left = 0;
		int right = array.length - 1;
		while(left <= right) {
			if(array[left] != 0) {
				left++;
			} else if (array[right] == 0) {
				right--;
			} else {
				swap(array, left, right);
			}
		}
		return array;
	}
	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
