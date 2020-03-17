/**
 * 
 */
package williamsNotebook.medium;

/**
 * @author yimin Huang
 *
 *		Given a triangle or Rectangular, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 *		Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle
 *	Input:			Output: 7,  Explanation: Because the path 1→3→1→1→1 minimizes the sum
	[
	  [1,3,1],
	  [1,5,1],
	  [4,2,1]
	]
 * Algorithm Class
 */
public class MinimumPathSum {

	// Rectangular Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path
	// You can only move either down or right at any point in time
	// Solution 1:
	// 2-d DP: Time ~ O(N^2), Space ~ O(M*N)
	// dp[i][j] is the min path from grid[0][0] to grid[i][j]
	// induction rule:
	// dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + grid[i][j]
	// base case :
	// dp[0][0] = grid[0][0]
	public int minPathSum(int[][] grid) {
		// sanity check
		if(grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int[][] dp = new int[grid.length][grid[0].length];
		for(int i = 0; i < grid.length; i++) {
			for(int j = 0; j < grid[0].length; j++) {
				// base case
				if(i == 0 && j == 0) dp[i][j] = grid[0][0];
				else if (i == 0){
					dp[i][j] = dp[i][j - 1] + grid[i][j];
				} else if(j == 0) {
					dp[i][j] = dp[i - 1][j] + grid[i][j];
				} else {
					dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
				}
			}
		}
		return dp[grid.length - 1][grid[0].length-1];
	}
	// Optimezed the Space Complexity by rolling array
	public int minPathSumOptimize(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int m = grid.length;
        int n = grid[0].length;
        int min = Math.min(m, n), max = Math.max(m, n);
        int[] sum = new int[min];
        int value;
        for(int i = 0; i < max; i++) {
        	for (int j = 0; j < min; j++) {
        		// you need to rotate the matrix when m is the shorter side
				if(n == min) value = grid[i][j];
				else value = grid[j][i];
				if(i == 0 && j == 0) sum[j] = value;
				else {
					sum[j] = Math.min((i>0)?sum[j]:Integer.MAX_VALUE, (j>0)?sum[j-1]:Integer.MAX_VALUE) + value;
				}
			}
        }
        return sum[min-1];
	}
	// it can also be optimized to O(1)
	
	public static void main(String[] args) {
		MinimumPathSum solution = new MinimumPathSum();
		int[][] matrix = new int[][] {{1,3,1},{1,5,1},{4,2,1}};
		int minPathSum = solution.minPathSum(matrix);
		System.out.println(minPathSum);
	}
}
