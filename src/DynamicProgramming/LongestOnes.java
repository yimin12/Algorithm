/**
 * 
 */
package DynamicProgramming;

import java.util.Currency;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月20日 下午1:49:55
* Description:
* 	Given a array that contains only 1s and 0s, find the largest subsequence which contains only 1s, 
* Assumption:
* 	The given array is not null, has size of N .
* Examples:
* 	{0, 0, 1, 1}		the largest cross of 1s has arm length 2.
*/

public class LongestOnes {
	public int longest(int[] array) {
		int result = 0;
		int cur = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 1) {
				if(i == 0 || array[i-1] == 0) {
//					restart the current value
					cur = 1;
				} else {
					cur++;
				}
			}
			result = Math.max(cur, result);
		}
		return result;
	}
}
