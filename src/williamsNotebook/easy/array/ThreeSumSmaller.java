/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 下午2:12:14
* Description: LeetCode 259
* 	Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n 
* 	that satisfy the condition nums[i] + nums[j] + nums[k] < target.
* Example:
* 	Input: nums = [-2,0,1,3], and target = 2 Output: 2 
* 	Explanation: Because there are two triplets which sums are less than 2: [-2,0,1] [-2,0,3]
*/
public class ThreeSumSmaller {
	
	// Two Pointer with sorting number
	public int threeSumSmaller(int[] nums, int target) {
		if(nums == null || nums.length == 0) {
			return -1;
		}
		Arrays.sort(nums);
		int count = 0;
		for(int i = 0; i < nums.length - 2; i++) {
			int j = i + 1;
			int k = nums.length - 1;
			while(j < k) {
				if(nums[i] + nums[j] + nums[k] >= target) {
					k--;
				} else {
					count += k - j;
					j++;
				}
			}
		}
		return count;
	}
}
