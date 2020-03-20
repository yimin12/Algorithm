/**
 * 
 */
package williamsNotebook.easy.array;

/**
 * @author yimin Huang
 *	
 *	A peak element is an element that is greater than its neighbors.
 *	Given an input array where num[i] 鈮� num[i+1], find a peak element and return its index.
 *	The array may contain multiple peaks, in that case return the index to any one of the peaks is fine
 *	You may imagine that num[-1] = num[n] = -鈭�
 *	For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.	
 *	Your solution should be in logarithmic complexity.
 *
 * Algorithm Class
 */
public class PeakElement {

	// 1D peak finding
	// Time : O(logn) for binary search, Space: O(1)
	public int findPeakElement(int[] num) {
		int left = 0, right = num.length - 1;
		while(left <= right) {
			int mid = left + (right - left)/2;
			if(mid < num.length - 1 && num[mid] < num[mid+1]) left = mid + 1;
			else if(mid > 0 && num[mid] < num[mid - 1]) right = mid;
			else return mid;
		}
		return -1;  // the peak doesn't exist
	}
	
	// Follow Up : 2D Peak Finding
	// Time: O(nlogn) where n is the numbers of element of grid, Space: O(cols/2) for call stack
	public int findPeakElement2D(int[][] grid) {
		if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
		int m = grid.length, n = grid[0].length;
		return findPeakRec(grid, m, n, n / 2);
	}
	private int findPeakRec(int[][] grid, int rows, int cols, int mid) {
		int max = 0;
		int max_index = findMax(grid, rows, mid, max);
		max = Max(grid, rows, mid, max);
		// if we are on the first or last column
		if(mid == 0 || mid == cols - 1) {
			return max;
		}
		// if mid col maximum is also peak, logic is the same as the 1D problem
		if(max >= grid[max_index][mid-1] && max >= grid[max_index][mid+1]) return max;
		if(max < grid[max_index][mid-1]) return findPeakRec(grid, rows, cols, (int)(mid - Math.ceil((double)mid/2)));
		else return findPeakRec(grid, rows, cols, (int)(mid + Math.ceil((double)mid/2)));
	}
	private int findMax(int[][] grid, int rows, int mid, int max) {
		int max_index = 0;
		for(int i = 0; i < rows; i++) {
			if(max < grid[i][mid]) {
				max = grid[i][mid];
				max_index = i;
			}
		}
		return max_index;
	}
	private int Max(int[][] grid, int rows, int mid, int max) {
		for(int i = 0; i < rows; i++) {
			if(max < grid[i][mid]) max = grid[i][mid];
		}
		return max;
	}
	
	public static void main(String[] args) {
		PeakElement solution = new PeakElement();
		 int[][] grid = {{ 10, 8, 10, 10 }, 
		                { 14, 13, 12, 11 },  
		                { 15, 9, 11, 21 },  
		                { 16, 17, 19, 20 }}; 
		 int findPeakElement2D = solution.findPeakElement2D(grid);
		 System.out.println(findPeakElement2D);
	}
}
