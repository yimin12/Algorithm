/**
 * 
 */
package williamsNotebook.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yimin Huang
 *
 *	Given a non-negative integer array representing the heights of a list of adjacent bars. Suppose each 
 *	bar has a width of 1. Find the largest rectangular area that can be formed in the histogram.
 *	
 *	Examples:
 *		{ 2, 1, 3, 3, 4 }, the largest rectangle area is 3 * 3 = 9(starting from index 2 and ending at index 4)
 *	
 * Algorithm Class
 */
public class MaxAreaHistogram {

	// Assumption: array is not null and array.length >= 1, all the value in the array are non-negative
	// Greedy Solution + Dynamic Program : Time: O(n), Space: O(n) in worst case
	public int maxArea(int[] array) {
		// record the updated max
		int result = 0;
		// simulate and maintain a stack ( the height is monotone increasing)
		Deque<Integer> stack = new LinkedList<Integer>();
		for(int i = 0; i <= array.length; i++) {
			int cur = i == array.length ? 0 : array[i];
			// peek the value just added
			while(!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
				int height = array[stack.pollFirst()];
				int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
				result = Math.max(result, height * (i- left));
			}
			stack.offerFirst(i);
		}
		return result;
	}
	public static void main(String[] args) {
		MaxAreaHistogram solution = new MaxAreaHistogram();
		int maxArea = solution.maxArea(new int[]{ 2, 1, 3, 3, 4 });
		System.out.println(maxArea);
	}
	
}
