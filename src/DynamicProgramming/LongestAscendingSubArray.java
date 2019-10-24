/**
 * 
 */
package DynamicProgramming;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月18日 上午9:53:13
* Description: 
* 	Given an array A[0]...A[n-1] of integers, find out the length of the 
* 	longest ascending subsequence.
* Assumption:
* 	A is not null;
* Examples:
	Input: A = {5, 2, 6, 3, 4, 7, 5}
	Output: 4
	Because [2, 3, 4, 5] is the longest ascending subsequence.
*/

public class LongestAscendingSubArray {
	public int longest(int[] array) {
//		Assumption: the given array is not null
		if(array.length == 0) {
			return 0;
		}
//		case 1: dp[i] = 1			(array[i] <= array[i-1])
//		case 2: 	  = dp[i-1] + 1 (array[i] > array[i-1])
//		So we can make the space consumption more efficient by only record the latest dp[i]
		int result = 1;
		int cur = 1;
		for(int i = 1; i < array.length; i++) {
			if(array[i] > array[i-1]) {
//				the second case:
				cur++;
				result = Math.max(result, cur);
			} else {
//				the first case;
				cur = 1;
			}
		}
		return result;
	}
}
