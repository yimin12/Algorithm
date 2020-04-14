/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.LinkedList;
import java.util.Queue;

import williamsNotebook.common.node.Point;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月11日 下午1:48:42
* Description:
* 	Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the 
* 	shortest path to a destination position, return the length of the route.
* 	Return -1 if destination cannot be reached.
* 	If the knight is at (x, y), he can get to the following positions in one step:
* 	(x + 1, y + 2)
	(x + 1, y - 2)
	(x - 1, y + 2)
	(x - 1, y - 2)
	(x + 2, y + 1)
	(x + 2, y - 1)
	(x - 2, y + 1)
	(x - 2, y - 1)
	Input:
		[[0,0,0],
		 [0,0,0],
		 [0,0,0]]
	source = [2, 0] destination = [2, 2] 
	Output: 2
	Explanation:
	[2,0]->[0,1]->[2,2]
* 	
*/

public class KnightShortestPath {

	// BFS
	public int shortestPath(boolean[][] grid, Point source, Point dest) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int[] dx = {1,1,-1,-1,2,2,-2,-2};
		int[] dy = {2,-2,2,-2,1,-1,1,-1};
		Queue<Point> q = new LinkedList<Point>();
		boolean[][] v = new boolean[grid.length][grid[0].length];
		if(source.x == dest.x && source.y == dest.y) {
			return 0;
		}
		q.offer(source);
		v[source.x][source.y] = true;
		
		int dist = 0;
		while(!q.isEmpty()) {
			dist++;
			int size = q.size();
			for(int i = 0; i < size; i++) {
				Point cur = q.poll();
				for(int k = 0; k < dx.length; k++) {
					int nx = cur.x + dx[k];
					int ny = cur.y + dy[k];
					if(nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && !grid[nx][ny] && !v[nx][ny]) {
						if(nx == dest.x && ny == dest.y) {
							return dist;
						}
						q.offer(new Point(nx, ny));
						v[nx][ny] = true;
					}
				}
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		KnightShortestPath solution = new KnightShortestPath();
		boolean[][] grid = {{false,false,false},{false,false,false},{false,false,false}};
		int shortestPath = solution.shortestPath(grid, new Point(2, 0), new Point(2, 2));
		System.out.println(shortestPath);
	}
}
