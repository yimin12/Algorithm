/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 上午10:51:29
* Description:
* 	Given two array of integers(the first array is array A, the second array is array B), now we are going 
* 	to find a element in array A which is A[i], and another element in array B which is B[j], so that the 
* 	difference between A[i] and B[j] (|A[i] - B[j]|) is as small as possible, return their smallest difference.
* 	For example, given array A = [3,6,7,4], B = [2,8,9,3], return 0
*/
public class TheSmallestDifference {

	
	// We sort it first with Time: O(nlogn)
	public int smallestDifference(int[] A, int[] B) {
		if(A == null || B == null || A.length == 0 || B.length == 0) return 0;
		Arrays.sort(A);
		Arrays.sort(B);
		int i = 0, j = 0;
		int diff = Integer.MAX_VALUE;
		while(i < A.length && j < B.length) {
			diff = Math.min(diff, Math.abs(A[i] - B[j]));
			if(A[i] < B[j]) {
				i++;
			} else {
				j++;
			}
		}
		return diff;
	}
}
