/**
 * 
 */
package RecursionAndSorting;

import java.util.ArrayList;
import java.util.List;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月16日 下午3:04:32
* Description:
* 	Traverse an N * N 2D array in spiral order clock-wise starting from the top left corner.
* 	Return the list of traversal sequence.
* Assumptions:
	The 2D array is not null and has size of N * N where N >= 0
  Examples:
		{ {1,  2,  3},        the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]
		  {4,  5,  6},
		  {7,  8,  9} } 
*/

public class spiralTraversalI {
//	Method 1: Recursive traversal
	public List<Integer> sprial(int[][] matrix){
//		Assumption: matrix is N * N, N>=0, matrix is not null
		List<Integer> list = new ArrayList<Integer>();
		recursiveTraverse(matrix, 0, matrix.length, list);
		return list;
	}
	private void recursiveTraverse(int[][] matrix, int offset, int size, List<Integer> list) {
//		base case is that when there is only 0 or 1 element left
		if(size == 0) return;
		if(size == 1) {
			list.add(matrix[offset][offset]);
			return;
		}
		for(int i = 0; i < size - 1; i++) {
			list.add(matrix[offset][offset+i]);
		}
		for(int i = 0; i < size - 1; i++) {
			list.add(matrix[offset+i][offset + size - 1]);
		}
		for(int i = size - 1; i >= 1; i--) {
			list.add(matrix[offset + size - 1][offset + i]);
		}
		for(int i = size - 1; i >= 1; i--) {
			list.add(matrix[offset + i][offset + size - 1]);
		}
		recursiveTraverse(matrix, offset+1, size-2, list);
	}
//	Method 2: Iterative traversal
	public List<Integer> spiralII(int[][] matrix){
//		Assumptions: matrix is N * N, N >= 0, matrix is not null
		List<Integer> list = new ArrayList<Integer>();
		int n = matrix.length;
		int start = 0, end = n - 1;
//		the base case is when there is 0 or 1 in the sub-matrix, which means start < end or start == end
		while(start < end) {
			for(int i = start; i < end; i++) {
				list.add(matrix[start][i]);
			}
			for(int i = start; i < end; i++) {
				list.add(matrix[i][end]);
			}
			for(int i = end; i > start; i--) {
				list.add(matrix[end][i]);
			}
			for(int i = end; i > start; i--) {
				list.add(matrix[i][start]);
			}
			start++;
			end--;
		}
//		if at last we have 1 element, we need to traverse it as well
		if(start == end) {
			list.add(matrix[start][end]);
		}
		return list;
	}
}
