/**
 * 
 */
package Heap_GraphSearchAlgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月11日 上午11:03:51
* Description: return the kth smallest value in an sorted matrix
* 	Assumption: 
* 		1) matrix is not null, N*M where N > 0; M > 0
* 		2) k > 0 and k <= N * M
*/


public class KthSamllestInSortedMatrix {
	public int kthSmallest(int[][] matrix, int k) {
		int rows = matrix.length;
		int cols = matrix[0].length;
//		Best First Search, need a minheap on the value of each cells
		PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new Comparator<Cell>() {

			@Override
			public int compare(Cell o1, Cell o2) {
				if(o1.value == o2.value) {
					return 0;
				}
//				min heap
				return o1.value < o2.value? -1:1;
			}
			
		});
//		all the generated cells will be marked true to avoid being generated more than once
		boolean[][] visited = new boolean[rows][cols];
		minHeap.offer(new Cell(0, 0, matrix[0][0]));
		visited[0][0] = true;
//		iterate k-1 rounds, and bfs the smallest k-1 cells, at the end, it contains k candidates and pick the smallest one
		for(int i=0; i < k - 1; i++) {
			Cell cur = minHeap.poll();
//			the neighbor cell will be inserted back to the minheap only if
//			1. it is not out of boundary
//			2. it has not been generate before
//			Because for each cell it could be generated twice
			if(cur.row + 1 < rows && !visited[cur.row+1][cur.col]) {
				minHeap.offer(new Cell(cur.row+1, cur.col, matrix[cur.row+1][cur.col]));
				visited[cur.row + 1][cur.col] = true;
			}
			if(cur.col + 1 < cols && !visited[cur.row][cur.col +1]) {
				minHeap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col+1]));
				visited[cur.row][cur.col +1] = true;
			}
		}
		return minHeap.peek().value;
	}
	static class Cell{
		int row;
		int col;
		int value;
		Cell(int row, int col, int value){
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}
}
