/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月31日 下午8:58:51
* Description:
* 	There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by 
* 	rolling up(u),down(d),left(l) or right(r), but it won't stop rolling until hitting a wall. When the ball stops, 
* 	it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it 
* 	rolls on to the hole.
* 	Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by 
* 	moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the 
* 	start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since 
* 	there could be several different shortest ways, you should output the lexicographically smallest way. If the ball 
* 	cannot reach the hole, output "impossible".
* 	The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the 
* 	borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.
*/

public class MazeIII {

	// Method 1: BFS 
	// Time complexity : O(m∗n∗max(m,n)). Complete traversal of maze will be done in the worst case. Here, m and n refers to the number of rows and columns of the maze. 
	// Further, for every current node chosen, we can travel up to a maximum depth of max(m,n) in any direction.
	// Space complexity :O(mn).queue size can grow up to m*nin the worst case.
	private class Point implements Comparable<Point>{
		int row, col, distance; // distance from ball
		String directions;
		Point(int distance, int row, int col, String directions){
			this.distance = distance;
			this.row = row;
			this.col = col;
			this.directions = directions;
		}
		@Override
		public int compareTo(Point o) {
			if(this.distance == o.distance) {
				return this.directions.compareTo(o.directions);
			}
			return this.distance = o.distance;
		}
	}
	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		if(maze == null || maze.length == 0 || maze[0].length == 0 || ball == null || hole == null || hole.length * ball.length == 0) {
			return "impossible";
		}
		int m = maze.length, n = maze[0].length;
		boolean[][] visited = new boolean[m][n];
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		pq.offer(new Point(0, ball[0], ball[1], ""));
		char[] dstr = {'d','l','r','u'};
		int[][] dirs = {{1,0},{0,-1},{0,1},{0,-1}};
		while(!pq.isEmpty()) {
			Point pt = pq.poll();
			if(pt.row == hole[0] && pt.col == hole[1]) {
				return pt.directions;
			}
			visited[pt.row][pt.col] = true;
			for(int i = 0; i < dirs.length; i++) {
				int newRow = pt.row;
				int newCol = pt.col ;
				int distance = pt.distance;
				String direction = pt.directions;
				while(canRoll(maze, newRow + dirs[i][0], newCol + dirs[i][1])) {
					newRow += dirs[i][0];
					newCol += dirs[i][1];
					distance++;
					if(newRow == hole[0] && newCol == hole[1]) {
						break;
					}
				}
				if(!visited[newRow][newCol]) {
					pq.offer(new Point(distance, newRow, newCol, direction + dstr[i]));
				}
			}
		}
		return "impossible";
	}
	private boolean canRoll(int[][] maze, int x, int y) {
		int m = maze.length, n = maze[0].length;
		return x >= 0 && x < m && y >= 0 && y < n && maze[x][y] != 1;
	}
}
