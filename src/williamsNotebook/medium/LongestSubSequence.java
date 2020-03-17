/**
 * 
 */
package williamsNotebook.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yimin Huang
 *	
 *	Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *	For example,
 *		Given [100, 4, 200, 1, 3, 2],
 *		The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4 and Your algorithm should run in O(n) complexity.
 *
 * Algorithm Class
 */
public class LongestSubSequence {

	
	// Follow Up 1: Given an array A[0]...A[n-1] of integers, find out the length of the longest ascending subsequence
	// Method 1: dynamic programming, dp[i] represent the longest ascending sequence within [0...i-1];
	// Time: O(n^2) and O(n) Extra Space
	public int longest(int[] array) {
		if(array == null || array.length == 0) return 0;
		int[] longest = new int[array.length];
		int result = 1;
		for(int i = 0; i < array.length; i++) {
			longest[i] = 1;
			for(int j = 0; j < i; j++) {
				if(array[j] < array[i]) longest[i] = Math.max(longest[j] + 1, longest[i]);
			}
			result = Math.max(longest[i], result);
		}
		return result;
	}
	// Method 2: using binary search and greedy solution
	public int longestII(int[] array) {
		if(array == null || array.length == 0) return 0;
		int[] tbl = new int[array.length + 1];
		int result = 1;
		tbl[1] = array[0];
		for(int i = 1; i < array.length; i++) {
			int index = find(tbl, 1, result, array[i]);
			if(index == result) {
				tbl[++result] = array[i];
			} else {
				tbl[index+1] = array[i];
			}
		}
		return result;
	}
	private int find(int[] tbl, int left, int right, int target) {
		while(left <= right) {
			int mid = left + (right - left)/2;
			if(tbl[mid] >= target) {
				right = mid + 1;
			} else {
				left = mid - 1;
			}
		}
		return right;
	}
	
	// Follow Up 2: Longest Consecutive Sequence
	// Time ~ O(N), Space ~ O(N)
	public int longestConsectiveSequence(int[] array) {
		if(array == null || array.length == 0) return 0;
		Set<Integer> set =  new HashSet<Integer>();
		int max = 0;
		for(int i : array) set.add(i);
		for(int i : array) {
			int len = 1;
			for(int j = i + 1; set.contains(j); j++) {
				len++;
				set.remove(j);
			}
			for(int j = i - 1;set.contains(j); j--) {
				len++;
				set.remove(j);
			}
			// the found value should be removed, or it will cause O(n^2) for repeatly searching
			set.remove(i);
			max = Math.max(max, len);
		}
		return max;	
	}
}
