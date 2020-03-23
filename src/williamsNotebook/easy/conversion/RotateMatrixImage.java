/**
 * 
 */
package williamsNotebook.easy.conversion;

import java.util.Arrays;
import williamsNotebook.common.SWAP;

/**
 * @author yimin Huang
 *	
 *	 Rotate an N*N matrix closewise 90 degree
 *	
 * Algorithm Class
 */
public class RotateMatrixImage {
	
	// Method 1: do it inplace, split into levels and for each level split into four partitions
	// Time: O(n^2) Extra Space: O(1)
	public void rotate(int[][] matrix) {
		// matrix is not null and has size of N * N, N >= 0
		int n = matrix.length;
		// base case
		if(n < 1) return;
		int rount = n/2;
		for(int level = 0; level < rount; level++) {
			int left = level;
			int right = n - 2 - level;
			for(int i = left; i <= right; i++) {
				int temp = matrix[left][i];
				matrix[left][i] = matrix[n-1-i][left];
				matrix[n-1-i][left] = matrix[i][n-1-left];
				matrix[i][n-1-left] = temp;
			}
		}
	}
	
	// Method 2: Reverse Each Row and flip diagonally
	// Time: O(n^2) and Extra Space: O(1)
	public void rotateII(int[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		// step 1: reverse each row
		reverse(matrix);
		// step 2: flip diagonally
		flip(matrix);
	}
	public void reverse(int[][] matrix) {
		int n = matrix.length;
		for(int[] row:matrix) {
			int i = 0, j = n - 1;
			while(i < j) {
				SWAP.intSwap(row, i++, j--);
			}
		}
	}
	public void flip(int[][] matrix) {
		int n = matrix.length;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n - 1 - i; j++) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[n-1-j][n-1-i];
				matrix[n-1-j][n-1-i] = tmp;
			}
		}
	}
	
	public static void main(String[] args) {
		RotateMatrixImage solution = new RotateMatrixImage();
		int[][] matrix = new int[][] {{ 5, 1, 9,11},
									  { 2, 4, 8,10},
									  {13, 3, 6, 7},
									  {15,14,12,16}};
		solution.rotateII(matrix);
		System.out.println(Arrays.deepToString(matrix));
	}
}
