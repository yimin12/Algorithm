/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.ArrayList;
import java.util.List;

import williamsNotebook.easy.tree.laioffer.isBST;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月9日 下午3:21:17
* Description:
* 	Given an circular integer array (the next element of the last element is the first element), find a continuous subarray in it, where 
* 	the sum of numbers is the biggest. Your code should return the index of the first number and the index of the last number.
* 	If duplicate answers exist, return any of them.
* Example:	
* 	Give [3, 1, -100, -3, 4], return [4,1].
*/

public class MaximumSubArraySumV {

	// Two case possible:
//	|————————---|  max  |———————————|  Case 1
//            start     end
//
//	|     max   |-------|    max    |	Case 2
//	           end     start
	// For case 1: normal way
	// For case 2: can convert to the problem of minimum subarray sum
	// Time: O(n) Space: O(n)
	public List<Integer> maxSubArraySum(int[] nums){
		if(nums == null || nums.length == 0) return new ArrayList<Integer>();
		List<Integer> case1 = new ArrayList<Integer>(), case2 = new ArrayList<Integer>();
		int max = max(nums, case1);
		int min = min(nums, case2);
		int sum = 0;
		for(int i = 0; i < nums.length; i++) {
			sum += nums[i];
		}
		if(max > sum - min) return case1;
		else return case2;
	}
	private int max(int[] nums, List<Integer> list) {
		int sum = 0, curLeft = 0, globalMax = Integer.MIN_VALUE;
		int left = 0, right = 0;
		for(int i = 0; i < nums.length; i++) {
			if(sum <= 0) {
				curLeft = i;
				sum = nums[i];
				if(sum > globalMax) {
					globalMax = sum;
					left = curLeft;
					right = i;
				}
			} else {
				sum += nums[i];
				if(sum > globalMax) {
					globalMax = sum;
					left = curLeft;
					right = i;
				}
			}
		}
		list.add(left);
		list.add(right);
		return sum;
	}
	private int min(int[] nums, List<Integer> min) {
		int sum = 0, curLeft = 0, globalMin = Integer.MAX_VALUE;
		int left = 0, right = 0;
		for(int i = 0; i < nums.length; i++) {
			if(sum >= 0) {
				curLeft = i;
				sum = nums[i];
				if(sum < globalMin) {
					globalMin = sum;
					left = curLeft;
					right = i;
				}
			} else {
				sum += nums[i];
				if(sum < globalMin) {
					globalMin = sum;
					left = curLeft;
					right = i;
				}
			}		
		}
		min.add((left-1)%nums.length);
		min.add((right+1)%nums.length);
		return sum;
	}
	public static void main(String[] args) {
		MaximumSubArraySumV solution = new MaximumSubArraySumV();
		int[] array = {3, 1, -100, -3, 4};
		List<Integer> maxSubArraySum = solution.maxSubArraySum(array);
		System.out.println(maxSubArraySum.toString());
	}
}
