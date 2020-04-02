/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��16�� ����8:42:05
* Description: 
* 	Generate an N * N 2D array in spiral order clock-wise starting from the top left corner, 
* 	using the numbers of 1, 2, 3, ��, N * N in increasing order.
* Assumptions: N >= 0
* Examples:
	N = 3, the generated matrix is
	{ {1,  2,  3}
	
	  {8,  9,  4},
	
	  {7,  6,  5} }
*/
public class SpiralGenerateI {

	//Method 1: Recursive Generate
	public int[][] spiralGenerate(int n){
//		Assumption: n >= 0
		int[][] matrix = new int[n][n];
		if(n == 0) {
			return matrix;
		}
		recursiveGenerate(matrix, 0, n, 1);
		return matrix;
	}
	private void recursiveGenerate(int[][] matrix, int offset, int size, int num) {
//		the base case is when there is only 0 or 1 element left;
		if(size == 0) return;
		if(size == 1) {
			matrix[offset][offset]=num;
			return;
		}
		for(int i = 0; i < size - 1; i++) {
			matrix[offset][offset+i] = num++;
		}
		for(int i = 0; i < size - 1; i++) {
			matrix[i][offset+size-1] = num++;
		}
		for(int i = size - 1; i > 0; i--) {
			matrix[offset+size-1][offset+i] = num++;
		}
		for(int i = size - 1; i > 0; i--) {
			matrix[offset+i][offset]=num++;
		}
		recursiveGenerate(matrix, offset+1, size-2, num);
	}
//	Method 2:Iterative way
	public int[][] spiralGenerateII(int n){
//		Assumption: n >= 0;
		int[][] matrix = new int[n][n];
		if(n == 0) {
			return matrix;
		}
		int start = 0, end = n - 1;
		int num = 1;
//		base case is there is 0 and 1 element left.
		while(start<end) {
			for(int i = start; i <= end; i++) {
				matrix[start][i] = num++;
			}
			for(int i = start + 1; i <= end - 1; i++) {
				matrix[i][end] = num++;
			}
			for(int i = end; i >= start; i--) {
				matrix[end][i] = num++;
			}
			for(int i = end; i >= start + 1; i--) {
				matrix[i][start] = num++;
			}
			start++;
			end--;
		}
		if(start == end) {
			matrix[start][end] = num++;
		}
		return matrix;
	}
	
	
}
