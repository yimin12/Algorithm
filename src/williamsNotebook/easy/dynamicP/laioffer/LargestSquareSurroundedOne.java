/**
 * 
 */
package williamsNotebook.easy.dynamicP.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��20�� ����8:25:59
* Description:
* 	Determine the largest square of 1s in a binary matrix 
* 	(a binary matrix only contains 0 and 1), return the length of the largest square.
* Assumption:
* 	The given matrix is not null and guaranteed to be of size N * N and N >= 0
* ExamplesL:
* 	 {0, 0, 0, 0},  the largest square of 1s has length of 2
  	{1, 1, 1, 1},
  	{0, 1, 1, 1},
  	{1, 0, 1, 1}}
*/

public class LargestSquareSurroundedOne {
	// return the length of the largest square
	public int largest(int[][] matrix) {
		if(matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int result = 0;
		int M = matrix.length;
		int N = matrix[0].length;
		int[][] left = new int[M+1][N+1];
		int[][] up = new int[M+1][N+1];
//		the longest left arm length ending at each position in the matrix, we here apply
//		a trick for ease of later processing.
//		left[i][j] is actually mapped to matrix[i-1][j-1], this will reduced the corner cases
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] == 1) {
					left[i+1][j+1] = left[i+1][j] + 1;
					up[i+1][j+1] = up[i][j+1] + 1;
//					the maximum length of a surrounded by 1 matrix with rightbottom position at 
//					matrix[i][j] is determined by the min value of left[i+1][j+1] and up[i+1][j+1]
//					and we check one by one all the possible lengths if it can provide the actual
//					matrix. we only need to check the longest left arm length of the righttop cell and
//					longest up arm length of the bottom cell;
					for(int maxLength = Math.min(left[i+1][j+1],up[i+1][j+1]);maxLength >= 1;maxLength--) {
						if(left[i+2-maxLength][j+1] >= maxLength && up[i+1][j+2-maxLength] >= maxLength) {
							result = Math.max(result, maxLength);
							break;
						}
					}
				}
			}
		}
		return result;
	}
}
