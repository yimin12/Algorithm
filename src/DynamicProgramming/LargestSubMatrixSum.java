/**
 * 
 */
package DynamicProgramming;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月20日 下午10:37:46
* Description:
* 	Given a matrix that contains integers, find the submatrix with the largest sum.
* 	Return the sum of the sub matrix
* Assumption:
* 	The given matrix is not null and has size of M * N, where M >= 1 and N >=1
* Examples:
*  {{1, -2, -1, 4},		the largest submatrix sum is (-1) + 4 + 1 + 1 + (-1) + 1 + 1 + 1 = 7.
  	{1, -1,  1, 1},
    {0, -1, -1, 1},
  	{0,  0,  1, 1} }
*/

public class LargestSubMatrixSum {
	public int largest(int[][] matrix) {
		int N = matrix.length;
		int M = matrix[0].length;
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			int[] cur = new int[M];
			for(int j = 0; j < M; j++) {
//				for each row i, we add rows one by one for row j. to make sure each time we only
//				introduce O(n) time
			add(cur, matrix[i]);
//			for each possible pair of rows i,j we would like to know what is the largest sub matrix
//			sum using top row as row i and bottom row as row j. we 'flatten' these area to one dimensional
//			array
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
//	largest sub-array sum.
	private int max(int[] cur) {
		int result = cur[0];
		int temp = cur[0];
		for(int i = 0; i < cur.length; i++) {
			temp = Math.max(cur[i], cur[i] + temp);
			result = Math.min(result, temp);
		}
		return result;
	}
}
