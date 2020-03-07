/**
 * 
 */
package williamsNotebook.easy.conversion;

/**
 * @author yimin Huang
 *	
 *	 Rotate an N*N matrix closewise 90 degree
 *	
 * Algorithm Class
 */
public class RotateMatrixImage {
	
	// Method 1: do it inplace, split into levels and for each level split into four partitions
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
				matrix[left][i] = matrix[n-1-left][n-1-i];
				matrix[n-1-left][n-1-i] = matrix[i][n-1-left];
				matrix[i][n-1-left] = temp;
			}
		}
	}
}
