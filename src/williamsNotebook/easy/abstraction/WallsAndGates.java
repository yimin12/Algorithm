/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.LinkedList;
import java.util.Queue;

import BinaryTreeAndBST.isBST;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午4:38:10
* Description:
* 	You are given am x n2D grid initialized with these three possible values.
* 	-1- A wall or an obstacle.
* 	0- A gate.
* 	INF- Infinity means an empty room. We use the value 2^31- 1 = 2147483647 to represent 
* 		INF as you may assume that the distance to a gate is less than 2147483647.
*	Example: 				
		INF  -1  0  INF				3  -1   0   1
		INF INF INF  -1				2   2   1  -1
		INF  -1 INF  -1				1  -1   2  -1
		  0  -1 INF INF				0  -1   3   4
*/

public class WallsAndGates {
	
	// Point is an common encapsulated class for BFS
	class Point{
		int row, col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	static final int[][] dirs = {{-1, 0},{0,-1},{1, 0},{0, 1}};
	public void wallsAndGates(int[][] rooms) {
		if(rooms == null || rooms.length == 0 || rooms[0].length == 0) {
			return;
		}
		int EMPTY = Integer.MAX_VALUE;
		int GATE = 0;
		int WALL = -1;
		Queue<Point> q = new LinkedList<Point>();
		int m = rooms.length;
		int n = rooms[0].length;
		for(int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(rooms[i][j] == GATE) {
					q.offer(new Point(i, j));
				}
			}
		}
		while(!q.isEmpty()) {
			Point point = q.poll();
			for(int k = 0; k < dirs.length; k++) {
				int row = point.row + dirs[k][0];
				int col = point.col + dirs[k][1];
				if(row < 0 || row == m || col < 0 || col == n || rooms[row][col] == WALL) {
					continue;
				}
				rooms[row][col] = rooms[point.row][point.col] + 1;
				q.offer(new Point(row, col));
			}
		}
	}
}
