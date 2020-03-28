/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 下午9:42:59
* Description:
* 	Given a 2D array, find the maximum sum subarray in it. For example, in the following 2D array, the maximum sum subarray is highlighted with blue rectangle and sum of this subarray is 29.
* 	Given a matrix that contains integers, find the submatrix with the largest sum. Return the sum of submatrix
*/

public class LargestSubMatrixSum {

	// Method 1: flatten 2D to 1D and convert it to find maximum subarray
	
	public int largest(int[][] matrix) {
		// Assumptions: matrix is not null and has siz N * M > 0
		int N = matrix.length;
		int M = matrix[0].length;
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			int[] cur = new int[M];
			for(int j = i; j < M; j++) {
				add(cur, matrix[j]);
				result = Math.max(result, max(cur));
			}
		}
		return result;
	}
	private void add(int[] cur, int[] add) {
		for(int i = 0; i < cur.length; i++) {
			cur[i] += add[i];
		}
	}
	private int max(int[] cur) {
		int res = cur[0];
		int temp = cur[0];
		for(int i = 1; i < cur.length; i++) {
			temp = Math.max(temp + cur[i], cur[i]);
			res = Math.max(res, temp);
		}
		return res;
	}
}
