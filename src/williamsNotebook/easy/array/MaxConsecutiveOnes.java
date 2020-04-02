/**
 * 
 */
package williamsNotebook.easy.array;

import williamsNotebook.easy.tree.maxPathSum;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月2日 上午11:26:49
* Description:
* 	Given a binary array, find the maximum number of consecutive 1s in this array.
* 	Input: [1,1,0,1,1,1] Output: 3
* 	Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
*/
public class MaxConsecutiveOnes {
	
	// Method 1: Two Pointer:
	public int findMaxConsecutiveOnes(int[] array) {
		if(array == null || array.length == 0) return 0;
		int i = 0, j = 0, max = 0;
		while(i < array.length && j < array.length) {
			while(i < array.length && array[i] != 1) {
				i++;
			}
			// i is the first element of consecutive ones
			i = j;
			while(j < array.length && array[j] == 1) {
				j++;
			}
			max = Math.max(i - j, max);
			i = j;
		}
		return max;
	}
	// Method 2： Dynamic Programming
	public int findMaxConsecutiveOnesDP(int[] array) {
		if(array == null || array.length == 0) return 0;
		int globalMax = Integer.MIN_VALUE, curMax = 0;
		for(int n : array) {
			globalMax = Math.max(globalMax, curMax = n == 0 ? 0 : curMax++);
		}
		return globalMax;
	}
}
