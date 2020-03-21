/**
 * 
 */
package williamsNotebook.easy.array;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月21日 下午1:11:01
* Description:
* 	Given an array of integers, find two non-overlapping subarrays which have the largest sum. 
* 	The number in each subarray should be contiguous.
* 	Return the largest sum.
* Assumption:
* 	The subarray should contain at least one number
* Example:
* 	For given[1, 3, -1, 2, -1, 2], the two subarrays are[1, 3]and[2, -1, 2]or[1, 3, -1, 2]and[2], 
* 	they both have the largest sum7.
*/
public class MaximumSubArrayII {

	// Method 1: Dynamic Programming, Kadane's Algorithm
	// Time: O(n) and Extra Space O(n);
	public int maxTwoSubArrays(int[] array) {
		if(array == null || array.length == 0) return 0;
		int n = array.length;
		// left[i] represent the maxSubArraySum between [0...i]
		int[] left = new int[array.length];
		// right[i] represent the maxSubArraySum between[i...n-1];
		int[] right = new int[array.length];
		// base case:
		left[0] = array[0];
		right[n-1] = array[n-1];
		// you can generate another dp array for finding prev, I optimize it to O(1) space comsumption
		int prev = left[0], max = left[0];
		for(int i = 1; i < n; i++) {
			prev = Math.max(prev + array[i], array[i]);
			max = Math.max(max, prev);
			left[i] = max;
		}
		// did the same thing as left array have done
		for(int i = n - 2; i >= 0; i--) {
			prev = Math.max(prev + array[i], array[i]);
			max = Math.max(max, prev);
			right[i] = max;
		}
		// Find teh max sum of left and right subarrays
		int res = Integer.MIN_VALUE;
		for(int i = 0; i < n - 1; i++) {
			res = Math.max(left[i] + right[i + 1], res);
		}
		return res;
	}
	// Method 2: Same logic by implementing prefix Sum
	public int maxTwoSubArraysSumII(int[] array) {
		if(array == null || array.length == 0) return 0;
		int n = array.length;
		// left[i] represent the maxSubArraySum between [0...i]
		int[] left = new int[array.length];
		// right[i] represent the maxSubArraySum between[i...n-1];
		int[] right = new int[array.length];
		// base case:
		left[0] = array[0];
		right[n-1] = array[n-1];
		int prefixSum = 0;
		int minPrevfixSum = 0;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			prefixSum += array[i];
			max = Math.max(max, prefixSum - minPrevfixSum);
			minPrevfixSum = Math.min(minPrevfixSum, prefixSum);
			left[i] = max;
		}
		// reset the variable
		prefixSum = 0;
		minPrevfixSum = 0;
		max = Integer.MIN_VALUE;
		for(int i = n - 1; i >= 0; i--) {
			prefixSum += array[i];
			max = Math.max(max, prefixSum - minPrevfixSum);
			minPrevfixSum = Math.min(minPrevfixSum, prefixSum);
			right[i] = max;
		}
		int res = Integer.MIN_VALUE;
		for(int i = 0; i < n - 1; i++) {
			res = Math.max(res, left[i] + right[i+1]);
		}
		return res;
	}
}
