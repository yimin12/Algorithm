/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月4日 上午11:30:52
* Description:
* 	There are n coins in a line. Two players take turns to take one or two coins from right side until there are no 
* 	more coins left. The player who take the last coin wins.
* 	Could you please decide the first play will win or lose?
* 	Example
		n = 1, return true.
		n = 2, return true.
		n = 3, return false.
		n = 4, return true.
		n = 5, return true
*/

public class CoinsInALine {

	// Regular Dynamic Programming Or memorize search
	// dp[i] represent if you have i coins, whether you will win
	public boolean firstWillWin(int n) {
		int[] dp = new int[n+1];
		return memorySearch(n, dp);
	}
	boolean memorySearch(int n, int[] dp) {
		// 0 is empty, 1 is false, 2 is true
		// termination condition
		if(dp[n] != 0) {
			if(dp[n] == 1) {
				return false;
			} else {
				return true;
			}
		}
		// base case
		if(n <= 0) {
			dp[n] = 1;
		} else if(n == 1) {
			dp[n] = 2;
		} else if(n == 2) {
			dp[n] = 2;
		} else if(n == 3) {
			dp[n] = 1;
		} else {
			if((memorySearch(n-2, dp) && memorySearch(n-3, dp)) || (memorySearch(n-3, dp) && memorySearch(n-4, dp))){
				dp[n] = 2;
			} else {
				dp[n] = 1;
			}
		}
		if(dp[n] == 2) {
			return true;
		}
		return false;
	}
	// Greedy Solution:
	public boolean firstWillWinII(int n) {
		if(n % 3 == 0) {
			return false;
		}
		return true;
	}
}
