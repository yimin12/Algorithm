/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月3日 下午10:36:09
* Description:
* 	Give an integer array，find the longest increasing continuous subsequence in this array.
* 	An increasing continuous subsequence:
* 	Can be from right to left or from left to right.
* 	Indices of the integers in the subsequence should be continuous.
* 	O(n) time and O(1) extra space.
* 	For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.
* 	For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.
*/
public class LongestIncreasingContinuousSubsequence {

	public int longestIncreasingContinuousSubsequence(int[] A) {
		if(A == null || A.length == 0) {
			return 0;
		}
		int res = 1;
		int N = A.length;
		// step 1: from left to right
		int len = 1;
		for(int i = 1; i < N; i++) {
			if(A[i] > A[i-1]) {
				len++;
			} else {
				len = 1;
			}
			res = Math.max(len, res);
		}
		len = 1;
		for(int i = N - 1; i > 0; i--) {
			if(A[i-1] > A[i]) {
				len++;
			} else {
				len = 1;
			}
			res = Math.max(res, len);
		}
		return res;
	}
}
