/**
 * 
 */
package williamsNotebook.easy.binarySearch;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午9:11:48
* Description:
* 	Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal 
* 	or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood? Given L 
* 	& k, return the maximum length of the small pieces.
* Example:
* 	For L=[232, 124, 456], k=7, return 114.
*/

public class WoodCut {

	 /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     *           return: The maximum length of the small pieces.
     */
	// O(n log Len), where Len is the longest length of the wood.
	// New trick of using binary search
	public int woodCut(int[] L, int k) {
		// the length of wood should larger than 1
		int left = 1, right = 0, res = 0;
		for(int item : L) {
			right = Math.max(right, item);
		}
		while(left <= right) {
			int mid = left + (right - left)/2;
			if(count(L, mid) >= k) {
				res = mid;
				right = mid+1;
			} else {
				left = mid-1;
			}
		}
		return res;
	}
	private int count(int[] L, int len) {
		int sum = 0;
		for(int item : L) {
			sum += item/len;
		}
		return sum;
	}
}
