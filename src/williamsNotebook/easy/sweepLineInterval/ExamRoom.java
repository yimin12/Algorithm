/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午2:22:40
* Description:
* 	In an exam room, there areNseats in a single row, numbered0, 1, 2, ..., N-1.	
* 	When a student enters the room, they must sit in the seat that maximizes the distance to the closest person. If there 
* 	are multiple such seats, they sit in the seat with the lowest number. (Also, if no one is in the room, then the student 
* 	sits at seat number 0.)
* 	Return a classExamRoom(int N) that exposes two functions:ExamRoom.seat() returning an int representing what seat the student sat in, 
* 	and ExamRoom.leave(int p) representing that the student in seat number p now leaves the room. It is guaranteed that 
*   any calls toExamRoom.leave(p)have a student sitting in seat p.
* Example:
* 	Input: ["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
	Output: [null,0,9,4,2,null,5]
	Explanation:
	ExamRoom(10) -> null
	seat() -> 0, no one is in the room, then the student sits at seat number 0.
	seat() -> 9, the student sits at the last seat number 9.
	seat() -> 4, the student sits at the last seat number 4.
	seat() -> 2, the student sits at the last seat number 2.
	leave(4) -> null
	seat() -> 5, the student sits at the last seat number 5.
*/
public class ExamRoom {

	// Analysis:
	// Use priority queue to sort by customized class interval{int dist; int x, y;}.
	// Sort by larger distance and then sort by start index
	// seat(): pq.poll() to find interval of largest distance. Split and add new intervals back to queue.
	// leave(x): one seat will be in 2 intervals: remove both from pq, and merge to a new interval.
	PriorityQueue<Interval> pq;
	int N;
	class Interval{
		int x, y, dist;
		public Interval(int x, int y) {
			this.x = x;
			this.y = y;
			if(x == -1) {
				this.dist = y;
			} else if(y == N) {
				this.dist = N - 1 - x;
			} else {
				this.dist = Math.abs(x-y)/2;
			}
		}
	}
	
	public ExamRoom(int N) {
		this.N = N;
		this.pq = new PriorityQueue<Interval>((a, b) -> a.dist != b.dist ? b.dist - a.dist: a.x - b.x);
		pq.add(new Interval(-1, N));
	}
    // O(logn): poll top candidate, split into two new intervals
	public int seat() {
		int seat = 0;
		Interval interval = pq.poll();
		if(interval.x == -1) seat = 0;
		else if(interval.y == N) seat = N-1;
		else seat = interval.x + (interval.y - interval.x)/2;
		pq.offer(new Interval(interval.x, seat));
		pq.offer(new Interval(seat, interval.y));
		return seat;
	}
    // O(n)Find head and tail based on p. Delete and merge two ends
	public void leave(int p) {
		Interval head = null, tail = null;
		List<Interval> intervals = new ArrayList<Interval>(pq);
		for(Interval interval : intervals) {
			if(interval.x == p) tail = interval;
			if(interval.y == p) head = interval;
			if(head != null && tail != null) break;
		}
		// Delete
		pq.remove(head);
		pq.remove(tail);
		// Merge
		pq.offer(new Interval(head.x, tail.y));
	}
}
