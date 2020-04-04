/**
 * 
 */
package williamsNotebook.medium;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 下午11:08:01
* Description:
* 	Give you an integer matrix (with row size n, column size m)，find the longest increasing continuous subsequence in this 
* 	matrix. (The definition of the longest increasing continuous subsequence here can start at any row or column and 
* 	go up/down/right/left any direction).
* 	[
	 [1 ,2 ,3 ,4 ,5],
	 [16,17,24,23,6],
	 [15,18,25,22,7],
	 [14,19,20,21,8],
	 [13,12,11,10,9]
	] return 25
*/
public class LongestIncreasingContinuousSubsequenceII {

	// Important Memorized search
	// Use memory search and record in a table
	int[][] dp; // record the max length
	int[][] flag; // record whether it has been visited
	int M, N;
	 int[] dx = new int[] {-1, 0, 1, 0};
	 int[] dy = new int[] {0, -1, 0, 1};
	 public int longestIncreasingContinuousSubsequenceII(int[][] matrix) {
		 if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
		 M = matrix.length;
		 N = matrix[0].length;
		 dp = new int[M][N];
		 flag = new int[M][N];
		 int res = 0;
		 
		 for(int i = 0; i < M; i++) {
			 for(int j = 0; j < N; j++) {
				 // start from each point
				 dp[i][j] = search(i, j, matrix);
				 res = Math.max(res, dp[i][j]);
			 }
		 }
		 return res;
	 }
	 // Memorized Search, recursive
	 public int search(int x, int y, int[][] matrix) {
		 if(flag[x][y] != 0) {
			 return dp[x][y];
		 }
		 int res = 1;
		 int nextX, nextY;
		 for(int i = 0; i < 4; i++) {
			 nextX = x + dx[i];
			 nextY = y + dy[i];
			 if(insideBoundary(nextX, nextY) && (matrix[x][y] > matrix[nextX][nextY])) {
				 res = Math.max(res, search(nextX, nextY, matrix) + 1);
			 }
		 }
		 flag[x][y] = 1;
		 dp[x][y] = res;
		 return res;
	 }
	 private boolean insideBoundary(int x, int y) {
		 return (x >= 0 && x < M && y >= 0 && y < N);
	 }
}
