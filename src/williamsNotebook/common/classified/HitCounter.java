/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.LinkedList;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 下午3:23:58
* Description:
* 	Design a hit counter which counts the number of hits received in the past 5 minutes.
* 	Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being 
* 	made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume 
* 	that the earliest timestamp starts at 1.
* 	It is possible that several hits arrive roughly at the same time.
* HitCounter counter = new HitCounter();
		// hit at timestamp 1.
		counter.hit(1);
		// hit at timestamp 2.
		counter.hit(2);
		// hit at timestamp 3.
		counter.hit(3);
		// get hits at timestamp 4, should return 3.
		counter.getHits(4);
		// hit at timestamp 300.
		counter.hit(300);
		// get hits at timestamp 300, should return 4.
		counter.getHits(300);
		// get hits at timestamp 301, should return 3.
		counter.getHits(301);
*/
// Window Buckets, fixed size window for times counting
public class HitCounter {

	int[] hits;
	int[] times;
	final int WINDOW_SIZE = 300;
	public HitCounter() {
		hits = new int[WINDOW_SIZE];
		times = new int[WINDOW_SIZE];
	}
	// Time: O(1) Space: O(constant)
	public void hit(int timestamp) {
		int index = timestamp % WINDOW_SIZE;
		if(times[index] != timestamp) {
			times[index] = timestamp;
			hits[index] = 1;
		} else {
			hits[index] += 1;
		}
	}
	// Time: O(constant) Space: O(1)
	public int getHits(int timestamp) {
		int count = 0;
		for(int i = 0; i < WINDOW_SIZE; i++) {
			if(timestamp - times[i] < 300) {
				count += hits[i];
			}
		}
		return count;
	}
}
class HitCounterII{
	Queue<Integer> queue;
	private final int FIVE_MINUTES = 300;
	public HitCounterII() {
		queue = new LinkedList<Integer>();
	}
	// Time Complexity: O(1)
	public void hit(int timestamp) {
		queue.offer(timestamp);
	}
	// Time Complexity: O(n), n is how many times of hits
	public int getHits(int timestamp) {
		while(!queue.isEmpty() && (timestamp - queue.peek() >= FIVE_MINUTES)) {
			queue.poll();
		}
		return queue.size();
	}
	
}