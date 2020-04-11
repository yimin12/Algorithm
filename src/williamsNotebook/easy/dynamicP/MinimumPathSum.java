/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午1:48:02
* Description:
* 	Given a _m_x_n _grid filled with non-negative numbers, find a path from top left to bottom right which_minimizes_the sum of all numbers along its path.
* 	You can only move either down or right at any point in time.
* 	Input:				Output: 7
	[
	  [1,3,1],
	  [1,5,1],
	  [4,2,1]
	]
	Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

public class MinimumPathSum {

	// Idea is similar with Unique Path, but it involves weight
	// dp[i][j] = represent the min Path Sum from start i, j
	// Induction rule: dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j] (dp[i][j-1] comes from left, dp[i-1][j] comes from up)
	// base case: dp[0][0] = grid[0][0]
	// Time: O(m*n) Space: O(m*n)
	public int minPathSum(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		int m = grid.length;
		int n = grid[0].length;
		int[][] dp = new int[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(i == 0 && j == 0) dp[i][j] = 0;
				else if(i == 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
				else if(j == 0) dp[i][j] = dp[i-1][j] + grid[i][j];
				else {
					dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
				}
			}
		}
		return dp[m-1][n-1];
	}
	// Version 2: without extra space by reusing the given matrix itself
	// Time: O(m*n) Space: O(1)
	public int minPathSumII(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		int m = grid.length;
		int n = grid[0].length;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(i == 0 && j == 0) continue;
				else if(i == 0) grid[i][j] += grid[i][j-1];
				else if(j == 0) grid[i][j] += grid[i-1][j];
				else {
					grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
				}
			}
		}
		return grid[m-1][n-1];
	}
}
