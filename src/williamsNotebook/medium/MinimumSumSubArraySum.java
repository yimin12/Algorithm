/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import williamsNotebook.common.ToList;

/**
 * @author yimin Huang
 *
 *	Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum 鈮� s. If there isn't one, return 0 instead.
 *	For example, given the array [2,3,1,2,4,3] and s = 7,
 *	the subarray [4,3] has the minimal length under the problem constraint.
 *	If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

 *
 * Algorithm Class
 */
public class MinimumSumSubArraySum {
	
	// Two Pointers: Time ~ O(N), Space ~ O(1)
	// Insight: Similar with sliding window
	public int minSubArrayLen(int s, int[] nums) {
		int prev = 0, sum = 0, len = Integer.MAX_VALUE;
		for(int i = 0; i < nums.length; i++) {
			sum += nums[i];
			while(sum >= s) {
				// if(sum == s) len = Math.min(len, i - prev + 1); when specific require sum is equal than s
				len = Math.min(len, i - prev + 1);
				sum -= nums[prev++];
			}
		}
		return len == Integer.MAX_VALUE ? 0 : len;
	}
	
	// Binary Search: Time ~ O(NlogN), Space ~ O(N)
	// By Implementing Prefix Sum
	public int minSubArrayLenPrefixSum(int s, int[] nums) {
		int n = nums.length;
		int[] prefixSum = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			prefixSum[i] = prefixSum[i-1] + nums[i-1];
		}
		int len = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			if(prefixSum[i] + s > prefixSum[n]) break;
			int end = binarySearch(i + 1, n, prefixSum[i] + s, prefixSum);
			len = Math.min(len, end - i);
		}
		return len == Integer.MAX_VALUE	? 0 : len;
	}
	private int binarySearch(int lo, int hi, int key, int[] sum) {
	    while (lo < hi) {
	        int mid = (lo + hi) / 2;
	        if (sum[mid] < key)         lo = mid + 1;
	        else if (sum[mid] > key)    hi = mid;
	        else                        return mid;
	    }
	    return lo;
	}
	// Follow Up 2: What if there consist negative number
	// Implement it with TreeMap Time ~ O(NlogN), Space ~ O(N)
	
	
	
	// Follow Up 3: Find the min for the Minimum Subarray Sum, by using prefix sum
	// You need maintain the maximum Prefix when you iterate the whole array
	// Time: O(n) and Extra Space: O(1)
	public int minSubArray(List<Integer> nums) {
		if(nums == null || nums.size() == 0) {
			return 0;
		}
		int max = 0, min = Integer.MAX_VALUE;
		int prefixSum = 0;
		for(int i : nums) {
			prefixSum += i;
			min = Math.min(min, prefixSum - max);
			max = Math.max(max, prefixSum);
		}
		return min;
	}
	
	// Method 2: Common way by using dynamic program
	// Time: O(n) and Extra Space: O(n) can be optimized to O(1)
	public int minSubArrayI(List<Integer> array) {
		if(array == null || array.size() == 0) {
			return 0;
		}
		int[] dp = new int[array.size()];
		dp[0] = array.get(0);
		int minSum = dp[0];
		for(int i = 1; i < array.size(); i++) {
			dp[i] = Math.min(dp[i-1] + array.get(i), array.get(i));
			minSum = Math.min(minSum, dp[i]);
		}
		return minSum;
	}
	public static void main(String[] args) {
		MinimumSumSubArraySum solution = new MinimumSumSubArraySum();
		int[] array = new int[] {1, -1, -2, 1};
		List<Integer> list = ToList.toList(array);
		int minSubArrayLen = solution.minSubArrayI(list);
		System.out.println(minSubArrayLen);
	}
}
