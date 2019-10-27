/**
 * 
 */
package DFS_BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月24日 下午2:00:47
* Description:
* 	Given a non-negative integer 2D array representing the heights of bars in a matrix. Suppose each 
* 	bar has length and width of 1. Find the largest amount of water that can be trapped in the matrix. 
* 	The water can flow into a neighboring bar if the neighboring bar's height is smaller than the water's 
* 	height. Each bar has 4 neighboring bars to the left, right, up and down side.
* Assumptions:
* 	The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values are
*   non-negative integers in the matrix.
* Examples:
* 	{ { 2, 3, 4, 2 },		the amount of water can be trapped is 3, 
  	  { 3, 1, 2, 3 },		at position (1, 1) there is 2 units of water trapped,
      { 4, 3, 5, 4 } }		at position (1, 2) there is 1 unit of water trapped.
*/
public class MaxWaterTrappedII {
	public int maxTrapped(int[][] matrix) {
//		Assumption: matrix is not null and has size of M*N, M > 0 & N > 0, all the values are non-negative integers
		int rows = matrix.length;
		int cols = matrix[0].length;
		if(rows < 3 || cols <3) {
			return 0;
		}
//		Best-First-Search, minHeap maintains all the border cells of the "closed area" and we always find the one
//		with lowest height to see if any of its neightbors can trap any water
		PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>();
		boolean[][] visited = new boolean[rows][cols];
//		put all the border cells of the matrix at the very begining
		processBorder(matrix, visited, minHeap, rows, cols);
		int result =0;
		while(!minHeap.isEmpty()) {
			Pair cur = minHeap.poll();
//			get all possible neighbor cells
			List<Pair> neighbors = allNeighbors(cur, matrix, visited);
			for(Pair nei:neighbors) {
//				if any neighbor has been visited before, we just ignore
				if(visited[nei.x][nei.y]) {
					continue;
				}
//				adjust the neighbor cell's height to the current water level if necessary, mark the neighbor cell
//				as visited, and put neighbor cell into the min heap.
				visited[nei.x][nei.y]=true;
//				how much water can be trapped at the neighbor cell, the maximum water level currently is 
//				controlled by the cur cell.
				result += Math.max(cur.height - nei.height, 0);
				nei.height = Math.max(cur.height, nei.height);
				minHeap.offer(nei);
			}
		}
		return result;
	}
//	Put all the border cells into the min heap at the very beginning, these are the start points of the whole BFS process
	private void processBorder(int[][] matrix, boolean[][] visited, PriorityQueue<Pair> minHeap, int rows, int cols) {
		for(int j = 0; j < cols; j++) {
			minHeap.offer(new Pair(0, j, matrix[0][j]));
			minHeap.offer(new Pair(rows - 1, j, matrix[rows-1][j]));
			visited[0][j] = true;
			visited[rows-1][j]=true;
		}
		for(int i = 0; i < rows - 1; i++) {
			minHeap.offer(new Pair(i, 0, matrix[i][0]));
			minHeap.offer(new Pair(i, cols - 1, matrix[i][cols-1]));
			visited[i][0] = true;
			visited[i][cols - 1] = true;
		}
	}
	
	private List<Pair> allNeighbors(Pair cur, int[][] matrix, boolean[][] visited){
		List<Pair> neis = new ArrayList<Pair>();
		if(cur.x + 1 < matrix.length) {
			neis.add(new Pair(cur.x+1, cur.y, matrix[cur.x+1][cur.y]));
		}
		if(cur.x -1 >= 0) {
			neis.add(new Pair(cur.x - 1, cur.y, matrix[cur.x-1][cur.y]));
		}
		if(cur.y + 1 < matrix[0].length) {
			neis.add(new Pair(cur.x, cur.y+1, matrix[cur.x][cur.y+1]));
		}
		if(cur.y - 1 >= 0) {
			neis.add(new Pair(cur.x, cur.y-1, matrix[cur.x][cur.y - 1]));
		}
		return neis;
	}
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		int height;
		Pair(int x, int y, int height){
			this.x = x;
			this.y = y;
			this.height = height;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.height == o.height) {
				return 0;
			}
			return this.height < o.height ? -1:1;
		}
		
	}
}
