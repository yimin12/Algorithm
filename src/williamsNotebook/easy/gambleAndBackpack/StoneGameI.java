/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月5日 下午11:50:27
* Description:
* 	There is a stone game.At the beginning of the game the player picks n piles of stones in a Line.
* 	The goal is to merge the stones in one pile observing the following rules:
* 		 1.each step of the game, the player can merge two adjacent piles to a new pile.
* 		 2.The score is the number of stones in the new pile.
* 	You are to determine the minimum of the total score.
* Example:
* 	For [4, 1, 1, 4], in the best solution, the total score is 18:
*/
// Notice the difference, StoneGameI is in a line, StoneGameII is in a circle
public class StoneGameI {

	// It can be solved by pure dynamic programming
	// dp[i][j]: represent the minimum cost of merging from i to j
	// Induction rule: dp[i][j] = min(dp[i][k] + dp[k+1][j] + sum[i,j] k from i to j
	// base case: dp[i][i] = 0 (can not merge with itself)
	// return dp[0][n-1]
	public int stoneGame(int[] A) {
		if(A == null || A.length == 0) {
			return 0;
		}
		int n = A.length;
		int[][] dp = new int[n][n];
		int[] prefixSum = new int[n+1];
		// Pre-Processing Interval Sum
		for(int i = 1; i <= A.length; i++) {
			prefixSum[i] = prefixSum[i-1] + A[i-1];
		}
		return memorizeSearch(0, n-1, A, dp, prefixSum);
	}
	// Use divide and conquer
	// TIme: O(n^3) Space: O(n^2)
	private int memorizeSearch(int left, int right, int[] A, int[][] dp, int[] prefixSum) {
		if(left > right) return 0;
		if(left == right) return 0; // base case
		if(left + 1 == right) return A[left] + A[right];
		int min = Integer.MAX_VALUE;
		for(int i = left; i < right; i++) {
			int cost = memorizeSearch(left, i, A, dp, prefixSum) 
					+ memorizeSearch(i+1, right, A, dp, prefixSum)
					+ prefixSum[right+1] - prefixSum[left];
			min = Math.min(min, cost);
		}
		dp[left][right] = min;
		return min;
	}
}
