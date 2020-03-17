/**
 * 
 */
package williamsNotebook.easy.dynamicP;

import java.util.Arrays;
/**
 * @author yimin Huang
 *	A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *		The robot can only move either down or right at any point in time. 
 *		The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *		How many possible unique paths are there?
 *	
 * Algorithm Class
 */
public class UniquePath {

	// Method 1: dfs, each level have two choice, so total movement is constant and assume it is m
	// Time: O(2^(m+n-2))  extra space: O(max(m,n)) for call stack
	public int uniquePath(int m, int n) {
		if(m <= 0 || n <= 0) return 0;
		return uniqueDFS(m-1, n-1);
	}
	private int uniqueDFS(int m, int n) {
		if(m < 0 || n < 0) return 0;
		else if(m == 0 || n == 0) return 1;
		else return uniqueDFS(m - 1, n) + uniqueDFS(m, n-1);
	}
	// Method 2: dfs with memorization
	// each possible only go through once, Time: O(m*n) Space:O(m*n)
	public int uniquePathII(int m, int n) {
		if(m <= 0 || n <= 0) return 0;
		return uniqueDFSWithMem(m - 1, n - 1, new int[n][m]);
	}
	private int uniqueDFSWithMem(int m, int n, int[][] visited) {
		if(m < 0 || n < 0) return 0;
		else if(m == 0 || n == 0) return 1;
		else if(visited[n][m] > 0) {
			return visited[n][m];
		} else {
			visited[n][m] = uniqueDFSWithMem(n-1, m, visited) + uniqueDFSWithMem(n, m-1, visited);
			System.out.println(Arrays.deepToString(visited));
			return visited[n][m];
		}
	}
	// Method 3: dynamic programming
	// dp[i][j] represent the number of ways from [0][0] move to [i][j].
	// Base case :
	//  	dp[0][0] = 1;
	// Induction rule:
	// 		dp[i][j] = dp[i-1][j] + dp[i][j-1] care about the out of boundary 
	// Time ~ O(M*N), Space ~ O(M*N)
	public int uniquePathDP(int m, int n) {
		if(m <= 0 || n <= 0) return 0;
		int[][] dp = new int[m][n];
		// induction rule
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(i == 0 || j == 0) {
					// base case
					dp[i][j] = 1;
				} else {
					// induction rule
					dp[i][j] = dp[i-1][j] + dp[i][j-1];
				}
			}
		}
		return dp[m-1][n-1];
	}
	// Method 4: dynamic program with rolling array, idea is the same as Method 3 
	// Time ~ O(M*N), Space ~ O(min(m,n))
	public int uniquePathDPOptimiezed(int m, int n) {
		if(m <= 0 || n <= 0) return 0;
		int min = Math.min(m,n), max = Math.max(m, n);
		int[] dp = new int[min];
		for(int i = 0; i < max; i++) {
			for(int j = 0; j < min; j++) {
				if(i == 0 && j == 0) dp[j] = 1;
				else dp[j] = ((i > 0) ? dp[j] : 0) + ((j > 0) ? dp[j-1] : 0);
			}
		}
		return dp[min - 1];
	}
	
	// Follow Up 2: Now consider if some obstacles are added to the grids. How many unique paths would there be?
	// An obstacle and empty space is marked as 1 and 0 respectively in the grid.
	// Method 1: dfs 
	// Time: O(2^(m+n-2))  extra space: O(max(m,n)) for call stack
	public int uniquePathWithObstacleDFS(int[][] obstacleGrid) {
		if(obstacleGrid == null || obstacleGrid.length == 0) return 0;
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[] count = new int[1];
		count[0] = dfsObstacle(0,0,m-1,n-1,obstacleGrid, count);
		return count[0];
	}
	private int dfsObstacle(int x, int y, int rows, int cols, int[][] obstacleGrid, int[] count) {
		// base case
		if(x == rows && y == cols) {
			count[0]++;
			return count[0];
		}
		// if there is an obstacle, just ignore and do nothing
		if(x < rows && obstacleGrid[x+1][y] == 0) dfsObstacle(x+1, y, rows, cols, obstacleGrid, count);
		if(y < cols && obstacleGrid[x][y+1] == 0) dfsObstacle(x, y+1, rows, cols, obstacleGrid, count);
		return count[0];
	}	
	// Method 2: DFS with memorization
	// each possible only go through once, Time: O(m*n) Space:O(m*n)
	public int uniquePathWithObstacleDFSWithMem(int[][] obstacleGrid) {
		if(obstacleGrid == null || obstacleGrid.length == 0) return 0;
		int m = obstacleGrid.length, n = obstacleGrid[0].length;
		int[][] visited = new int[m][n];
		return dfsWithObstacleDFSWithMem(0,0,m-1,n-1,0,visited,obstacleGrid);
	}
	private int dfsWithObstacleDFSWithMem(int x, int y, int rows, int cols,int count, int[][] visited, int[][] obstacleGrid) {
		// base case
		if(x > rows || y > cols) {
			return 0;
		} else if (x == rows || y == cols) {
			return  1;
		} else if(visited[x][y] > 0) {
			return visited[x][y];
		}
		// recursive rule
		if(x < rows && obstacleGrid[x+1][y] == 0) visited[x+1][y] = dfsWithObstacleDFSWithMem(x+1, y, rows, cols, count, visited, obstacleGrid);
		if(y < cols && obstacleGrid[x][y+1] == 0) visited[x][y+1] = dfsWithObstacleDFSWithMem(x, y+1, rows, cols, count, visited, obstacleGrid);
		visited[x][y] = visited[x+1][y] + visited[x][y+1];
		return visited[x][y];
	}
	// Method 3: Dynamic Programming , Logic is the same as last question, but dp[i][j] = 0 when you encounter an obstacle
	// Time ~ O(M*N), Space ~ O(M*N)
	public int uniquePathWithObstacles(int[][] obstacleGrid) {
		// sanity check
		if(obstacleGrid == null || obstacleGrid.length == 0) return 0;
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int[][] dp = new int[m][n];
		// base case
		dp[0][0] = 1;
		// induction rule:
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(i == 0 || j == 0) {
					dp[i][j] = obstacleGrid[i][j] == 1 ? 0 : 1;
				} else {
					dp[i][j] = (obstacleGrid[i-1][j] == 1 ? 0 : dp[i-1][j]) + (obstacleGrid[i][j-1] == 1 ? 0 : dp[i][j-1]);
				}
			}
		}
		return dp[m-1][n-1];
	}
	// Method 4: DP Optimized with space, logic is the same
	// Time ~ O(M*N), Space ~ O(min{M, N})
	public int uniquePathWithObstaclesDPOptimized(int[][] obstacleGrid) {
		if(obstacleGrid == null || obstacleGrid.length == 0) return 0;
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		int min = Math.min(m, n), max = Math.max(m, n);
		int[] dp = new int[min];
		int value;
		for(int i = 0; i < max; i++) {
			for(int j = 0; j < min; j++) {
				if(n == min) value = obstacleGrid[i][j];
				else value = obstacleGrid[i][j];
				if(i == 0 || j == 0) {
					dp[j] = value == 1 ? 0 : 1;
				} else {
                    dp[j] = value == 1 ? 0 : ((i > 0) ? dp[j] : 0) + ((j > 0) ? dp[j - 1] : 0);
				}
			}
		}
		return dp[min-1];
	}
	
	// Follow Up 3: On a 2-dimensional grid, there are 4 types of squares:
	// 1 represents the starting square.  There is exactly one starting square.
	// 2 represents the ending square.  There is exactly one ending square
	// 0 represents empty squares we can walk over.
	// -1 represents obstacles that we cannot walk over
	// Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
	// Method 1: DFS with back tracking
	// Time: O(4^(m*n)) Space:(m*n)
	public int uniquePathIII(int[][] grid) {
		// step 1. find the start point and how many the empty space, and guarantee start point and end point is unique
		int startX = 0, startY = 0, walk = 0;
		int m = grid.length, n = grid[0].length;
		for(int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(grid[i][j] == 0) {
					walk++;
				}
				if(grid[i][j] == 1) {
					startX = i;
					startY = j;
				}
			}
		}
		return dfs(startX, startY, m, n, -1, walk, grid);
	}
	private int dfs(int x, int y, int rows, int cols, int index, int needed,int[][] grid) {
		// base case: case 1. Out of boundary or encounter obstacle
		if(x < 0 || x == rows || y < 0 || y == cols || grid[x][y] == -1) return 0;
		if(grid[x][y] == 2) {
			// case 2: all the grid have been traversed exactly once
			if(index == needed) {
				return 1;
			} else {
				return 0;
			}
		}
		// if you visited the grid, mark it as obstacle
		grid[x][y] = -1;
		int total = dfs(x + 1, y, rows, cols, index + 1, needed, grid);
	    total += dfs(x - 1, y, rows, cols, index + 1, needed, grid);
		total += dfs(x, y + 1, rows, cols, index + 1, needed, grid);
		total += dfs(x, y - 1, rows, cols, index + 1, needed, grid);
		// back tracking 
		grid[x][y] = 0;
		return total;
		
	}
	public static void main(String[] args) {
		UniquePath solution = new UniquePath();
		int uniquePath = solution.uniquePathDPOptimiezed(3, 2);
		System.out.println(uniquePath);
		
		int[][] matrix = new int[][] {{0,0,0,0},{0,0,0,0},{0,0,1,0},{0,0,0,0}};
		int uniquePathWithObstacles = solution.uniquePathWithObstaclesDPOptimized(matrix);
		System.out.println(uniquePathWithObstacles);
		
		int[][] test = new int[][] {{1,0,0,0},{0,0,0,0},{0,0,-1,2}};
		int uniquePathIII = solution.uniquePathIII(test);
		System.out.println(uniquePathIII);
	}
}
