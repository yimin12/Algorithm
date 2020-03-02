/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
 * @author yimin Huang
 *
 *		You are given coins of different denominations and a total amount of money amount. Write a function to the number
 *		of different combinations that can sum up to that amount.
 *
 *		Example 1:
 *			coins = [1,2] , amount = 5
 *			return 3
 *		Explanation: 
 *			5 = 1 + 1 + 1 + 1 + 1 = 1 + 1 + 1 + 2 = 1 + 2 + 2
 *		Note:
 *			You may assume that you have an infinite number of each kind of coin.
 *	
 * Algorithm Class
 */
public class Coin_Change {

	// Method 1: naive dynamic programming
	// n = number of types of coins, m = desired amount
	// dp[i][j] = the number of combinations to make up amount j with the first i types of coins
	// Induction rule:
	// 		dp[i][j] = sum(dp[i-1][j],dp[i-1][j-coins[i-1]*1],dp[i-1][j-coins[i-1]*2],dp[i-1][j-coins[i-1]*3]......)
	//      dp[i][j] = sum(dp[i-1][j-k*coins[i-1]) where k is 0 <= k <= j/coins[i-1]
	// Base case: dp[0][0] = 1, dp[0][1...m] = 0;
	// Time: O(n*m^2) time, Space: O(n*m) extra space
	public int naiveChange(int amount, int[] coins) {
		// sanity check 
		if(coins == null || amount < 0) return -1;
		int[][] dp = new int[coins.length + 1][amount + 1];
		// base case 
		// Arrays.fill(f[0],0)
		dp[0][0] = 1;
		for(int i = 1; i <= coins.length; i++) {
			for(int j = 0; j <= amount; j++) {
				// dp[i][j] = 0 default as 0
				for(int k = 0; k * coins[i - 1] <= j; k++) {
					dp[i][j] += dp[i-1][j - k*coins[i-1]];
				}
				
			}
		}
		return dp[coins.length][amount];
	}
	
	// Method 2: faster dp with dp[i][j] has original physical meaning
	// Induction rule : dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
	// base case : dp[0][0] = 1, d[0][1....m] = 0
	// Time: O(n*m) time, Space: O(n*m) extra space
	public int fasterChange(int amount, int[] coins) {
		if(coins == null || amount < 0) return -1;
		int[][] dp = new int[coins.length + 1][amount + 1];
		// base case 
		// Arrays.fill(f[0],0)
		dp[0][0] = 1;
		// Induction rule:
		// 	dp[i][j] = sum(dp[i-1][j],dp[i-1][j-coins[i-1]*1],dp[i-1][j-coins[i-1]*2],dp[i-1][j-coins[i-1]*3]......)
		// 	dp[i][j-coins[i-1]] = sum(dp[i-1][j-coins[i-1]*1],dp[i-1][j-coins[i-1]*2],dp[i-1][j-coins[i-1]*3]......)
		// so : dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]]
		for(int i = 1; i <= coins.length; i++) {
			for(int j = 0; j <= amount; j++) {
				dp[i][j] = dp[i-1][j] + (coins[i-1] <= j ? dp[i][j - coins[i-1]] : 0);
			}
		}
		return dp[coins.length][amount];
	}
	
	// Method 3: faster and more space-efficient dynamic programming
	// Idea is exactly the same as the method 2 but only need maintain 1D array
	// Invariant: g[k] = f[i][k]   if k < j or   g[i-1][k] if k >= j, j is the current amount
	// Time: O(n*m) time, Space: O(m) extra space
	public int fasterEfficientChange(int amount, int[] coins) {
		if(coins == null || amount < 0) return -1;
		int[] dp = new int[amount + 1];
		// base case 
		// Arrays.fill(f[0],0)
		dp[0] = 1; // corresponding dp[0][0] = 1
		for(int i = 1; i <= coins.length; i++) {
			for(int j = 0; j <= amount; j++) {
				if(coins[i - 1] <= j) {
					dp[j] += dp[j - coins[i-1]];
				}
			}
		}
		return dp[amount];
	}
	
	public static void main(String[] args) {
		Coin_Change solution = new Coin_Change();
		int naiveChange = solution.naiveChange(5, new int[] {1,2,5});
		System.out.println(naiveChange);
	}
}
