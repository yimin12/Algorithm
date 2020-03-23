/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;
import java.util.Comparator;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月21日 下午4:05:07
* Description:
* 	Given an integer array, find a subarray with sum closest to zero. Return the indexes of the 
* 	first number and last number.
* Example:
* 	Given [-3, 1, 1, -3, 5], return [0, 2], [1, 3], [1, 1], [2, 2] and [0, 4].
*/
public class SubarraySumClosest {

	static class Pair{
		int sum, index;
		public Pair(int sum, int index) {
			this.sum = sum;
			this.index = index;
		}
	}
	
	// you can record the index while calculating the sum, you can only return one pair if there have multiple solutions
	// because we need sort it and keep the index
	public int[] subarraySumClosest(int[] nums) {
		int[] res = new int[2];
		if(nums == null || nums.length == 0) return res;
		int n = nums.length;
		if(n == 1) {
			res[0] = res[1] = 0;
			return res;
		}
		Pair[] sums = new Pair[n + 1];
		int prev = 0;
		sums[0] = new Pair(0, 0);
		for(int i = 1; i <= n; i++) {
			sums[i] = new Pair(prev + nums[i-1], i);
			prev = sums[i].sum;
		}
		Arrays.sort(sums, new Comparator<Pair>() {
			public int compare(Pair a, Pair b) {
				return a.sum - b.sum;
			}
		});
		int ans = Integer.MAX_VALUE;
		for(int i = 1; i <= n; i++) {
			if(ans > sums[i].sum - sums[i-1].sum) {
				ans = sums[i].sum - sums[i-1].sum;
				int[] temp = new int[] {sums[i].index - 1, sums[i - 1].index - 1};
				Arrays.sort(temp);
				res[0] = temp[0] + 1;
				res[1] = temp[1];
			}
		}
		return res;
	}
}
