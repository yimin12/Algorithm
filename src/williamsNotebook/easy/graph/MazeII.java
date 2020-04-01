/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午8:41:20
* Description:
*   There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up,down,left or right,
* 	but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction
* 	Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination.
*   The distance is defined by the number of empty spacestraveled by the ball from the start position (excluded) to the destination (included). 
*   If the ball cannot stop at the destination, return -1.
*   The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the
*   maze are all walls. The start and destination coordinates are represented by row and column indexes.
* Example:
* 	0 0 1 0 0 			Input 2: start coordinate (rowStart, colStart) = (0, 4)
	0 0 0 0 0			Input 3: destination coordinate (rowDest, colDest) = (4, 4)
	0 0 0 1 0			Output: 12
	1 1 0 1 1			Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right. 
	0 0 0 0 0			The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
*/
// This is the follow up Maze
public class MazeII {

	// Method 1: BFS 
	// Time complexity : O(m∗n∗max(m,n)). Complete traversal of maze will be done in the worst case. Here, m and n refers to the number of rows and columns of the maze. 
	// Further, for every current node chosen, we can travel up to a maximum depth of max(m,n) in any direction.
	// Space complexity :O(mn).queue size can grow up to m*nin the worst case.
	static int[][] dirs = {{1,0},{0,1},{0,-1},{-1,0}};
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		if(maze == null || maze.length == 0 || maze[0].length == 0 || start == null || destination == null || destination.length * start.length == 0) {
			return -1;
		}
		int m = maze.length, n = maze[0].length;
		int[][] distance = new int[m][n];
		for(int[] row:distance) {
			Arrays.fill(row,  Integer.MAX_VALUE);
		}
		Deque<int[]> queue = new ArrayDeque<int[]>();
		queue.offer(start);
		distance[start[0]][start[1]] = 0;
		while(!queue.isEmpty()) {
			int[] p = queue.poll();
			for(int[] dir:dirs) {
				int nextRow = p[0];
				int nextCol = p[1];
				int count = 0;
				while(canRoll(maze, nextRow + dir[0], nextCol + dir[1])) {
					nextRow += dir[0];
					nextCol += dir[1];
					count++;
				}
				// Update the distance[][], and use to check if a position is visited or not
				if(distance[p[0]][p[1]] + count < distance[nextRow][nextCol]) {
					distance[nextRow][nextCol] = distance[p[0]][p[1]] + count;
					queue.offer(new int[] {nextRow, nextCol});
				}
			}
		}
		return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
	}
	private boolean canRoll(int[][] maze, int nextRow, int nextCol) {
		int m = maze.length, n = maze[0].length;
		return nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && maze[nextRow][nextCol] == 0;
	}
	// Method 2: Dijkstra Algorithm with PriorityQueue
	public int shortestDistanceII(int[][] maze, int[] start, int[] dest) {
		int[][] distance = new int[maze.length][maze[0].length];
		for(int[] row:distance) {
			Arrays.fill(row,  Integer.MAX_VALUE);
		}
		distance[start[0]][start[1]] = 0;
		dijkstra(maze, start, distance);
		return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
	}
	private void dijkstra(int[][] maze, int[] start, int[][] distance) {
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
		queue.offer(new int[] {start[0],start[1], 0});
		while(!queue.isEmpty()) {
			int[] s = queue.poll();
			if(distance[s[0]][s[1]] < s[2]) continue;
			for(int[] dir:dirs) {
				int x = s[0] + dir[0];
				int y = s[0] + dir[1];
				int count = 0;
				while(x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
					x += dir[0];
					y += dir[1];
					count++;
				}
				if(distance[s[0]][s[1]] + count <distance[x - dir[0]][y - dir[1]]) {
					distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
					queue.offer(new int[] {x - dir[0], y - dir[1], distance[x-dir[0]][y-dir[1]]});
				}
			}
		}
	}
	// Method 3: DFS
	public int shortestDistanceIII(int[][] maze, int[] start, int[] dest) {
		int[][] distance = new int[maze.length][maze[0].length];
		for(int[] row : distance) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		distance[start[0]][start[1]] = 0;
		dfs(maze, start, distance);
		return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1: distance[dest[0]][dest[1]];
	}
	public void dfs(int[][] maze, int[] start, int[][] distance) {
		for(int[] dir : dirs) {
			int x = start[0] + dir[0];
			int y = start[1] + dir[1];
			int count = 0;
			while(canRoll(maze, x, y)) {
				x += dir[0];
				y += dir[1];
				count++;
			}
			if(distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
				 distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
				 dfs(maze, new int[] {x - dir[0], y - dir[1]}, distance);
			}
		}
	}
}
