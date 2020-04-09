/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.HashMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午4:23:09
* Description:
* 	Given four lists A, B, C, D of integer values, compute how many tuples(i, j, k, l)there are such that
* 	A[i] + B[j] + C[k] + D[l]is zero.
* 	To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in 
* 	the range of -2^28 to 2^28- 1 and the result is guaranteed to be at most 2^31- 1
* Example:
* 	Input:				Output: 2
		A = [ 1, 2]
		B = [-2,-1]
		C = [-1, 2]
		D = [ 0, 2]
	Explanation:
	The two tuples are:
	1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
	2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
*/
public class FourSumII {

	// Time: Complexity O(n^2) and Space: O(n^2)
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int count = 0;
		for(int i = 0; i < A.length; i++) {
			for(int j = 0; j < B.length; j++) {
				int sum = A[i] + B[j];
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
		}
		for(int k = 0; k < C.length; k++) {
			for(int l = 0; l < D.length; l++) {
				int sum = C[k] + D[l];
				count += map.getOrDefault(-sum, 0);
			}
		}
		return count;
	}
}
