/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午5:13:31
* Description:
* 	Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such 
* 	that the sum of elements in both subsets is equal.
* Assumption:
* 	Each of the array element will not exceed 100.	
* 	The array size will not exceed 200.
* Example:
* 	Input: [1, 5, 11, 5]
* 	Output: true
* 	Explanation: The array can be partitioned as [1, 5, 5] and [11].
*/

public class PartitionEqualSubsetSum {

	// Analysis: This problem is essentially let us to find whether there are several numbers 
	// in a set which are able to sum to a specific value (in this problem, the value is sum/2).
	// Actually, this is a 0/1 knapsack problem, for each number, we can pick it or not. Let us assume
	// dp[i][j] means whether the specific sum j can be gotten from the first i numbers. If we can pick such a 
	// series of numbers from 0-i whose sum is j, dp[i][j] is true, otherwise it is false.
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for(int num : nums) {
			sum += num;
		}
		if((sum & 1) == 1) return false; // if sum is odd, it can not be equally divided
		sum = sum/2;
		int n = nums.length;
		boolean[][] dp = new boolean[n+1][sum+1];
		for(int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], false);
		}
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j<= sum; j++) {
				if(i == 0 && j == 0) dp[i][j] = true;
				else if(i == 0) dp[i][j] = false;
				else if(j == 0) dp[i][j] = true;
				else {
					dp[i][j] = dp[i-1][j];
					if(j >= nums[i-1]) {
						dp[i][j] = (dp[i][j] || dp[i-1][j - nums[i-1]]);
					}
				}
			}
		}
		return dp[n][sum];
	}
	// Optimize The space Complexity
	public boolean canPartitionOpt(int[] nums) {
		int sum = 0;
		for(int num : nums) {
			sum += num;
		}
		if((sum & 1) == 1) return false; // if sum is odd, it can not be equally divided
		sum = sum/2;
		int n = nums.length;
		boolean[] dp = new boolean[n+1];
		Arrays.fill(dp, false);
		dp[0] = true;
		// each num can only use at most once
		for(int num:nums) {
			for(int i = sum; i > 0; i--) {
				if(i >= num) {
					dp[i] = dp[i] || dp[i-num];
				}
			}
		}
		return dp[sum];
	}
}
