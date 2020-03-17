/**
 * 
 */
package williamsNotebook.easy.array;

/**
 * @author yimin Huang
 *	
 *	A peak element is an element that is greater than its neighbors.
 *	Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 *	The array may contain multiple peaks, in that case return the index to any one of the peaks is fine
 *	You may imagine that num[-1] = num[n] = -∞
 *	For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.	
 *	Your solution should be in logarithmic complexity.
 *
 * Algorithm Class
 */
public class PeakElement {

	// 1D peak finding
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
	
	// Follow Peak Finding
	
}
