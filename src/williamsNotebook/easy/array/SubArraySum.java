/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月9日 下午1:06:17
* Description:
* 	Given an integer array, find a subarray where the sum of numbers is in a given interval. Your code should return the number of possible answers.
* 	(The element in the array should be positive)
* 	Example Given [1,2,3,4] and index's length interval = [1,3], return 4. The possible answers are:
* 	[0, 0] [0, 1] [1, 1] [2, 2]
*/

public class SubArraySum {

	// Use Prefix Sum and sliding window
	// Time:O(n) Space: O(n) 
	public int subArraySum(int[] nums, int[] interval, int target) {
		if(nums == null || nums.length == 0) return 0;
		int min = interval[0], max = interval[1];
		int[] prefixSum = new int[nums.length+1];
		for(int i = 1; i <= nums.length; i++) {
			prefixSum[i] = prefixSum[i-1] + nums[i-1];
		}
		int left = 0, right = min;
		int count = 0;
		while(right < prefixSum.length) {
			if(right - left < min) {
				right++;
			} else if(right - left > max) {
				left++;
			} else if(prefixSum[right] - prefixSum[left] == target) {
				count++;
				left++;
			} else if(prefixSum[right] - prefixSum[left] < target) {
				right++;
			} else {
				left++;
			}
		}
		return count;
	}
	// Follow Up 2: Given [1,2,3,4] and result interval = [1,3], return 4. The possible answers are:
	public int subArraySumII(int[] nums, int[] interval) {
		if(nums == null || nums.length == 0) return 0;
		int min = interval[0], max = interval[1];
		int[] prefixSum = new int[nums.length+1];
		for(int i = 1; i <= nums.length; i++) {
			prefixSum[i] = prefixSum[i-1] + nums[i-1];
		}
		int left = 0, right = 1;
		int count = 0;
		while(right < prefixSum.length) {
			if(left < right && prefixSum[right] - prefixSum[left] > max) {
				left++;
			} else if(prefixSum[right] - prefixSum[left] < min) {
				right++;
			} else {
				int smallestLarger = binarySearch(prefixSum, left, right, min);
				if(smallestLarger!=-1) count += smallestLarger -left + 1;
				else count++;
				right++;
			}
		}
		return count;
	}
	private int binarySearch(int[] prefix, int left, int right, int min) {
		int curLeft = left, curRight = right;
		while(curLeft < curRight-1) {
			int mid = curLeft + (curRight - curLeft)/2;
			if(prefix[right] - prefix[mid] > min) {
				curLeft = mid;
			} else {
				curRight = mid;
			}
		}
		if(prefix[right] - prefix[curRight] > min) return curRight;
		else if(prefix[right] - prefix[curLeft] > min) return curLeft;
		return -1;
	}
	public static void main(String[] args) {
		SubArraySum solution = new SubArraySum();
		int[] nums = {1,2,3,4}, interval = {1,5};
		int subArraySum = solution.subArraySumII(nums, interval);
		System.out.println(subArraySum);
	}
}
