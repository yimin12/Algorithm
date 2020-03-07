/**
 * 
 */
package williamsNotebook.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author yimin Huang
 *
 *	Given an array of integers and integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute 
 *	difference between i and j is at most k
 * Algorithm Class
 */
public class ContainDuplicate {

	// Solution 1: naive solution
	// enumerate and try every possible pair
	// Time: O(n^2) Space: O(1)
	public boolean containsNearByDuplicate(int[] nums, int k) {
		if(nums == null || nums.length == 0 || k < 0) throw new IllegalArgumentException("invalid input");
		for(int i = 0; i < nums.length; i++) {
			
			for(int j = i+1; j < nums.length && j - i <= k; j++) {
				if(nums[i] == nums[j]) {
					return true;
				}
			}
		}
		return false;
	}
	// Solution 2: use HashMap
	// keep track of every unique number index, check if it appeared before and the difference current and previous index
	// Time : O(n) and Space: O(n)
	public boolean containsNearByDuplicateII(int[] nums, int k) {
		if(nums == null || nums.length == 0 || k < 0) throw new IllegalArgumentException("invalid input");
		// key = number, value = index
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < nums.length; i++) {
			if(map.containsKey(nums[i]) && i - map.get(nums[i]) <= k) {
				return true;
			} 
			map.put(nums[i], i);
		}
		return false;
	}
	// Solution 3: sliding window
	// To reduce the space complexity, keep sliding window with k length, using a set to check if any duplicate number in sliding window
	// Time: O(n) Space: O(k)
	public boolean containsNearBySlidingWindow(int[] nums, int k) {
		if(nums == null || nums.length == 0 || k < 0) throw new IllegalArgumentException("invalid input");
		Set<Integer> set = new HashSet<Integer>();
		// fill the sliding window to size k
		for(int i = 0; i < nums.length; i++) {
			if(!set.add(nums[i])) {
				// it has been already inserted in this part
				return true;
			}
			if(i >= k) {
				set.remove(nums[i-k]);
			}
		}
		return false;
	}
	
	// Follow Up 2: absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k
	// Input: nums[1,20,10,30,40] , t = 5, k = 3, Output = false;  Input: nums[1,20,5,30,40], t = 5, k = 3, Output: true
	// Solution 1: naive solution. Try every number compare with its next k numbers
	// Time: O(n*k) Space: O(1)
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if(nums == null || nums.length == 0 || k < 0 || t <= 0) {
			return false;
		}
		for(int i = 0; i < nums.length; i++) {
			for(int j = i + i; j < nums.length && j-i <= k; j++) {
				if(Math.abs(Long.valueOf(nums[j]) - Long.valueOf(nums[i])) <= t) {
					return true;
				}
			}
		}
		return false;
	}
	// Solution 2: TreeSet 
	// Record numbers in Binary Search Tree(TreeSet in Java), check if the diff between the new number came in its closest number in the BST is less than t
	// Time: O(n*logk) Space: O(k)
	public boolean containsNearByAlmostDuplicateTreeSet(int[] nums, int k, int t) {
		if(nums == null || nums.length == 0 || k < 0 || t <= 0) {
			return false;
		}
		// use treeset rather than hashset when you need the element sorted
		TreeSet<Integer> set = new TreeSet<Integer>();
		// compare the smallest number larger than target and largest number smaller than target
		for(int i = 0; i < nums.length; i++) {
			// find the smallest number larger than nums[i], O(logk)
			Integer ceil = set.ceiling(nums[i]);
			if(ceil != null && Long.valueOf(ceil) - Long.valueOf(nums[i]) <= t) {
				return true;
			}
			// find the largest number smaller than nums[i], O(logk)
			Integer floor = set.floor(nums[i]);
			if(floor != null && Long.valueOf(nums[i]) - Long.valueOf(floor) <= t) {
				return true;
			}
			if(i >= k) {
				set.remove(nums[i-k]);
			}
			set.add(nums[i]);
		}
		return false;
	}
	// Solution 3: Bucket 
	// put every number in a corresponding bucket(bucket_index = (number-min_num)/(t+1)). Return true if we see two numbers in the same bucket or 
	// the adjacent bucket has a number with diff was than t
	// Time: O(n), Space: O((max_number-min_number)/t) and can be optimized to O(k)
	public boolean containsAlmostAlmostDuplicateBucket(int[] nums, int k, int t) {
		// The thought is quite similar with hash function
		if(nums == null || nums.length == 0 || k < 0 || t <= 0) {
			return false;
		}
		int min = Integer.MAX_VALUE;
		for(int num : nums) {
			min = Math.min(num, min);
		}
		HashMap<Long, Integer> bucket = new HashMap<Long, Integer>();
		// permitted threshold
		long diff = Long.valueOf(t) + 1;
		// what if collision happen ? 
		for(int i = 0; i < nums.length; i++) {
			long index = (Long.valueOf(nums[i]) - Long.valueOf(min))/diff;
			// check the left adjacent bucket
			Integer left_bucket = bucket.get(index - 1);
			if(left_bucket != null && Math.abs(Long.valueOf(nums[i])- Long.valueOf(left_bucket))<diff) {
				return true;
			}
			// check the right adjacent bucket
			Integer right_bucket = bucket.get(index + 1);
			if(right_bucket != null && Math.abs(Long.valueOf(nums[i])- Long.valueOf(right_bucket))<diff) {
				return true;
			}
			// check the current bucket 
			Integer current_bucket = bucket.get(index);
			if(current_bucket != null && Math.abs(Long.valueOf(nums[i]) - Long.valueOf(current_bucket))<diff) {
				return true;
			}
			if(i >= k) {
				bucket.remove((Long.valueOf(nums[i-k]) - Long.valueOf(min))/diff);
			}
			bucket.put(index, nums[i]);
		}
		return false;
	}
	
	public static void main(String[] args) {
		ContainDuplicate solution = new ContainDuplicate();
		int[] array = new int[] {1,3,4,1,5,6};
		boolean containsNearByDuplicateII = solution.containsNearBySlidingWindow(array, 4);
		System.out.println(containsNearByDuplicateII);
		
		int[] array2 = new int[] {11,20,5,30,8};
		boolean containsAlmostAlmostDuplicateBucket = solution.containsAlmostAlmostDuplicateBucket(array2, 5, 3);
		System.out.println(containsAlmostAlmostDuplicateBucket);
	}
}
