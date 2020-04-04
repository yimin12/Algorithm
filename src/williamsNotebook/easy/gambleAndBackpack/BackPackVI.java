/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 下午3:33:54
* Description:
* 	Given an integer array nums with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target
* 	A number in the array can be used multiple times in the combination Different orders are counted as different combinations.
* Example:
* 	Given nums =[1, 2, 4], target = 4
* 	The possible combination ways are:
* 	[1, 1, 1, 1]
	[1, 1, 2]
	[1, 2, 1]
	[2, 1, 1]
	[2, 2]
	[4]
*/
public class BackPackVI {

	// It is exactly the same as Combination Sum
	// Key Insight: 
	// Because all elements are orderless, this is core different between the former backpack question
	// If we want value m, and given some options [a1, a2, a3, a4]; 
	// We should know how many possibilities that can form (m - a1) + (m - a2) + (m - a3) + (m - a4); 
	// i: use i types of items 
	// j: value that we want to fullfill
	// induction rule: dp[j] = dp[j-A[0]] + dp[j-A[1]] + .... dp[j-A[i-1]] if possible
	// base case: dp[0] = 1 (there are only one way to fullfill value 0)
	// Time: O(n * m) Space: O(m)
	public int backPack(int[] A, int m) {
		if(A == null || A.length == 0 || m <= 0) return 0;
		int n = A.length;
		int[] dp = new int[m+1];
		dp[0] = 1;
		int i,j;
		for(i = 1; i <= m; i++) {
			// how many ways can we make weight i last item A[j]
			dp[i] = 0;
			for(j = 0; j < n; j++) {
				if(A[j] < j) {
					dp[i] += dp[i - A[j]];
				}
			}
		}
		return dp[m];
	}
	
	// Method 2: Search(DFS) + Memorization (Top Down)
	public int combinationSum(int[] nums, int target) {
		if(nums == null || nums.length == 0) return 0;
		int[] memo = new int[target + 1];
		Arrays.fill(memo, -1);
		memo[0] = 1;
		return dfs(nums, target, memo);
	}
	private int dfs(int[] nums, int target, int[] memo) {
		if(memo[target] != -1) return memo[target];
		int res = 0;
		for(int i = 0; i < nums.length; i++) {
			if(target >= nums[i]) {
				res += dfs(nums, target - nums[i], memo);
			}
		}
		memo[target] = res;
		return res;
	}
}
