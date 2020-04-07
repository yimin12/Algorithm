/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午4:38:38
* Description:
* 	You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols+and-.
*   For each integer, you should choose one from+and-as its new symbol.		
*   Find out how many ways to assign symbols to make sum of integers equal to target S.
* Example:
* 	Input: nums is [1, 1, 1, 1, 1], S is 3. 	 Output: 5
* 	Explanation: 
* 	-1+1+1+1+1 = 3
	+1-1+1+1+1 = 3
	+1+1-1+1+1 = 3
	+1+1+1-1+1 = 3
	+1+1+1+1-1 = 3
	There are 5 ways to assign symbols to make the sum of nums be target 3.
*/
public class TargetSum {

	// Assumption: The sum of elements in the given array will not exceed 1000.
	// Your output answer is guaranteed to be fitted in a 32-bit integer.
	// Logic is similar to coins change Or BackPack
	// Analysis:
//	Let P be the positive subset and N be the negative subset
//	For example:
//	Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
//	Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
//
//	Then let's see how this can be converted to a subset sum problem:
//
//	                  sum(P) - sum(N) = target
//	sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
//	                       2 * sum(P) = target + sum(nums)
//	So the original problem has been converted to a subset sum problem as follows:
//	Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
//
//	Note that the above formula has proved that target + sum(nums) must be even
//	We can use that fact to quickly identify inputs that do not have a solution (Thanks to @BrunoDeNadaiSarnaglia for the suggestion)
	public int findTargetSumWays(int[] nums, int S) {
		int sum = 0;
		for(int i = 0; i < nums.length;i++) {
			sum += nums[i];
		}
		// Key Insight: odd can not convert to even by add or minus
		// odd+odd = even, odd + even = odd;
		if(S > sum || (sum + S) % 2 == 1) return 0;
		return subsetSum(nums, (sum+S)/2);
	}
	private int subsetSum(int[] nums, int S) {
		int[] dp = new int[S+1];
		dp[0] = 1;
		for(int i = 0; i < nums.length; i++) {
			for(int j = S; j >= nums[i]; j--) {
				dp[j] += dp[j - nums[i]];
			}
		}
		return dp[S];
	}
	
	// Follow Up: Coins change, Two possible situation
	/** 
	 * @return number of ways to make sum s using repeated coins
	 */
	public static int coinrep(int[] coins, int s) {
	    int[] dp = new int[s + 1]; 
	    dp[0] = 1;          
	    for (int coin : coins)      
	        for (int i = coin; i <= s; i++)         
	            dp[i] += dp[i - coin];                                  
	    return dp[s];
	}                                       

	/**
	 * @return number of ways to make sum s using non-repeated coins
	 */
	public static int coinnonrep(int[] coins, int s) {
	    int[] dp = new int[s + 1];
	    dp[0] = 1;  
	    for (int coin : coins)
	        for (int i = s; i >= coin; i--)
	            dp[i] += dp[i - coin];              
	    return dp[s];                                                   
	}
	
	public static void main(String[] args) {
		TargetSum solution = new TargetSum();
		int[] array = {1, 1, 1, 1, 1};
		int findTargetSumWays = solution.coinnonrep(array,3);
		System.out.println(findTargetSumWays);
 	}
}
