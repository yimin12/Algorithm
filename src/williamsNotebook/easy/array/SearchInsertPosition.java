/**
 * 
 */
package williamsNotebook.easy.array;

/**
 * @author yimin Huang
 * 
 *		Given a sorted array and a target value, return the index if the target is found. If not, 
 *		return the index where it would be if it were inserted in order.
 *		You may assume no duplicates in the array.
 *	
 * Algorithm Class
 */
public class SearchInsertPosition {
	
	public int insertPosition(int[] array, int target) {
		if(array == null) throw new IllegalArgumentException("Invalid input");
		// directly insert when length is 0
		if(array.length == 0) return 0;
		// use binary search find the smallest larger than target
		int left = 0, right = array.length - 1;
		while(left < right - 1) {
			int mid = left + (right - left)/2;
			if(array[mid] >= target) {
				right = mid;
			} else {
				left = mid;
			}
		}
		if(array[left] > target) {

			return left;
		} else if (array[right] > target){
			return right;
		} else { 
			// if the target is larger than all target
			return right+1;
		}
	}
	public static void main(String[] args) {
		SearchInsertPosition solution = new SearchInsertPosition();
		int[] array = new int[] {1,3,5,6};
		int insertPosition = solution.insertPosition(array, 0);
		System.out.println(insertPosition);
	}
}
