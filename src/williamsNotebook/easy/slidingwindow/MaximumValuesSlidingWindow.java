  
package williamsNotebook.easy.slidingwindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author yimin Huang
 *
 *	Given an integer array A and a sliding window of size K, find the maximum value of each window 
 *	as it slides from left to right.
 *
 *	Assumptions:
 *		The given array is not null and is not empty
 *		K >= 1, K <= A.length
 *	Examples:
 *		A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}}
 *		and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
 *
 * Algorithm Class
 */
public class MaximumValuesSlidingWindow {

	public List<Integer> maxWindows(int[] array, int k){
		List<Integer> max = new ArrayList<Integer>();
		// use ascending deque to solve this pro blem, we store the index rather than the actual value in deque.
		// 1. the deque only contains index in the current sliding window.
		// 2. for any index, the previous index with smaller value is discard from the deque
		Deque<Integer> deque = new ArrayDeque<Integer>();
		for(int i = 0; i < array.length; i++) {
			// step 1: push the largest value to the head of deque
			while(!deque.isEmpty() && array[i] >= array[deque.peekLast()]) {
				deque.pollLast();
			}
			// step 2: remove the first element when the boundary is out of limit
			if(!deque.isEmpty() && deque.peekFirst() <= i - k) {
				deque.pollFirst();
			}
			// step 3: offer the selected value
			deque.offerLast(i);
			// step 4: fill the sliding window
			if(i >= k - 1) {
				max.add(array[deque.peekFirst()]);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		MaximumValuesSlidingWindow solution = new MaximumValuesSlidingWindow();
		List<Integer> maxWindows = solution.maxWindows(new int[] {1, 2, 3, 2, 4, 2, 1}, 3);
		System.out.println(maxWindows.toString());
	}
}
