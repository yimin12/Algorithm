/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 上午11:04:46
* Description:
* 	Given an array nums _and a value _val, remove all instances of that value in-place and return the new length.
* 	Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
* 	The order of elements can be changed. It doesn't matter what you leave beyond the new length.
* Example:
* 	Given nums = [3,2,2,3], val = 3, Your function should return length = 2, with the first two elements of nums being 2.
* 	It doesn't matter what you leave beyond the returned length.
*/
public class RemoveElements {

	// Two Pointer move in the same direction
	// Time: O(n), Space: O(1) - Assume the array has a total of n elements, both i and j traverse at most n steps.
	public int removeElements(int[] array, int target) {
		if(array == null || array.length == 0) return 0;
		// elements on the left of slow is answer, elements between slow and fast are useless elements 
		int slow = 0; 
		for(int fast = 0; fast < array.length; fast++) {
			if(array[fast] != target) {
				array[slow++] = array[fast];
			}
		}
		return slow;
	}
	// Two Pointer move in reversed direction
	// Time: O(n), Space: O(1)
	public int removeElementsII(int[] array, int target) {
		if(array == null || array.length == 0) return 0;
		int left = 0, right = array.length-1;
		while(left <= right) {
			if(array[left] == target) {
				array[right] = array[left];
				right--;
			} else {
				left++;
			}
		}
		return left;
	}
}
