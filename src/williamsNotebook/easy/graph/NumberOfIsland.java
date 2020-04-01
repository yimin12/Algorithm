/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.LinkedList;
import java.util.Queue;

import williamsNotebook.common.unionFind.SimpleUnionFind;
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
	// Method 2: same just the regular dfs
	public int numIslandsDFSII(boolean[][] grid) {
		// Assumption: the given the input is valid
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		int m = grid.length, n = grid[0].length;
		int nums = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == false) {
					continue;
				}
				nums++;
				dfsII(grid, i, j);
			}
		}
		return nums;
	}
	private void dfsII(boolean[][] grid, int row, int col) {
		if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
			return;
		}
		if(grid[row][col] == true) {
			grid[row][col] = false;
			dfsII(grid, row-1, col);
			dfsII(grid, row+1, col);
			dfsII(grid, row, col-1);
			dfsII(grid, row, col+1);
		}
	}
	// Method 3: BFS Solution
	static class Point{
		int row, col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	public int numIslandBFS(char[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		int m = grid.length, n = grid[0].length;
		int nums = 0;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == '1') {
					nums++;
					grid[i][j] = '0';
					Queue<Point> q = new LinkedList<Point>();
					q.offer(new Point(i, j));
					while(!q.isEmpty()) {
						Point point = q.poll();
						for(int k = 0; k < 4; k++) {
							int nextX = point.row + dirs[k][0];
							int nextY = point.col + dirs[k][1];
							if(nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == '1') {
								grid[nextX][nextY] = '0';
								q.offer(new Point(nextX, nextY));
							}
						}
					}
				}
			}
		}
		return nums;
	}
	// Method 4: Another version of BFS by not implementing encapsulation class
	private int m,n;
	public int numsIslandBFSII(char[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		int m = grid.length, n = grid[0].length;
		int count = 0;
		boolean[][] visited = new boolean[m][n];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == '1' && !visited[i][j]) {
					count++;
					bfs(grid, visited, i, j);
				}
			}
		}
		return count;
	}
	private void bfs(char[][] grid, boolean[][] visited, int x, int y) {
		int[] dx = {1, 0 , -1, 0};
	    int[] dy = {0, 1 , 0, -1};
		Queue<Integer> moveX = new LinkedList<Integer>();
		Queue<Integer> moveY = new LinkedList<Integer>();
		moveX.offer(x);
		moveY.offer(y);
		visited[x][y] = true;
		while(!moveX.isEmpty() && !moveY.isEmpty()) {
			int curX = moveX.poll();
			int curY = moveY.poll();
			for(int i = 0; i < 4; i++) {
				int nextX = curX + dx[i];
				int nextY = curY + dy[i];
				if(nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < n && !visited[x][y] && grid[x][y] == '1') {
					visited[nextX][nextY] = true;
					moveX.offer(nextX);
					moveY.offer(nextY);
				}
			}
		}
	}
	
	// Method 5: Union Find
	public boolean isValid(int x, int y, int m, int n) {
		return (x >= 0 && y >= 0 && x < m && y < n);
	}
	public int numIslandsUnionFind(boolean[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
		int m = grid.length, n = grid[0].length;
		int nums = 0;
		IslandUnionFind uf = new IslandUnionFind();
		uf.initIsland(grid);
	    int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        for(int i = 0; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		if(isValid(i, j, m, n) && grid[i][j]) {
        			// four directions for each point
        			for(int k = 0; k < 4; k++) {
        				int nextX = i + dx[k];
            			int nextY = j + dy[k];
            			if(isValid(nextX, nextY, m, n)) {
            				int curParent = uf.find(i * n + j);
            				int neiParent = uf.find(nextX * n + nextY);
            				if(curParent != neiParent) {
            					uf.union(curParent, neiParent);
            					uf.updateCount(-1);
            				}
            			}
        			}
        		}
        	}
        }
        return uf.getCount();

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

class IslandUnionFind extends SimpleUnionFind{
	public int count;
	public void initIsland(boolean grid[][]) {
		count = 0;
		int m = grid.length;
		int n = grid[0].length;
		this.parent = new int[m*n];
		this.size = new int[m*n];
		// initialize the union find
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(grid[i][j] == true) {
					parent[i*n+j] = i * n + j;
					count++;
				} else {
					parent[i * n + j] = -1;
				}
				this.size[i * n + j] = 1;
			}
		}
	}
	public void updateCount(int k) {
		count = count + k;
	}
	public int getCount() {
		return count;
	}
}
