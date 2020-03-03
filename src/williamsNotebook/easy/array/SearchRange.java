/**
 * 
 */
package williamsNotebook.easy.array;

/**
 * @author yimin Huang
 *
 *	Given sorted array of integers, find the starting and ending position of a given target value.
 *	Your algorithm's runtime complexity must be in the order of O(log n).
 *	Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
 *
 * Algorithm Class
 */
public class SearchRange {

	// Method 1: Binary Search
	// Binary Search: Time ~ O(2logN), Space ~ O(1)
	// step 1: find the first match point
	// step 2: find the last  match point
	public int[] searchRange(int[] array, int target) {
		// Assumption: array is not null
		int left = 0, right = array.length;
		// step 1: find the first match value
		while(left < right) {
			int mid = left + (right - left)/2;
			if(array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		int start = left;
		if(array[start] != target) return new int[] {-1,-1};
		// step 2: find the last match value
		// reset the right index
		right = array.length - 1;
		while(left < right - 1) {
			int mid = left + (right - left)/2;
			if(array[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		int end = 0;
		if(array[right] == target) {
			end = right;
		} else {
			end = left;
		}
		return new int[] {start, end};
	}
	
	
	
	public static void main(String[] args) {
		SearchRange solution = new SearchRange();
		int[] searchRange = solution.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8);
		for(int i : searchRange) {
			System.out.print(i + " ");
		}
	}
}
