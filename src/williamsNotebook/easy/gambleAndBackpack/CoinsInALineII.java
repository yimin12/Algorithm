/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月4日 下午12:16:27
* Description:
* 	There are n coins with different value in a line. Two players take turns to take one or two coins from left side 
* 	until there are no more coins left. The player who take the coins with the most value wins.
* Could you please decide the first player will win or lose?
* Example:
* 	Given values array A = [1,2,2], return true.
* 	Given A = [1,2,4], return false.
*/
public class CoinsInALineII {

	// State: dp[i] the maximum value we can get if there are i coins left
	// Function: i : how many types of coins we have
	//		     sum[i] : sum of last i coins
	// Induction rule: dp[i] = sum[i] - min(dp[i-1],dp[i-2])
	// Base case: dp[0] = 0, dp[1] = coins[n-1], dp[2] = coins[n-1] + coins[n-2]
	public boolean firstWillWin(int[] values) {
		if(values == null || values.length == 0) return false;
		int n = values.length;
		int[] sum = new int[n+1];
		int[] dp = new int[n+1];
		for(int i = 1; i <= n; i++) {
			sum[i] =sum[i-1] + values[n-i];
		}
		// base case
		dp[0] = 0;
		dp[1] = values[n-1];
		for(int i= 2; i <= n; i++) {
			dp[i] = sum[i] - Math.min(dp[i-1], dp[i-2]);
		}
		return dp[n] > sum[n]/2;
	}
	
	
	// Analysis: 改变动态规划的状态改变
	// dp[i]:还剩i个coins先手最多可以获得的价值
	// Coins: 4,1,2,3.最大化 dp[4]？子状态有dp[3],dp[2]
	// 	假设你和对手都明白如何取，是自己能够取得最大值（操作拉满）的情况下，他们都会在心里建立一个dp表，并按照表格能给的最大值进行选取
	//	这时轮到你选，场面上还剩下4个数（4,1,2,3）， 你要获得更多的价值，这与你想让对手获得更少的价值是等价的(总价不变) 
	// 	你在这轮填的表是dp[4]， 你留给对手的可选项就只有dp[3]和dp[2]了（分别剩下三枚和两枚的时候)，dp表由此填下去
	// 	转移方程： dp[i] = sum[i] - min(dp[i-1],dp[i-2]), sum[i]是后面i个数的和（剩下的数的总和）
	// 	dp[4] = sum[4](10) - min(dp[i-1],dp[i-2]) = 10 - min(5,3) = 7, dp[2] = 2 + 3 = 5, dp[3] = 6 - min(3,5) = 3
	// 	取胜条件：只要我们最后能拿到超过总价值的1/2就算获胜
	
}
