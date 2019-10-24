/**
 * 
 */
package Common;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月23日 下午4:25:36
* Description:
* 	Rotate an N * N matrix clockwise 90 degrees.
* Assumption:
* 	The matrix is not null and N >= 0;
* Examples:
* 	{ {1,  2,  3}			after rotation is		{ {7,  8,  1}
  	  {8,  9,  4},									  {6,  9,  2},
  	  {7,  6,  5} }									  {5,  4,  3} }		
*/
public class RotateMatrix {
//	Method 1: split into levels and for each level split it into for partition
	public void rotate(int[][] matrix) {
//		Assumption: matrix is not null and has size of N * N, N >= 0
		int n = matrix.length;
		if (n <= 1) {
			return; 
		}
		int round = n/2;
		for(int level = 0; level < round; level++) {
			int left = level;
//			leave the last element stable
			int right = n - 2 - level;
			for (int i = left; i <= right; i++) {
				int tmp = matrix[left][i];
				matrix[left][i] = matrix[n-1-i][left];
				matrix[n-1-i][left] = matrix[n-1-left][n-1-i];
				matrix[n-1-left][n-1-i] = matrix[i][n-1-left];
				matrix[i][n-1-left] = tmp;
			}
		}
	}
//	Method 2: Rotate a point by 90 degree closewise == 
//	1. Mirror the point according to y axis, then 
//	2. Mirror the point according the line of y = x; 
	public void rotateII(int[][] matrix) {
		int n = matrix.length;
		if(n <= 1) {
			return;
		}
		mirrorY(matrix, n);
		mirrorYEX(matrix, n);
	}
//	mirror the point by y axis
	private void mirrorY(int[][] matrix, int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				swap(matrix, i, j, i, n - 1 - j);
			}
		}
	}
//	mirror the point by the line of y = x;
	private void mirrorYEX(int[][] matrix, int n) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j + i < n - 1; j++) {
				swap(matrix, i, j, n-1-i, n-1-j);
			}
		}
	}
	
	private void swap(int[][] matrix, int iRow, int iCol, int jRow, int jCol) {
		int temp = matrix[iRow][iCol];
		matrix[iRow][iCol] = matrix[jRow][jCol];
		matrix[jRow][jCol] = temp;
	}
//
}
