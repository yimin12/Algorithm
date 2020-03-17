/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author yimin Huang
 *
 *	Given a 2d grid map of 1s (land) and 0s (water), count the number of islands. An island is 
 *	surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
 *	You may assume all four edges of the grid are all surrounded by water.
 *
 *	Example 1:
				11110
				11010
				11000
				00000
	return 1
	Example 2:
				11000
				11000
				00100
				00011
	return 3
 * Algorithm Class
 */
public class NumberOfIsland {

	// Insight: "1" points in new graph, "0" we do not care
	// how to check if a cell has been visited:
	// solution 1: use boolean[][];
	// solution 2: do it inplace, use 2 to replace 1 when you traverse these cells
	//Assumption: if the given grid is invalid, return 0
	
	// define traverse direction
	public static int[][] dirs = {{-1, 0},{1, 0},{0, 1},{0, -1}};
	
	// Method 1: using dfs, Time: O(m*n) worst case Space:(m*n) by call stack
	public int numIslandsDFS(char[][] grid) {
		// sanity check
		if(grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int count = 0;
		final int rows = grid.length;
		final int cols = grid[0].length;
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(grid[i][j] == '1') {
					count++;
					dfs(grid, i, j, rows, cols);
				}
			}
		}
		return count;
	}
	// if we do not need to return the original grid, so mark 1 as 0, because 0 is meaningless for us
	private void dfs(char[][] grid, int x, int y, int rows, int cols) {
		if(x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == '0') {
			return;
		}
		grid[x][y] = '0';
		for(int[] dir:dirs) {
			int neiX = x + dir[0];
			int neiY = y + dir[1];
			dfs(grid, neiX, neiY, rows, cols);
		}
	}
	
	
	public static void main(String[] args) {
		NumberOfIsland solution = new NumberOfIsland();
		char[][] grid = new char[][] {{'1','1','0','0','0'},
									  {'1','1','0','0','0'},
									  {'0','0','1','0','0'},
									  {'0','0','0','1','1'}};
		int numIslands = solution.numIslandsDFS(grid);	
		System.out.println(numIslands);
	}
}
