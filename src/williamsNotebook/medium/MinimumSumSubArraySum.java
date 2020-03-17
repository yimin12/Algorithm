/**
 * 
 */
package williamsNotebook.medium;

/**
 * @author yimin Huang
 *
 *	Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
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
	
	
	public static void main(String[] args) {
		MinimumSumSubArraySum solution = new MinimumSumSubArraySum();
		int[] array = new int[] {20, 30, 10, 20, 40, 30};
		int minSubArrayLen = solution.minSubArrayLenPrefixSum(70, array);
		System.out.println(minSubArrayLen);
	}
}
