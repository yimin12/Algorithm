  
package williamsNotebook.easy.slidingwindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
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
	
	// Monotonic Queue Question
	
	// Method 1: Storing the Value in Deque 
	public void inQueue(Deque<Integer> deque, int num) {
		while(!deque.isEmpty() && deque.peekLast() < num) {
			deque.pollLast();
		}
		deque.offerLast(num);
	}
	public void outQueue(Deque<Integer> deque, int num) {
		if(deque.peekFirst() == num) {
			deque.pollFirst();
		}
	}
	// Use the inQueue and outQueue for the implementation
	public ArrayList<Integer> maxSlidingWindow(int[] nums, int k){
		ArrayList<Integer> res = new ArrayList<Integer>();
		Deque<Integer> deque = new ArrayDeque<Integer>();
		if(nums == null || nums.length == 0) {
			return res;
		}
		for(int i = 0; i < nums.length; i++) {
			if(i < k - 1) {
				inQueue(deque, nums[i]);
			} else {
				inQueue(deque, nums[i]);
				res.add(deque.peekFirst());
				outQueue(deque, nums[i-k+1]);
			}
		}
		return res;
	}
	
	// Method 2: Storing the index in Monotonic Queue
	public int[] maxSlidingWindowII(int[] nums, int k) {
		if(nums == null || nums.length == 0 || k > nums.length) {
			return new int[0];
		};
		int n = nums.length;
		if(n == 1 || k == 1) {
			return nums;
		}
		int[] res = new int[n-k+1];
		Deque<Integer> mq = new LinkedList<Integer>();
		for(int i = 0; i < n; i++) {
			// remove index out of sliding window
			if(!mq.isEmpty() && mq.peekFirst() < i - k + 1) {
				mq.pollFirst();
			}
			while(!mq.isEmpty() && nums[i] >= nums[mq.peekLast()]) {
				mq.pollLast();
			}
			// all the queue is corresponding to index rather than the value
			mq.offerLast(i);
			System.out.println(mq.toString());
			if(i >= k - 1) {
				res[i-k+1] = nums[mq.peekFirst()];
			}
		}
		return res;
	}
	
	// Method 3: Use Array to implement the greedy solution
	public int[] maxSlidingWindowIII(int[] nums, int k) {
		if(nums == null || nums.length == 0) {
			return new int[0];
		};
		int n = nums.length;
		if(n == 1 || k == 1) {
			return nums;
		}
		int[] max_window = new int[n - k + 1];
		int max_index = -1;
		int index = 0;
		for(int i = 0; i < n - k + 1; i++) {
			// case one 1: max_index represent the max value between max_index - k + 1 and max_index of the nums
			// Once you finish traversing the previous k elements, you should traverse next k elements
			if(i > max_index) {
				max_index = i;
				for(int m = i; m < i + k; m++) {
					if(nums[m] >= nums[max_index]) {
						max_index = m;
					}
				}
			} else {
				if(nums[i + k - 1] > nums[max_index]) {
					max_index = i + k - 1;
				}
			}
			max_window[index++] = nums[max_index];
		}
		return max_window;
	}
	
	// Official Answer
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
		
		int[] maxSlidingWindowII = solution.maxSlidingWindowIII(new int[] {1, 2, 3, 2, 4, 2, 1}, 3);
		System.out.println(Arrays.toString(maxSlidingWindowII));
		//System.out.println(maxWindows.toString());
	}
	
}
