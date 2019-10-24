/**
 * 
 */
package Common;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月22日 下午4:31:21
* Description:
* 	Use the least number of comparisons to get the largest and smallest number in the given integer array. 
* 	Return the largest number and the smallest number.
* Assumptions:
* 	The given array is not null and has length of at least 1
* Examples:
* 	{2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1].
*/

public class LargestAndSmallest {
//	key insight = divide the whole array into an large array and a short array
	public int[] largestAndSmallest(int[] array) {
		int n = array.length;
//		indeces (x, n-1-x) will be paired up, the larger numbers of each pair will be put on the left
//		side, so after comparisons, the left half of the array are the larger values for each pairs
//		The right half of the array are the smaller values for each pairs
		for(int i = 0; i < n / 2; i++) {
			if(array[i] < array[n-1-i]) {
				swap(array, i, n-1-i);
			}
		}
		return new int[] {largest(array, 0, (n-1)/2), smallest(array, n/2, n-1)};
	}
	private int largest(int[] array, int left, int right) {
		int largest = array[left];
		for(int i = left + 1; i <= right; i++) {
			largest = Math.max(largest,  array[i]);
		}
		return largest;
	}
	private int smallest(int[] array, int left, int right) {
		int smallest = array[left];
		for(int i = left +1; i <= right; i++) {
			smallest = Math.min(smallest, array[i]);
		}
		return smallest;
	}
	private void swap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
}
