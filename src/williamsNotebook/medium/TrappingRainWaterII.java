/**
 * 
 */
package williamsNotebook.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月24日 下午11:51:38
* Description:
* 	Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1,
* 	compute how much water it is able to trap after raining.
*/

public class TrappingRainWaterII {
	
	// Use Cell class that can encapsulate x, y, height
	class Cell{
		public int x, y, h;
		public Cell(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}
	// Use Best First Search by PriorityQueue
	// Key insight : you should know the situation of outer bound for traversing each of them
	// 				 use recording board to record whether you have visited the board
	// 				 when you poll the minimum value from the minHeap, traverse four direction if possible
	//				 if neighbors' cells were higher, update its height for a new outer bound
	// Time: O(m*n*lognm) Space: O(m*n) for PriorityQueue
	public int trapRainWater(int[][] heights) {
		if(heights == null || heights.length == 0 || heights[0].length == 0) {
			return 0;
		}
		int m = heights.length;
		int n = heights.length;
		PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(1, new Comparator<Cell>() {
			public int compare(Cell c1, Cell c2) {
				if(c1.h > c2.h) {
					return 1;
				} else if(c1.h < c2.h) {
					return -1;
				} else {
					return 0;
				}
			}
		});
		// Use best first search
		int[][] visited = new int[m][n];
		// traverse the outer cell and add the boundary element into minHeap
		for(int i = 0; i < m; i++) {
			minHeap.offer(new Cell(i, 0, heights[i][0]));
			minHeap.offer(new Cell(i, n - 1, heights[i][n-1]));
			visited[i][0] = 1;
			visited[i][n-1] = 1;
		}
		for(int j = 0; j < n; j++) {
			minHeap.offer(new Cell(0, j, heights[0][j]));
			minHeap.offer(new Cell(n-1, j, heights[m-1][j]));
			visited[0][j] = 1;
			visited[m-1][j] = 1;
		}
		int[] dirX = new int[] {0,0,-1,1};
		int[] dirY = new int[] {-1,1,0,0};
		int water = 0;
		while(!minHeap.isEmpty()) {
			Cell now = minHeap.poll();
			for(int k = 0; k < 4; k++) {
				int x = now.x + dirX[k];
				int y = now.y + dirY[k];
				if(x < m && x >= 0 && y < n && y >= 0 && visited[x][y] != 1) {
					minHeap.offer(new Cell(x, y, Math.max(now.h, heights[x][y])));
					visited[x][y] = 1;
					water += Math.max(0, now.h - heights[x][y]);
				}
			}
		}
		return water;
	}
}
