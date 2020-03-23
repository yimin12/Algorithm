/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月22日 上午12:19:19
* Description: leetCode 689
* 	In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
* 	Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
* 	Return the result as a list of indices representing the starting position of each interval (0-indexed). 
* 	If there are multiple answers, return the lexicographically smallest one.
* Example:
* 	Input: [1,2,1,2,6,7,5,1], 2
* 	Output: [0, 3, 5]
* 	Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
* 		We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
*/

public class MaximumSubarraySumIII {
	
	// Key Insight: there are total three intervals, so lets assume we have left interval, middle interval and right interval
	// each interval should contains exact k number of size. So, if left interval is in the range of [0,i-1], and the middle 
	// interval is [i,i+k-1] and the right interval is from subrange [i+k,n-1]
	// We need to maintain 3 dp array:
	// posLeft[i] represent the index of the left interval which is in the range of [0,i];
	// posRight[i] represent the index of the right interval which in the range of [i,n-1]
	public int[] maxSumOfThreeSubarray(int[] nums, int k) {
		if(nums == null || nums.length < k*3) return new int[0];
		int n = nums.length, maxSum = 0;
		int[] prefixSum = new int[n+1], posLeft = new int[n], posRight = new int[n], res = new int[3];
		for(int i = 0; i < n; i++) {
			prefixSum[i + 1] = prefixSum[i] + nums[i];
		}
		// fill the posLeft array
		for(int i = k, sum = prefixSum[k] - prefixSum[0]; i < n; i++) {
			// if the next candidate is larger, updated
			if(prefixSum[i+1] - prefixSum[i+1-k] > sum) {
				posLeft[i] = i + 1 - k;
				sum = prefixSum[i+1] - prefixSum[i+1-k];
			} else {
				// else , keep the original index
				posLeft[i] = posLeft[i-1];
			}
		}
		posRight[n-k] = n - k;
		for(int i = n - k - 1, sum = prefixSum[n] - prefixSum[n-k]; i >= 0; i--) {
			if(prefixSum[i+k] - prefixSum[i] > sum) {
				posRight[i] = i;
				sum = prefixSum[i+k] - prefixSum[i];
			} else {
				posRight[i] = posRight[i+1];
			}
		}
		// validate all possible middle interval
		for(int i = k; i <= n - 2*k; i++) {
			int left = posLeft[i-1], right = posRight[i+k];
			// adding these things up
			int sum = (prefixSum[i+k] - prefixSum[i]) + (prefixSum[left+k] - prefixSum[left]) + (prefixSum[right+k] - prefixSum[right]);
			if(sum > maxSum) {
				maxSum = sum;
				res[0] = left; res[1] = i; res[2] = right;
			}
		}
		return res;
	}
}
