/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午3:44:33
* Description:  LeetCode 490
* 	There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up,down,left or right, 
* 	but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
* 	Given the ball'sstart position, the destination and the maze, determine whether the ball could stop at the destination
* 	The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the 
* 	borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
* 	Input 1: a maze represented by a 2D array
		0 0 1 0 0
		0 0 0 0 0
		0 0 0 1 0
		1 1 0 1 1
		0 0 0 0 0
		Input 2: start coordinate (rowStart, colStart) = (0, 4)
		Input 3: destination coordinate (rowDest, colDest) = (4, 4)
		Output: true
		Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
*/
public class Maze {

	// DFS traverse, there are 4 branches in each level of recursion tree
	// Time complexity :O(mn). Complete traversal of maze will be done in the worst case. 
	// Here, m and n refers to the number of rows and coloumns of the maze.
	// Space complexity :O(mn). visited array of size m*n is used.
	private static int DIRS[][] = {{-1,0},{0,1},{1,0},{0,-1}};
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		// Assumption
		if(maze == null || maze.length == 0 || maze[0].length == 0 || start == null || destination == null || destination.length * start.length == 0) {
			return false;
		}
		int m = maze.length, n = maze[0].length;
		boolean[][] visited = new boolean[m][n];
		return traverse(maze, start, destination, visited);
	}
	private boolean traverse(int[][] maze, int[] position, int[] destination, boolean[][] visited) {
		if(visited[position[0]][position[1]]) {
			return false;
		}
		if(Arrays.equals(position, destination)) return true;
		visited[position[0]][position[1]] = true;
		for(int i = 0; i < DIRS.length; i++) {
			int[] newPosition = roll(maze, position, DIRS[i]);
			if(traverse(maze, newPosition, destination, visited)) {
				return true;
			}
		}
		return false;
	}
	//Roll the ball based on current position and direction, return new stopped postion
	private int[] roll(int[][] maze, int[] position, int[] direction) {
		int row = position[0];
		int col = position[1];
		while(canRoll(maze, row + direction[0], col + direction[1])) {
			row += direction[0];
			col += direction[1];
		}
		return new int[] {row, col};
	}
	// Using next row, col position to check if a ball can roll
	private boolean canRoll(int[][] maze, int nrow, int ncol) {
		int m = maze.length;
		int n = maze[0].length;
		if(nrow < 0 || nrow >= m || ncol < 0 || ncol >= n || maze[nrow][ncol] == 1) {
			return false;
		}
		return true;
	}
	// Method 2: BFS
	public boolean hasPathBFS(int[][] maze, int[] start, int[] destination) {
		// Assumption
		if(maze == null || maze.length == 0 || maze[0].length == 0 || start == null || destination == null || destination.length * start.length == 0) {
			return false;
		}
		int m = maze.length, n = maze[0].length;
		Deque<int[]> queue = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[m][n];
		queue.offer(start);
		visited[start[0]][start[1]] = true;
		while(!queue.isEmpty()) {
			int[] pos = queue.poll();
			if(pos[0] == destination[0] && pos[1] == destination[1]) {
				return true;
			}
			for(int[] dir:DIRS) {
				int nextRow = pos[0];
				int nextCol = pos[1];
				while(canRoll(maze, nextRow + dir[0], nextCol + dir[1])) {
					nextRow += dir[0];
					nextCol += dir[1];
				}
				if(!visited[nextRow][nextCol]) {
					queue.offer(new int[]{nextRow, nextCol});
					visited[nextRow][nextCol] = true;
				}
			}
		}
		return false;
	}
}
