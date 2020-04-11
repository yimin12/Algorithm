/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午12:14:50
* Description:
* 	Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.
* 	Return the difference between the sum of the two integers and the target.
* Example:	
* 	Given array nums = [-1, 2, 1, -4], and target = 4.
* 	The minimum difference is 1. (4 - (2 + 1) = 1).
*/

public class TwoSumClosest {

	// Time: O(nlogn + n)
	public int twoSumClosest(int[] nums, int target) {
		if(nums == null || nums.length < 2) return Integer.MAX_VALUE;
		if(nums.length == 2) {
			return Math.abs(target - (nums[0] + nums[1]));
		}
		Arrays.sort(nums);
		int left = 0;
		int right = nums.length - 1;
		int minDiff = Integer.MAX_VALUE;
		while(left < right) {
			int sum = nums[left] + nums[right];
			int diff = Math.abs(sum - target);
			if(diff == 0) {
				return 0;
			}
			if(diff < minDiff) {
				minDiff = diff;
			}
			if(sum > target) {
				right--;
			} else {
				left++;
			}
		}
		return minDiff;
	}
}
