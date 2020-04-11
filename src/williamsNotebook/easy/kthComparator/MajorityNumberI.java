/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午2:49:31
* Description:
* 	Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.
* Example:
* 	Given [1, 1, 1, 1, 2, 2, 2], return 1
* 	Solve it O(n) time and O(1) extra space
*/
public class MajorityNumberI {

	// Method 1: Use HashMap
	// Time: O(n) Space: O(n)
	public int majorityNumber(ArrayList<Integer> nums) {
		if(nums == null || nums.size() == 0) {
			return -1;
		}
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int listSize = nums.size();
		int halfSize = listSize>>2;
		int result = -1;
		for(int i = 0; i < listSize; i++) {
			int num = nums.get(i);
			map.put(num, map.getOrDefault(num, 0) + 1);
			if(map.get(num) > halfSize) {
				result = num;
			}
		}
		return result;
	}
	
	// The Boyer-Moore Vote Algorithm solves the majority vote problem in linear time O(n) and logarithmic space O(log n)
	// In this problem, Time: O(n) and Space: O(1)
	public int majorityNumberBoyerMoore(int[] nums) {
		int n = nums.length;
		int candidate = nums[0], counter = 0;
		for(int i : nums) {
			if(counter == 0) {
				candidate = i;
				counter = 1;
			} else if (i == candidate) {
				counter++;
			} else {
				counter--;
			}
		}
		counter = 0;
		for(int i : nums) {
			if(i == candidate) counter++;
		}
		if(counter < (n+1)/2) return -1;
		return candidate;
	}
	
	// Follow Up : Given an array of integers, the majority number is the number that occurs more than 1/3 of the size of the array.
	// Example : Given [1, 2, 1, 2, 1, 3, 3], return 1.
	public int majorityNumberII(ArrayList<Integer> nums) {
		if(nums == null || nums.size() == 0) {
			return -1;
		}
		int count1 = 0, count2 = 0;
		int candidate1 = 0, candidate2 = 0;
		for(int num:nums) {
			if(num == candidate1) {
				count1++;
			} else if(num == candidate2) {
				count2++;
			} else if(count1 == 0) {
				candidate1 = num;
				count1 = 1;
			} else if(count2 == 0) {
				candidate2 = num;
				count2 = 1;
			} else {
				count1--;
				count2--;
			}
		}
		count1 = count2 = 0;
		for (int i = 0; i < nums.size(); i++) {
			if(nums.get(i) == candidate1) {
				count1++;
			} else if(nums.get(i) == candidate2) {
				count2++;
			}
		}
		if(count1 > count2) return count1 > nums.size()/3 ? count1:-1;
		else return count2 > nums.size()/3 ? count2:-1;
	}
}
