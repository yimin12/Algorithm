/**
 * 
 */
package DynamicProgramming;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月20日 上午9:15:17
* Description:
* 	Determine the largest square of 1s in a binary matrix 
* 	(a binary matrix only contains 0 and 1), return the length of the largest square.
* Assumption:
* 	The given matrix in not null and guaranteed to be of size N * N and N >= 0
* Examples:
* 	{ {0, 0, 0, 0},
  	{1, 1, 1, 1},
  	{0, 1, 1, 1},
	{1, 0, 1, 1}}

the largest square of 1s has length of 2
*/

public class LargestSquareOfOnes {
	public int largest(int[][] matrix) {
		int N = matrix.length;
		if(N == 0) return 0;
		int result = 0;
//		dp[i][j] means the largest square of 1's with right bottom corner as matrix[i][j]
		int[][] largest = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == 0 || j == 0) {
					largest[i][j] = matrix[i][j] == 1 ? 1:0;
				} else if (matrix[i][j] == 1) {
//					if the matrix[i][j] == 0; means that there a gap between two are, it will start
//					from 0. The default value of matrix[i][j] is zero
					largest[i][j] = Math.min(largest[i][j-1] + 1, largest[i-1][j] + 1);
					largest[i][j] = Math.min(largest[i-1][j-1]+1, largest[i][j]);
				}
				result = Math.max(largest[i][j], result);
			}
		}
		return result;
	}
}
