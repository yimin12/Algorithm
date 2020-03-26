/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午8:11:38
* Description:
* 	Find the kth smallest number in a row and column sorted matrix.	
* 	Given k = 4 and a matrix:
* 	[
	  [1 ,5 ,7],
	  [3 ,7 ,8],
	  [4 ,8 ,9],
	]
*/

public class KthSmallestInSortedMatrix {

	//Method 1: Priority Queue, generating in two direction
	static class Number{
		int x, y, val;
		public Number(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}
	class NumberComparator implements Comparator<Number>{
		@Override
		public int compare(Number o1, Number o2) {
			return o1.val - o2.val;
		}
	}
	private boolean isValid(int x, int y, int[][] matrix, boolean[][] visited) {
		if(x < matrix.length && y < matrix[x].length && !visited[x][y]) {
			return true;
		}
		return false;
	}
	int[] dx = new int[] {0, 1};
	int[] dy = new int[] {1, 0};
	public int kthSmallest(int[][] matrix, int k) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix.length*matrix[0].length < k) {
			return -1;
		}
		PriorityQueue<Number> heap = new PriorityQueue<Number>(k, new NumberComparator());
		heap.add(new Number(0, 0, matrix[0][0]));
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		visited[0][0] = true;
		for(int i = 0; i < k - 1; i++) {
			Number cur = heap.poll();
			for(int j = 0; j < 2; j++) {
				int x = cur.x + dx[j];
				int y = cur.y + dy[j];
				if(isValid(x, y, matrix, visited)) {
					visited[x][y] = true;
					heap.add(new Number(x, y, matrix[x][y]));
				}
			}
		}
		return heap.peek().val;
	}
	// Min Heap, the generating rules has slightly different,
	// choose one bound and traverse in vertical direction
	public int kthSmallestI(int[][] matrix, int k) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0 || matrix.length*matrix[0].length < k) {
			return -1;
		}
		int m = matrix.length, n = matrix[0].length;
		PriorityQueue<Number> minHeap = new PriorityQueue<Number>((a, b)->a.val - b.val);
		for(int i = 0; i < m; i++) {
			minHeap.offer(new Number(i, 0, matrix[i][0]));
		}
		for(int i = 0; i < k - 1; i++) {
			Number cur = minHeap.poll();
			if(cur.y + 1 < n) {
				minHeap.offer(new Number(cur.x, cur.y + 1, matrix[cur.x][cur.y+1]));
			}
		}
		Number kth = minHeap.peek();
		return kth.val;
	}
}
