
package williamsNotebook.easy.array;
/**
 * @author yimin Huang
 *
 *	Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *	Find the minimum element.
 *	You may assume no duplicate exists in the array.
 *
 * Algorithm Class
 */
public class RotatedSortedArray {

	// Follow Up1: Find Minimum in Rotated Sorted Array I: no duplicates
	// Time ~ O(logN), Space ~ O(1)
//	注意是 sorted array，所以：
//	1. 如果 num[lo] 不大于 num[hi]（循环附加条件），即 array 没有被 rotate，可停止循环，返回 num[lo] 为 min。
//	2. 剔除了 unrotated array 后，有两种情况：
//	num[mid] > num[lo], num[mid] > num[hi]：mid 和 lo 在一侧，则移动 lo；
//	num[mid] < num[lo], num[mid] < num[hi]：mid 和 hi 在一侧，则移动 hi。
	public int findMin(int[] num) {
		// Assumption: ....
		if(num == null) return -1;
		int left = 0, right = num.length - 1;
		while(left < right && num[left] > num[right]) {
			int mid = left + (right - left)/2;
			if(num[mid] > num[right]) {
				left = mid + 1;
			} else {
				right = mid; // right = mid - 1 xxxxxxxxx, you might exclude the answer
			}
		}
		return num[left];
	}
	
	// Follow Up2: Find Minimum in Rotated Sorted Array II: contains duplicates
	// Time ~ O(logN), worst case ~ O(N), Space ~ O(1), worse case happen when all the element is duplicate
//	1. 如果 num[lo] 小于 num[hi]（循环附加条件），可停止循环，num[lo] 即为 min。
//	2. 若 num[lo] >= num[hi]，当没有 duplicates 只有两种情况：
//	num[mid] > num[lo], num[mid] > num[hi]：mid 和 lo 在一侧，则移动 lo；
//	num[mid] < num[lo], num[mid] < num[hi]：mid 和 hi 在一侧，则移动 hi；
//	当存在 duplicates 时会增加另一种情况：
//	不论 mid 在哪一侧：num[mid] == num[lo] or num[mid] == num[hi]，则将 hi 后移一位（也可将 lo 前移一位）。
	public int findMinDup(int[] num) {
		// Assumption: ....
		if(num == null) return -1;
		int left = 0, right = num.length - 1;
		while(left < right && num[left] >= num[right]) {
			int mid = left + (right - left)/2;
			if(num[mid] > num[right]) left = mid + 1;
			else if(num[mid] < num[right]) right = mid;
			// when you encounter duplicate , you can right--, because you want to find the smallest one
			else right--; 
		}
		return num[left];
	}
	
	// Follow Up 3: Search in Rotated Sorted Array I: no duplicates
	// You are given a target value to search. If found in the array return its index, otherwise return -1.
	// Binary Search: Time ~ O(logN), Space ~ O(1)
	public int searchTarget(int[] nums, int target) {
		int left = 0, right = nums.length;
		while(left < right) {
			int mid = left + (right - left)/2;
			if(nums[mid] == target) return mid;
			// should find the sorted part of the rotated array
			// Case 1. the right part is sorted
			if(nums[mid] < nums[right]) {
				if(nums[mid] < target && nums[right] >= target) {
					left = mid + 1;
				} else {
					right = mid;
				}
			} else {
				// case 2. the left part is sorted
				if(nums[left] <= target && target < nums[mid]) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}
		}
		if(nums[left] == target) return left;
		return -1;
	}
	
	// Follow Up 4:Search in Rotated Sorted Array II: contains duplicates
//	Binary Search: Time ~ O(logN) worst case ~ O(N), Space ~ O(1)
//	Slight modifications:
//	除上述的两种情况：
//	num[mid] < num[hi]，若 target 在 num[mid .. hi] 中，则移动 lo，否则移动 hi；
//	num[mid] > num[hi]，若 target 在 num[lo .. mid] 中，则移动 hi，否则移动 lo；
//	再增加另一种情况：
//	num[mid] == num[hi]，将 hi 后移一位（不能将 hi = mid，因为会漏掉 mid 到 hi 中大的元素，比如 [1, 1, 3, 1], target = 3）
	public boolean searchDup(int[] nums, int target) {
		int left = 0, right = nums.length - 1;
		while(left < right) {
			int mid = left + (right - left)/2;
			if(nums[mid] == target) return true;
			if(nums[mid] < nums[right]) {
				if(nums[mid] < target && target <= nums[right]) {
					left = mid + 1;
				} else {
					
					right = mid;
				}
			} else if(nums[mid] > nums[right]){
				if(nums[mid] > target && target >= nums[left]) {
					right = mid;
				} else {
					left = mid + 1;
				} 
			} else { // nums[mid] == nums[right]
				right--;
			}
		}
		if(nums[left] == target) return true;
		return false;
	}
	
	// Follow Up 5: Right Shift by N characters, "abc", 4 -> "cab"
	// Time: O(n) Space: O(n)
	public String rightShift(String s, int k) {
		if(s == null || s.length() < 1) return s;
		char[] array = s.toCharArray();
		k %= array.length;
		reverse(array, array.length - k, array.length - 1);
		reverse(array, 0, array.length - k - 1);
		reverse(array, 0, array.length);
		return new String(array);
	}
	private void reverse(char[] array, int left, int right) {
		while(left < right) {
			char temp = array[left];
			array[left++] = array[right];
			array[right--] = temp;
		}
		
	}
}
