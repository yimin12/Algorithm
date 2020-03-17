/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import williamsNotebook.common.SWAP;
/**
 * @author yimin Huang
 * 	
 *	Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *	Integers in each row are sorted from left to right.
 *	The first integer of each row is greater than the last integer of the previous row.
 *
 * Algorithm Class
 */
public class MatrixSearch {

	// binary search in matrix
	// Time: O(m*n) Space: O(1)
	public int[] search(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[] {-1,-1};
		// flatten the 2D into 1D array
		int n = matrix.length * matrix[0].length;
		int left = 0, right = n - 1;
		int rows = matrix.length, cols = matrix[0].length;
		while(left < right) {
			int mid = left + (right - left)/2;
			int candidate = matrix[mid/cols][mid%cols];
			if(candidate == target) {
				return new int[] {mid/cols, mid%cols};
			} else if (candidate > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return new int[] {-1,-1};
	}
	// Follow Up 2: Given a matrix of size N*N, and for each row the elements are sorted in an ascending order. and for each column the elements are also sorted
	// 		in an ascending order. Return the kth smallest value
	// Method 1: use best first search with maxHeap
	public int searchKthMatrix(int[][] matrix, int k) {
		// Assumption:
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return -1;
		int rows = matrix.length, cols = matrix[0].length;
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1.equals(o2)) {
					return 0;
				}
				return o1 > o2 ? -1 : 1;
			}
		}); // create maxHeap with size k
		int count = 0;
		for(int i = 0; i < matrix.length*matrix[0].length; i++){
			int val = matrix[i/cols][i%cols];
			if(i < k) {
				maxHeap.offer(val);
			} else {
				if(maxHeap.peek() > val) {
					maxHeap.poll();
					maxHeap.offer(val);
				}	
			}
		}
		return maxHeap.peek();
	}
	// Method 2: quick select
	public int[]  kthSmallestPartition(int[][] matrix, int k) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[] {-1,-1};
		int rows = matrix.length, cols = matrix[0].length;
		quickSelect(matrix, 0, rows * cols - 1, k - 1);
		int[] result = toList(matrix, k);
		Arrays.sort(result);
		return result;
	}
	private void quickSelect(int[][] matrix, int left, int right, int target) {
		// like quick sort, we need to partition using pivot
		int mid = partition(matrix, left, right);
		if(mid == target) {
			return;
		} else if(target < mid) {
			quickSelect(matrix, left, mid - 1, target);
		} else {
			quickSelect(matrix, mid + 1, right, target);
		}
	}
	private int partition(int[][] matrix, int left, int right) {
		int rows = matrix.length, cols = matrix[0].length;
		int pivot = matrix[right/cols][right%cols];
		int start = left, end = right - 1;
		while(start <= end) {
			if(matrix[start/cols][start%cols] < pivot) {
				start++;
			} else if(matrix[end/cols][end%cols] >= pivot) {
				end--;
			} else {
				SWAP.matrixSwap(matrix, left++, right--);
			}
		}
		SWAP.matrixSwap(matrix, start, right);
		return start;
	}
	private int[] toList(int[][] matrix, int k) {
		int rows = matrix.length, cols = matrix[0].length;
		int[] result = new int[k];
		for(int i = 0; i < k; i++) {
			result[i] = matrix[i/cols][i%cols];
		}
		Arrays.sort(result);
		return result;
			
	}
	public static void main(String[] args) {
		MatrixSearch solution =  new MatrixSearch();
		int[][] matrix = new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		int[] search = solution.kthSmallestPartition(matrix, 3);
		System.out.println(Arrays.toString(search));
	}
}
