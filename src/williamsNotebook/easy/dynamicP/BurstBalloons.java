/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 上午12:08:22
* Description:
* 	Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
* 	You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] 
* 	coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
* 	Find the maximum coins you can collect by bursting the ballons wisely.
* 	You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
* 	0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
* Example:
* 	Input: [3,1,5,8]	 Output: 167 
* 	Explanation: nums = [3,1,5,8] -->  [3,5,8] -->   [3,8]   -->   [8]  -->  []
*                 coins =  3*1*5    +   3*5*8    +   1*3*8    +   1*8*1   =  167
*/
public class BurstBalloons {

	/*
	 * 1. subproblems are overlapped, so we can use divide and conquer + cache 
	 * 2. Balloons 0, 1, ..., n - 1 What is the max value if we burst all of them[0, n - 1]?
	 * 3. Let's first consider bursting[start, end] (including start and end boundaries) Imagine index i as the last one in the interval to be bursted
	 * 		[start, ... i - 1, (i), i + 1 ... end] Before the end, we already bursted [start, i - 1] and [i + 1, end] Before the end, boundaries start - 1 , i ,
	 * 		 end + 1 are always there 
	 * 4. This helps us calculate coins without worrying about details inside[start, i - 1] and [i + 1, end] 
	 * 5. So the range can be divided into start - 1 ,maxCoin(start, i - 1), i, maxCoins(i + 1, end), end + 1
	 */
	/*
	 * 基本思想就是在区间[start, end]中循环i，i作为该区间内最后被爆的气球，这样区间被分成了左右两个区间，再依次递归地寻找 [start, i - 1] 和 [i + 1, end] 的区间。这样就变成了子问题，而子问题区间上是可能有重叠的，因此可以用memoization来优化递归。
	 */
	public int maxCoins(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int[][] dp = new int[nums.length][nums.length];
		return maxCoinsHelper(nums, 0, nums.length - 1, dp);
	} // MemorySearch
	private int maxCoinsHelper(int[] nums, int start, int end, int[][] dp) {
		// divide 
		if(start > end) return 0;
		if(dp[start][end] != 0) return dp[start][end];
		// Generate temp result in each level of memorySearch
		int max = 0;
		for(int i = start; i <= end; i++) {
			int val = maxCoinsHelper(nums, start, i-1, dp) + maxCoinsHelper(nums, i+1, end, dp) 
				+ getVal(nums, start - 1) * getVal(nums, end + 1) * getVal(nums, i); // update rule in each level of recursion
			max = Math.max(max,  val);
		}
		dp[start][end] = max;
		return max;
	}
	private int getVal(int[] nums, int index) {
		if(index == -1 || index == nums.length) {
			return 1;
		}
		return nums[index];
	}
	// Version 2: Not including boundary, the boundary balloons not burst
	public int maxCoinsII(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		// Add the boundary first for the original input
		int[] array = new int[nums.length + 2];
		int n = 1;
		for(int x : nums) if(x > 0) array[n++] = x;
		array[0] = array[n++] = 1;
		int[][] memo = new int[n][n];
		return memorySearch(memo, array, 0, n - 1);
	}
	public int memorySearch(int[][] memo, int[] array, int left, int right) {
		if(left + 1 == right) return 0;
		if(memo[left][right] != 0) return memo[left][right];
		int res = 0;
		for(int i = left + 1; i < right; i++) {
			res = Math.max(res, array[left]*array[right]*array[i] + memorySearch(memo, array, left, i) + memorySearch(memo, array, i, right));
		}
		memo[left][right] = res;
		return res;
	}
	// Version 3: pure dynamic programming
	public int maxCoinsDp(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		int[] array = new int[nums.length + 2];
		int n = 1;
		for(int x : nums) if(x > 0) array[n++] = x;
		array[0] = array[n++] = 1;
		int[][] dp = new int[n][n];
		for(int k = 2; k < n; k++) {
			for(int left = 0; left < n - k; left++) {
				int right = left + k;
				for(int i = left + 1; i < right; i++) {
					dp[left][right] = Math.max(dp[left][right], array[left]*array[right]*array[i] + dp[left][i] + dp[i][right]);
				}
			}
		}
		return dp[0][n-1];
	}
	public static void main(String[] args) {
		BurstBalloons solution = new BurstBalloons();
		int[] array = new int[] {3,1,5,8};
		int maxCoins = solution.maxCoinsDp(array);
		System.out.println(maxCoins);
	}
}
