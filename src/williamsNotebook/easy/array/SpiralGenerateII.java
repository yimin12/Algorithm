/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��16�� ����10:07:09
* Description:
* 	Generate an M * N 2D array in spiral order clock-wise starting from the top left corner, 
* 	using the numbers of 1, 2, 3, ��, M * N in increasing order.
  Assumptions: M >= 0, N >= 0
  Examples:
	M = 3, N = 4, the generated matrix is
	{ {1,  2,  3,  4}
	  {10, 11, 12, 5},
	  {9,  8,  7,  6} }
*/

public class SpiralGenerateII {

//	Assumption: m, n >= 0;
	public int[][] sprialGenerate(int m, int n){
		int[][] matrix = new int[m][n];
		if(m == 0 || n == 0) {
			return matrix;
		}
		int num = 1;
		int left = 0;
		int right = n - 1;
		int up = 0;
		int down = m - 1;
		while(left < right && up < down) {
			for(int i = left; i <= right; i++) {
				matrix[up][i] = num++;
			}
			for(int i = up + 1; i <= down - 1; i++) {
				matrix[i][right] = num++;
			}
			for(int i = right; i >= left; i--) {
				matrix[down][i] = num++;
			}
			for(int i = down - 1; i >= up; i--) {
				matrix[i][left] = num++;
			}
			left++;
			right--;
			up++;
			down--;
		}
		if(left > right || up > down) {
			return matrix;
		}
		if(left == right) {
			for(int i = up; i <= down; i++) {
				matrix[i][left] = num++;
			}
		} else {
			for(int i = left; i <= right; i++) {
				matrix[up][i] = num++;
			}
		}
		return matrix;
	}
	
}
