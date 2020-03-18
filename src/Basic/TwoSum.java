/**
 * 
 */
package Basic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����5:36:10
* Description:
* 	Determine if there exist two elements in a given array, the sum of which is 
* 	the given target number.
* Assumption:
* 	The given array is not null and has length of at least 2
* Examples:
* 	A = {1, 2, 3, 4}, target = 5, return true (1 + 4 = 5)
* 	A = {2, 4, 2, 1}, target = 4, return true (2 + 2 = 4)
* 	A = {2, 4, 1}, target = 4, return false
*/

public class TwoSum {
//	Method 1: sort the array first, then use two pointers
	public boolean existSum(int[] array, int target) {
//		Assumption: array is not null and has size >= 2
		Arrays.sort(array);
		int left = 0;
		int right = array.length - 1;
		while(left < right) {
			int sum = array[left] + array[right];
			if(sum == target) {
				return true;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
		}
		return false;
	}
//	Method 2: use HashSet to record the previous traversed values
	public boolean existSumII(int[] array, int target) {
		Set<Integer> set = new HashSet<Integer>();
		for(int num : array) {
			if(set.contains(target - num))return true;
			set.add(num);
		}
		return false;
	}
}
