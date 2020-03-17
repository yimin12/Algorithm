/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
 * @author yimin Huang
 *	
 *	Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
 *	Input: 			Output:
		[				[
		  [1,1,1],		  [1,0,1],
		  [1,0,1],		  [0,0,0],
		  [1,1,1]		  [1,0,1],
		]				]
 * Algorithm Class
 */
public class SetMatrixZeros {

	// Iterative all elements in the matrix and do the clean method
	// push the 0 to the border
	public void setZeros(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
		boolean isFirstRow = false, isFirstCol = false;
		int m = matrix.length, n = matrix[0].length;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(matrix[i][j] == 0) {
					if(i == 0) isFirstRow = true;
					if(j == 0) isFirstCol = true;
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}
		for(int i = 1; i < m; i++) {
			 if(matrix[i][0] == 0) {
				 for(int j = 0; j < n; j++) {
					 matrix[i][j] = 0;
				 }
			 }
		}
		for(int i = 1; i < n; i++) {
			if(matrix[0][i] == 0) {
				for(int j = 0; j < m; j++) {
					matrix[j][i] = 0;
				}
			}
		}
		if(isFirstRow) {
			for(int i = 0; i < n; i++) {
				matrix[0][i] = 0;
			}
		}
		if(isFirstCol) {
			for(int j = 0; j < m; j++) {
				matrix[j][0] = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		SetMatrixZeros solution = new SetMatrixZeros();
		int[][] matrix = new int[][] {{1,1,1},{1,0,1},{1,1,1},{1,1,1}};
		solution.setZeros(matrix);
		System.out.println(Arrays.deepToString(matrix));
 	}
}
