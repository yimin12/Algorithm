/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午1:42:34
* Description:
* 	Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
* 	Return the sum of the three integers.
*/
public class ThreeSumClosest {

	public int threeSumClosest(int[] nums, int target) {
		if(nums == null || nums.length < 3) {
			return Integer.MAX_VALUE;
		}
		Arrays.sort(nums);
		int diff = Integer.MAX_VALUE/2;
		int length = nums.length;
		int sign = 1;
		for(int i = 0; i < length - 2; i++) {
			int left = i + 1;
			int right = length -1;
			while(left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if(sum == target) {
					return sum;
				} else if(sum < target) {
					if(target - sum < diff) {
						diff = target - sum;
						sign = -1;
					}
					left++;
				} else {
					if(target - sum < diff) {
						diff = target - sum;
						sign = 1;
					}
					right--;
				}
			}
		}
		return target + diff * sign;
	}
}
