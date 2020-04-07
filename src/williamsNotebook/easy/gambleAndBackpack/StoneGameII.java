/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月5日 下午7:33:25
* Description:
* 	There is a stone game.At the beginning of the game the player picks n piles of stones in a Circle.
* 	The goal is to merge the stones in one pile observing the following rules:
* 		 1.each step of the game, the player can merge two adjacent piles to a new pile.
* 		 2.The score is the number of stones in the new pile.
* 	You are to determine the minimum of the total score.
* Example:
* 	For [4, 1, 1, 4], in the best solution, the total score is 18:
*/

public class StoneGameII {

	// dp[i][j]: represent the minimum cost of merging from i to j
	// Induction rule: dp[i][j] = min(dp[i][k] + dp[k+1][j] + sum[i,j] k from i to j
	// base case: dp[i][i] = 0 (can not merge with itself)
	// return dp[0][n-1]
	public int stoneGameII(int[] A) {
		if(A == null || A.length == 0) {
			return -1;
		}
		int n = A.length;
		if(n <= 1) return 0;
		// because it is circular, the start and end of the array is adjacent, so method is similar with circular problem
		int[][] dp = new int[2*n][2*n];
		int[] sum = new int[2*n+1];
		for(int i = 1; i <= 2 * n; i++) {
			sum[i] = sum[i - 1] + A[(i-1)%n];
		}
		for(int i = 0; i < 2 * n; i++) {
			dp[i][i] = 0;
		}
		for(int len = 2; len <= 2;len++) {
			for(int i = 0; i < 2 * n && i + len - 1 < 2 * n; i++) {
				int j = i + len - 1;
				dp[i][j] = Integer.MAX_VALUE;
				for(int k = i; k < j; k++) {
					if(dp[i][k] + dp[k+1][j] + sum[j+1] - sum[i] < dp[i][j] ) {
						dp[i][j] = dp[i][k] + dp[k+1][j] + sum[j+1] - sum[i];
					}
				}
			}
		}
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			if(dp[i][i+n-1] < res) {
				res = dp[i][i + n - 1];
			}
		}
		return res;
	}
}
