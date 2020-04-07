/**
 * 
 */
package williamsNotebook.easy.gambleAndBackpack;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午10:02:49
* Description:
* 	In a N x N grid representing a field of cherries, each cell is one of three possible integers.
* 	0 means the cell is empty, so you can pass through;
* 	1 means the cell contains a cherry, that you can pick up and pass through;
* 	-1 means the cell contains a thorn that blocks your way.
* Your task is to collect maximum number of cherries possible by following the rules below:
* 	Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
* 	After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
* 	When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
* 	If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
* 	input: grid =				Output: 5
			[[0, 1, -1],
			 [1, 0, -1],
			 [1, 1,  1]]
	Explanation:
		The player started at (0, 0) and went down, down, right right to reach (2, 2).
		4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
		Then, the player went left, up, up, left to return home, picking up one more cherry.
		The total number of cherries picked up is 5, and this is the maximum possible.
*/
public class CherryPick {

	// Instead of walking from end to beginning, let's reverse the second leg of the path, so we are 
	// only considering two paths from the beginning to the end.
	// Notice after t steps, each position(r, c)we could be, is on the liner + c = t. 
	// So if we have two people at positions(r1, c1)and(r2, c2), thenr2 = r1 + c1 - c2. That means the variables r1, c1, c2
	// uniquely determine 2 people who have walked the same r1 + c1 number of steps. This sets us up for dynamic programming quite nicely.
	// 	从 [n-1][n-1] 再返回远点可以看为同时有两个人同时从原点出发，保持同步（步数一致），那样r1+c1 = c2+r2,这样有三个变量就能确定两个人全部位置信息
	// 	c2 = r1+c1-r2
	// If grid[r1][c1]and grid[r2][c2]are not thorns, then the value of dp[r1][c1][c2]is(grid[r1][c1] + grid[r2][c2]), 
	// plus the maximum of dp[r1+1][c1][c2],dp[r1][c1+1][c2],dp[r1+1][c1][c2+1],dp[r1][c1+1][c2+1]as appropriate. We should also 
	// be careful to not double count in case(r1, c1) == (r2, c2).
	// Person 1 down and person 2 down: dp[r1+1][c1][c2] ;
	// Person 1 right and person 2 down: dp[r1][c1+1][c2] ;
	// Person 1 down and person 2 right: dp[r1+1][c1][c2+1] ;
	// Person 1 right and person 2 right: dp[r1][c1+1][c2+1] ;
	// Time Complexity:O(N^3), whereNNis the length ofgrid. Our dynamic programming has O(N^3) states.
	// Space Complexity:O(N^3), the size ofmemo.
	int[][][] memo;
	int[][] grid;
	int N;
	public int cherryPickUp(int[][] grid) {
		this.grid = grid;
		N = grid.length;
		memo = new int[N][N][N];
		for(int[][] layer:memo) {
			for(int[] row:layer) {
				Arrays.fill(row, Integer.MIN_VALUE);
			}
		}
		return Math.max(0, memorySearch(0,0,0));
	}
	public int memorySearch(int r1, int c1, int c2) {
		int r2 = r1 + c1 + r1;
		if(N == r1 || N == r2 || N == c1 || N == c2 || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
			return -999999; 
		}
		if(r1 == N - 1 && c1 == N - 1) {
			return grid[r1][c1];
		}
		if(memo[r1][c1][r2] != Integer.MIN_VALUE) {
			return memo[r1][c1][r2];
		}
		int ans = grid[r1][c1];
		if(c1 != c2) ans += grid[r2][c2];
		ans += Math.max(Math.max(memorySearch(r1, c1+1, c2+1), memorySearch(r1, c1, c2)),
				Math.max(memorySearch(r1, c1+1, c2), memorySearch(r1+1, c1, c2)));
		memo[r1][c1][c2] = ans;
		return ans;
	}
}
