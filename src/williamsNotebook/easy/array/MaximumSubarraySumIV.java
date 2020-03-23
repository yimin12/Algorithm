/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月21日 下午11:33:43
* Description:
* 
* 	Given an integer arrays, find a contiguous subarray which has the largest sum and length should be
*   greater or equal to given length k. Return the largest sum, return 0 if there are fewer than k elements in the array.
*   Ensure that the result is an integer type.
* Example:
* 	 Given the array[-2,2,-3,4,-1,2,1,-5,3]and k =5, the contiguous subarray[2,-3,4,-1,2,1]has the largest sum = 5
*/

public class MaximumSubarraySumIV {

	// Prefix Sum it suitable for this kind of problem
	// Time : O(n) and Extra Space: O(n) for maintain the prefix sum array
	public int maxSubarraySum(int[] array, int k) {
		// Assumption: if the input is invalid, just return -1;
		if(array == null || array.length < k) return -1;
		int n = array.length;
		// step 1: calculate the basic required first k's sum
		int maxSum = 0;
		for(int i = 0; i < k; i++) {
			maxSum += array[i];
		}
		int[] sum = new int[n+1];
		sum[0] = 0;
		int minPrefix = 0;
		// find the minimum value before i - k, and maintain the minimum prefix,
		// then use current prefix Sum minus the minimum prefix before i - k to find the answer
		for(int i = 1; i <= n; i++) {
			sum[i] = sum[i-1] + array[i-1];
			if(i >= k) {
				minPrefix = Math.min(minPrefix, sum[i-k]);
				maxSum = Math.max(maxSum, sum[i] - minPrefix);
			}
		}
		return maxSum;
	}
}
