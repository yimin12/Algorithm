/**
 * 
 */
package williamsNotebook.easy.graph;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午3:28:46
* Description:
* 	Given an integer matrix, find the length of the longest increasing path.
* 	From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
* Example 1:
* 	Input: nums =      
* 	[
	  [9,9,4],
	  [6,6,8],
	  [2,1,1]
	]	 Output: 4 
	Explanation: The longest increasing path is [1, 2, 6, 9].
*/

public class LongestIncreasingPathInMatrix {

	// Flooding Algorithm: DFS + DP
	public int longestIncreasingPath(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
		int m = matrix.length, n = matrix[0].length;
		int[][] dp = new int[m][n];
		int max = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				max = Math.max(max, findPath(matrix, i, j, dp));
			}
		}
		return max;
	}
	private int findPath(int[][] matrix, int i, int j, int[][] dp) {
		if(dp[i][j] != 0) return dp[i][j];
		int m = matrix.length, n = matrix[0].length;
		int tempLen = 0;
		int[] dx = new int[] {0,1,0,-1};
		int[] dy = new int[] {1,0,-1,0};
		for(int k = 0; k < 4; k++) {
			int nextX = i + dx[k];
			int nextY = j + dy[k];
			if(nextX >= 0 && nextY >= 0 && nextX < m && nextY < n) {
				if(matrix[nextX][nextY] > matrix[i][j]) {
					tempLen = Math.max(tempLen, findPath(matrix, nextX, nextY, dp));
				}
			}
		}
		// do the calculating in the backtracking(post order)
		tempLen++;
		dp[i][j] = tempLen;
		return tempLen;
	}
}
