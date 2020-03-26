/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午8:49:11
* Description:
* 	Given two integer arrays sorted in ascending order and an integer k. Define sum = a + b, where a is an element from 
* 	the first array and b is an element from the second one. Find the kth smallest sum out of all possible sums.
* Given [1, 7, 11] and [2, 4, 6].
		For k = 3, return 7.
		For k = 4, return 9.
		For k = 8, return 15.
		Do it in either of the following time complexity:
		O(k log min(n, m, k)). where n is the size of A, and m is the size of B.
		O( (m + n) log maxValue). where maxValue is the max number in A and B.
*/
public class KthSmallestSumInTwoSortedArrays {

	// Best First Search for meeting the complexity requirement
	static class Cell{
		int x, y, sum;
		public Cell(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}
	static final int[] dx = new int[] {0,1};
	static final int[] dy = new int[] {1,0};
	public boolean isValid(int x, int y, int[] A, int[] B, boolean[][] visited) {
		if(x < A.length && y < B.length && !visited[x][y]) {
			return true;
		}
		return false;
	}
	public int kthSmallestSum(int[] A, int[] B, int k) {
		if (A == null || B == null || A.length == 0 || B.length == 0 || A.length * B.length < k) {
			return -1;
		}
		PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>((a, b) -> a.sum - b.sum);
		minHeap.offer(new Cell(0, 0, A[0] + B[0]));
		boolean[][] visited = new boolean[A.length][B.length];
		for(int i = 0; i < k - 1; i++) {
			Cell cur = minHeap.poll();
			for(int j = 0; j < 2; j++) {
				int x = cur.x + dx[j];
				int y = cur.y + dy[j];
				if(isValid(x, y, A, B, visited)) {
					visited[x][y] = true;
					int nextSum = A[x] + B[y];
					minHeap.offer(new Cell(x, y, nextSum));
				}
			}
		}
		return minHeap.peek().sum;
	}
	
	public static void main(String[] args) {
		KthSmallestSumInTwoSortedArrays solution = new KthSmallestSumInTwoSortedArrays();
		int[] A = {1, 7, 11};
		int[] B = {2, 4, 6};
		int kthSmallestSumII = solution.kthSmallestSum(A, B, 4);
		System.out.println(kthSmallestSumII);
	}
}
