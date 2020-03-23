/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.ArrayDeque;
import java.util.Deque;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月23日 下午4:22:33
* Description:
* 	Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
* 	MovingAverage m = new MovingAverage(3);
	m.next(1) = 1
	m.next(10) = (1 + 10) / 2
	m.next(3) = (1 + 10 + 3) / 3
	m.next(5) = (10 + 3 + 5) / 3
*/

// Method 1: Using ArrayDeque
public class MoveAverageFromDataStream {

	Deque<Integer> q; // simulating a queue
	int sum = 0;
	int size = 0;
	public MoveAverageFromDataStream(int size) {
		this.size = size;
		sum = 0;
		q = new ArrayDeque<Integer>();
	}
	public double next(int val) {
		if(size == q.size()) {
			sum -= q.pollFirst();
		}
		sum += val;
		q.offerLast(val);
		return 1.0 * sum / q.size();
	}
}
// Method 2: Using Circular Array
class MovingAverage{
	private int[] window;
	private int count, insertIndex, capacity;
	private long sum;
	public MovingAverage(int size) {
		this.window = new int[size];
		this.insertIndex = 0;
		this.capacity = size;
		this.count = 0;
		this.sum = 0;
	}
	public double next(int val) {
		if(count < capacity) {
			count++;
		} else {
			sum -= window[insertIndex];
		}
		window[insertIndex] = val;
		sum += val;
		insertIndex = (insertIndex + 1) % capacity;
		int size = Math.min(count, capacity);
		return 1.0 * sum/size;
	}
}
