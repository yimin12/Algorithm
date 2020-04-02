/**
 * 
 */
package williamsNotebook.easy.binarySearch;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��7�� ����11:23:27
* Description: 
* 	Search in sorted matrix, each row of the matrix is sorted in ascending order,
* 	and the first element of the row is equals to or larger than the last element of 
* 	previous row, return the position of the target, otherwise return (-1,-1)
*/

/**
 * @author 61771
 *
 */
public class SearchInSortedMatrixI {
	
//	Assumption: matrix is not null, and has size N * M where >= 0 and M >= 0; 
	public int[] searchI(int[][] matrix, int target) {
//		In this method, it will find the row first then column
		int[] result = new int[] {-1, -1};
//		This order should not be reversed, because it will cause npe;
		if(matrix.length == 0 || matrix[0].length == 0) {
			return result;
		}
		int row = findRow(matrix, 0, matrix.length - 1, target);
		if(row == -1) {
			return result;
		}
		int col = findCol(matrix[row], 0, matrix[row].length - 1, target);
		if(col == -1) return result;
		result[0] = row;
		result[1] = col;
		return result;
	}
// Find the largest row with first element <= target
	private int findRow(int[][] matrix, int up, int down, int target) {
		while(up <= down) {
			int mid = up + (down - up)/2;
			if(matrix[mid][0] > target) {
				down = mid - 1;
			} else {
				up = mid + 1;
			}
		}
		return down;
	}
	
//	Used Classical binary search to find the col in that specific row
	private int findCol(int[] array, int left, int right, int target) {
		while(left <= right) {
			int mid = (right - left)/2 + left;
			if(array[mid] == target) {
				return mid;
			} else if(array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
			
		}
		return -1;
	}
//	recommend to convert the 2D array to 1D array and to the binary search
	public int[] search(int[][] matrix, int target) {
//		Sanity Check
		if(matrix.length == 0 || matrix[0].length == 0) {
			return new int[]{-1, -1};
		}
		int rows = matrix.length;
		int cols = matrix[0].length;
		int left = 0, right = rows * cols - 1;
		while(left <= right) {
			int mid = left + (right - left)/2;
//			Convert the index mid to specific coordinate
			int row = mid / cols;
			int col = mid % cols;
			if(matrix[row][col] == target) {
				return new int[] {row, col};
			} else if (matrix[row][col] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return new int[] {-1, -1};
	}
}
