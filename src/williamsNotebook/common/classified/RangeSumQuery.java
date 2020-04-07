/**
 * 
 */
package williamsNotebook.common.classified;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午2:55:58
* Description:
* 	Given an integer array nums, find the sum of the elements between indices i and j (i≤j), inclusive.
* 	Given nums = [-2, 0, 3, -5, 2, -1]
*   sumRange(0, 2) - 1
*/
public class RangeSumQuery {

	// Implementation of Prefix Sum to get range sum
	private int[] sums;
	public RangeSumQuery(int[] nums) {
		if(nums.length == 0) return;
		sums = new int[nums.length];
		sums[0] = nums[0];
		for(int i = 1; i < nums.length; i++) {
			sums[i] = sums[i-1] + nums[i];
		}
	}
	public int sumRange(int i, int j) {
		if(i == 0) return sums[j];
		return sums[j] - sums[i-1];
	}
}
