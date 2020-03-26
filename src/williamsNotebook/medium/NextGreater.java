/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/**
 * @author yimin Huang
 *	
 *	You are given two arrays nums1 and nums2 where nums1's elements are subset of nums2. Find all the next greater numbers for nums1's element in the 
 *	corresponding places of nums2
 *	The next Greater Number of a number x in nums1 is the first greater number to its right in nums2, if it does not exist. Output -1 for this number
 *	Example:
 *		Input: nums1 = [4,1,2], nums2 = [1,0,3,4,2].
 *		Output: [-1,3,-1]
 *	Input: nums1 = [2,4], nums2 = [1,2,3,4].
 *		Output: [3,-1]
 * Algorithm Class
 */
public class NextGreater {

	// Situation 1: Without duplicate
	// Solution 1. Naive solution. Time:(m*n) Space: extra space for res O(m)
	public int[] nextGreaterWithoutDup(int[] one, int[] two) {
		// Assume one is subset of two
		if(two == null || two.length == 0) throw new IllegalArgumentException("Input is invalid");
		int[] res = new int[one.length];
		for(int i = 0; i < one.length; i++) {
			// return the first greater value 
			boolean found = false;
			int j = 0;
			for(;j < two.length; j++) {
				if(found && two[j] > one[i]) {
					res[i] = two[j];
					break;
				}
				if(two[j] == one[i]) {
					found = true;
				}
			}
			if(j == two.length) {
				res[i] = -1;
			}
		}
		return res;
	}
	// Solution 2: Using HashMap 
	// Time:(m*n) Space: extra space for res O(m + n)
	public int[] nextGreaterHashMap(int[] one, int[] two) {
		if(two == null || two.length == 0) throw new IllegalArgumentException("Input is invalid");
		int[] res = new int[one.length];
		// key = number, value = index
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < two.length; i++) {
			map.put(two[i], i);
		}
		for(int i = 0; i < one.length; i++) {
			int j = map.get(one[i]) + 1;
			for(;j < two.length; j++) {
				if(one[i] < two[j]) {
					res[i] = two[j];
					break;
				}
			}
			if(j == two.length) {
				res[i] = -1;
			}
		}
		return res;
	}
	// Solution 3: monotonically stack, 
	// Time:(n+m) Space: extra space for res O(m+3*n)
	public int[] nextGreaterStack(int[] one, int[] two) {
		if(two == null || two.length == 0) throw new IllegalArgumentException("Input is invalid");
		int[] res = new int[one.length];
		Deque<Integer> stack = new ArrayDeque<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = two.length - 1; i >= 0; i--) {
			// because it do not contain duplicate, stack do not have to store the index information
			while(!stack.isEmpty() && two[i] > stack.peekLast()) {
				stack.pollLast();
			}
			if(stack.isEmpty()) {
				map.put(two[i], -1);
			} else {
				map.put(two[i],stack.peekLast());
			}
			stack.offerLast(two[i]);
		}
		for(int i = 0; i < one.length; i++) {
			res[i] = map.get(one[i]);
		}
		return res;
	}
	
	// Follow Up 2: With duplicate and it give a circular array (the next element of the last element is the first element of array)
	// Example: Input:[1,2,1] Output:[2,-1,2], Idea: Expand the given array with itself. Expanded Input: [1,2,1,1,2,1]
	// Using Brute Force Time:(n^2) Space: extra space for res O(n)
	public int[] nextGreaterElements(int[] array) {
		int[] res = new int[array.length];
		for(int i = 0; i < array.length; i++) {
			// let the default value is -1
			res[i] = -1;
			for(int j = 1; j < array.length; j++) {
				if(array[(i+j)%array.length] > array[i]) {
					res[i] = array[(i+j)%array.length];
					break;
				}
			}
		}
		return res;
	}
	// Solution 2: monotonically stack, use stack to keep the information of index. It can handle the problem of duplicate
	// Using Stack Force Time:(n) Space: extra space for res O(2*n)
	public int[] nextGreaterElementStack(int[] nums) {
		int[] res = new int[nums.length];
		Deque<Integer> stack = new LinkedList<Integer>();
		for(int i = 2 * nums.length - 1; i >= 0; i--) {
			while(!stack.isEmpty() && nums[i%nums.length] >= nums[stack.peekLast()]) {
				stack.pollLast();
			}
			res[i%nums.length] = stack.isEmpty() ? -1:nums[stack.peekLast()];
			stack.offerLast(i%nums.length);
		}
		return res;
	}
	// Solution 3: the monotonically stack can always replaced by array
	// Using Stack Force Time:(n) Space:  O(n) extra space O(1), optimize way to pruning.
	public int[] nextGreaterElementArray(int[] nums) {
		int[] res = new int[nums.length];
		for(int i = 2 * nums.length - 2; i >= 0; i--) {
			res[i%nums.length] = -1;
			for(int j = i + 1; j < 2 * nums.length - 1;) {
				if(nums[j%nums.length] > nums[i%nums.length]) {
					res[i%nums.length] = j % nums.length;
					break;
				} else if(res[j%nums.length] == -1){
					break;
				} else {
					j = res[j%nums.length];
				}
			}
		}
		for(int i = 0; i !=nums.length; i++) {
			if(res[i] != -1) {
				res[i] = nums[res[i]];
			}
		}
		return res;
	}
	
	// Follow Up 3: Given a positive 32-bit integer n, you need to find the smallest 32-bit integer 
	// which has exactly the same digits existing in the integernand is greater in value than n. If no 
	// such positive 32-bit integer exists, you need to return -1. logic is similar with next permutation
	public int nextGreaterElement(int n) {
		char[] number = (n + "").toCharArray();
		int i, j;
		// step 1: search the first a[i] that is greater than a[i-1] from right to left
		for(i = number.length-1; i>=0; i--) {
			if(number[i-1] < number[i]) break;
		}
		// if there is no such situation, it is the largest one with same digits
		if(i==0) {
			return -1;
		}
		// step 2: find the value is exact smallest larger than a[i-1], we can not use binary search cause it is not sorted
		int x = number[i-1], smallest = i;
		for(j = i + 1; j < number.length; j++) {
			if(number[j] > x && number[j] <= number[smallest]) {
				smallest = j;
			}
		}
		// step 3: swap the next larger value with a[i-1]
		char temp = number[i-1];
		number[i-1] = number[smallest];
		number[smallest] = temp;
		// step 4: Sort the digits after (i-1) in ascending order
		Arrays.sort(number, i, number.length);
		long val = Long.parseLong(new String(number));
		return (val <= Integer.MAX_VALUE ? (int)val : -1);
	}
	public static void main(String[] args) {
		NextGreater solution = new NextGreater();
		int[] one = new int[] {4,1,2};
		int[] two = new int[] {1,0,3,4,2};
		int[] three = new int[] {4,4,1};
		int[] four = new int[] {1,0,3,4,2,4,2,1,0};

		int[] nextGreaterWithoutDup = solution.nextGreaterWithoutDup(one, two);
		int[] nextGreaterHashMap = solution.nextGreaterHashMap(one, two);
		int[] nextGreaterStack = solution.nextGreaterStack(three, four);
		System.out.println(Arrays.toString(nextGreaterHashMap));
		System.out.println(Arrays.toString(nextGreaterWithoutDup));
		System.out.println(Arrays.toString(nextGreaterStack));
		
		int[] cOne = new int[] {1,2,4,3};
		int[] nextGreaterElements = solution.nextGreaterElementArray(cOne);
		System.out.println(Arrays.toString(nextGreaterElements));
	}
}
