/**
 * 
 */
package Common;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月25日 上午9:48:59
* Description:
* 	Given an integer array A and a sliding window of size K, find the maximum 
* 	value of each window as it slides from left to right.
* Assumption:
* 	The given array is not null and is not empty
* 	K >= 1, k <= A.length
* Examples:
* 	A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},
* 	and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
*/

public class MaxValuesSlidingWindows {
	public List<Integer> maxWindows(int[] array, int k){
		List<Integer> max = new ArrayList<Integer>();
//		use a descending deque to solving this problem, we store the index instead of the actual value in the deque
//		and we make sure:
//		1.the deque only contains index in the current sliding window
//		2.for any index, the previous index with smaller value is discarded from the deque
		Deque<Integer> deque = new LinkedList<Integer>();
		for(int i = 0; i < array.length; i++) {
//			in order just store currently max value only
			while(!deque.isEmpty() && array[deque.peekLast()] <= array[i]) {
				deque.pollLast();
			}
//			it is possible the head element is out of the current sliding window so we might need to discard it as well
			if(!deque.isEmpty() && deque.peekFirst() <= i - k) {
				deque.pollFirst();
			}
			deque.offerLast(i);
			if(i >= k-1) {
				max.add(array[deque.peekFirst()]);
			}
		}
		return max;
	}
}
