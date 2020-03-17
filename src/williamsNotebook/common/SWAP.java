/**
 * 
 */
package williamsNotebook.common;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class SWAP {

	public static void charSwap(char[] array, int left, int right) {
		char temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	public static void intSwap(int[] array, int left, int right) {
		int temp = array[left];
		array[left] = array[right];
		array[right] = temp;
	}
	
	public static void matrixSwap(int[][] matrix, int left, int right) {
		int rows = matrix.length, cols = matrix[0].length;

		int temp = matrix[left/cols][left%cols];
		matrix[left/cols][left%cols] = matrix[right/cols][right%cols];
		matrix[right/cols][right%cols] = temp;
	}
}
