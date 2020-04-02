/**
 * 
 */
package williamsNotebook.easy.array;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月1日 下午9:56:10
* Description:
* 	Given an array of n positive integers and a positive integers, find the minimal length of a contiguous sub array of which the sum ≥s. If there isn't one, return 0 instead
* 	Input:
* 		s = 7, nums = [2,3,1,2,4,3]
* 	Output: 2
* 	Explanation: the sub array [4,3]  has the minimal length under the problem constraint.  
*/

public class MinimumSizeSubArraySum {

	// Two Pointer to simulate slide window
	// Time: O(n) and Space:O(1)
	public int minSubArrayLen(int target, int[] nums) {
		int sum = 0, i = 0, j = 0, win = Integer.MAX_VALUE;
		while(j < nums.length) {
			sum+= nums[j]; 
			while(sum >= target) {
				win = Math.min(win, j - i + 1);
				sum -= nums[i];
				i++;
			}
			j++;
		}
		return win == Integer.MAX_VALUE ? 0 : win;
	}
	
	// Method 2: Binary Search with prefix Sum
	public int minSubArrayLenII(int s, int[] nums) {
		int[] prefixSum = new int[nums.length + 1];
		for(int i = 1; i < prefixSum.length; i++) prefixSum[i] = prefixSum[i-1] + prefixSum[i];
		int minLen = Integer.MAX_VALUE;
		for(int i = 0; i < prefixSum.length; i++) {
			// small trick by using s = prefix[i+k] - prefix[i] = sum[i...k]
			int end = binarySearch(i + 1, prefixSum.length - 1, prefixSum[i] + s, prefixSum);
			if(end == prefixSum.length) break;
			if(end - i < minLen) minLen = end - i;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}
	private int binarySearch(int left, int right, int target, int[] array) {
		while(left <= right) {
			int mid = left + (right - left) / 2;
			if(array[mid] >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}
	public static void main(String[] args) {
		
	}
}
