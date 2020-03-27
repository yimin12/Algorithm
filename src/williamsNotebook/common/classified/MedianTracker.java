/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.Collections;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 下午2:53:59
* Description:
* 	Numbers keep coming, return the median of numbers at every time a new number added.
* 	What's the definition of Median?
* 	Median is the number that in the middle of a sorted array. If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. 
* 	For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.
* 	Example:
* 		For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].
* 		For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].
* 		For numbers coming list: [2, 20, 100], return [2, 2, 20].
* Another Definition :
* 	Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
* 	For example, [2,3,4], the median is 3, [2,3], the median is(2 + 3) / 2 = 2.5
* 	Design a data structure that supports the following two operations:
* 		void read(int num) - Add a integer number from the data stream to the data structure.
* 		double median() - Return the median of all elements so far.
*/
public class MedianTracker {
	private PriorityQueue<Integer> smallerHalf;
	private PriorityQueue<Integer> largerHalf;
	
	public MedianTracker() {
		largerHalf = new PriorityQueue<Integer>(); // minHeap for larger value to keep the middle candidate
		smallerHalf = new PriorityQueue<Integer>(Collections.reverseOrder()); // maxHeap for smaller value to keep the middle candidate
	}
	public void read(int value) {
		// decide which part the value belongs
		if(smallerHalf.isEmpty() || value <= smallerHalf.peek()) {
			smallerHalf.offer(value);
		} else {
			largerHalf.offer(value);
		}
		// there are two possible , odd or even numbers of value
		// if odd, the smallerHalf will have one more element than largeHalf
		if(smallerHalf.size() - largerHalf.size() > 1) {
			largerHalf.offer(smallerHalf.poll());
		} else if(largerHalf.size() > smallerHalf.size()) {
			smallerHalf.offer(largerHalf.poll());
		}
	}
	public Double median() {
		int size = size();
		if(size == 0) {
			return null;
		} else if(size % 2 == 1) {
			return (double)(smallerHalf.peek());
		} else {
			return (smallerHalf.peek() + largerHalf.peek()) / 2.0;
		}
	}
	private int size() {
		return smallerHalf.size() + largerHalf.size();
	}
}
