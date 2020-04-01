/**
 * 
 */
package williamsNotebook.easy.random;

import java.util.Random;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 上午12:40:44
* Description:
* 	Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
* 	The array size can be very large. Solution that uses too much extra space will not pass the judge
* Example:
* 	int[] nums = new int[] {1,2,3,3,3};
* 	Solution solution = new Solution(nums);
* // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
* 	solution.pick(3);
* // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
* 	solution.pick(1);
*/

public class RandomPickIndex {

	// Reservoir Sampling
//	To those who don't understand why it works. Consider the example in the OJ
//	{1,2,3,3,3} with target 3,you want to select 2,3,4 with a probability of 1/3 each.
//	2 : It's probability of selection is 1 * (1/2) * (2/3) = 1/3
//	3 : It's probability of selection is (1/2) * (2/3) = 1/3
//	4 : It's probability of selection is just 1/3
	
	private Random rand;
	private int[] nums;
	private int K = 1;
	public RandomPickIndex(int[] nums) {
		rand = new Random();
		this.nums = nums;
	}
	public int pick(int target) {
		int res = -1;
		int count = 0;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == target) {
				count++;
				if(rand.nextInt(count) < K) {
					res = i;
				}
			}
		}
		return res;
	}
}
