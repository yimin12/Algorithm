/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.HashSet;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午12:08:49
* Description:
* 	Given a non-empty 2D arraygridof 0's and 1's, an island is a group of1's (representing land) connected 
* 	4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
* 	Count the number of distinct islands. An island is considered to be the same as another if and only if one 
* 	island can be translated (and not rotated or reflected) to equal the other
* 	Example 1:  return 1;
* 	11000
	11000
	00011
	00011
	
	11011		return 3   11    and       1    is different
	10000				   1			  11
	00001
	11011
*/

public class NumberOfDistinctIsland {

	// Method 1: DFS + HashSet(Hash by Path Signature)
	public int numDistinctIslands(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		HashSet<String> islands = new HashSet<String>();
		int m = grid.length, n = grid[0].length;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == 1) {
					StringBuilder sb = new StringBuilder();
					// "o" represent original 
					dfs(grid, sb, "o", i, j);
					islands.add(sb.toString());
				}
			}
		}
		return islands.size();
	}
	private void dfs(int[][] grid, StringBuilder sb, String dir, int i, int j) {
		if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
			return;
		}
		grid[i][j] = 0;
		sb.append(dir);
		
		// four direction should contain different information
		dfs(grid, sb, "u", i-1, j);
		dfs(grid, sb, "l", i, j);
		dfs(grid, sb, "d", i, j);
		dfs(grid, sb, "r", i, j);
		
		// mark a finish symbol in stringBuilder
		sb.append("x");
	}
	
	// DFS + Hash By Local Coordinate
	int[][] grid;
	boolean[][] visited;
	Set<Integer> shape;
	private void explore(int r, int c, int r0, int c0) {
		if(0 <= r && r < grid.length && 0 <= c && c < grid[0].length && grid[r][c] == 1 && !visited[r][c]) {
			visited[r][c] = true;
			// you can also use hash here (r-r0)*hashCode + (c-c0)*hashCode
			shape.add((r-r0)*2*grid[0].length + (c-c0));
			explore(r+1, c, r0, c0);
			explore(r-1, c, r0, c0);
			explore(r, c+1, r0, c0);
			explore(r, c+1, r0, c0);
		}
	}
	public int numDistinctIslandsII(int[][] grid) {
		this.grid = grid;
		visited = new boolean[grid.length][grid[0].length];
		Set shapes = new HashSet<HashSet<Integer>>();
		for(int r = 0; r < grid.length; r++) {
			for(int c = 0; c < grid[0].length; c++) {
				shape = new HashSet<Integer>();
				explore(r, c, r, c);
				if(!shape.isEmpty()) {
					shapes.add(shape);
				}
			}
		}
		return shapes.size();
	}
	
}
