/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import williamsNotebook.common.node.Interval;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午12:04:42
* Description:
* 	Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei), 
* 	find the minimum number of conference rooms required
* Example:
* 	Input: [[0, 30],[5, 10],[15, 20]]
* 	Output:	2
* 	Input:  [[7,10],[2,4]]
* 	Output: 1
*/

public class MeetingRoomsII {

	// Method 1: Sweep Line
	class Point {
		int type; // start : 1, end : 0
		int time;
		
		public Point(int type, int time) {
			this.time = time;
			this.type = type;
		}
	}
	public int minMeetingRooms(Interval[] intervals) {
		List<Point> points = new ArrayList<Point>(intervals.length * 2);
		for(Interval interval : intervals) {
			points.add(new Point(interval.start, 1));
			points.add(new Point(interval.end, 0));
		}
		Comparator<Point> cmp = new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.time == o2.time) {
					return o1.type - o2.type;
				} else {
					return o1.time - o2.time;
				}
			}
		};
		Collections.sort(points, cmp);
		int maxOverlap = 0;
		int ongoing = 0;
		for(Point p:points) {
			if(p.type == 1) {
				ongoing++;
			} else if(p.type == 0) {
				ongoing--;
			}
			maxOverlap = Math.max(maxOverlap, ongoing);
		}
		return maxOverlap;
	}
	// Method 2: Version II of sweep line
	public int minMeetingRoomsII(Interval[] intervals) {
		int[] starts = new int[intervals.length];
		int[] ends = new int[intervals.length];
		for(int i = 0; i < intervals.length; i++) {
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int rooms = 0, endsItr = 0;
		for(int i = 0; i < starts.length; i++) {
			if(starts[i] < ends[endsItr]) {
				rooms++;
			} else {
				endsItr++;
			}
		}
		return rooms;
	}
	// Method 3: TreeMap
	public int minMeetRoomsTreeMap(Interval[] intervals) {
		// key: time, value: cnt
		TreeMap<Integer, Integer> tmap = new TreeMap<Integer, Integer>();
		int res = 0, cnt = 0;
		for(Interval interval:intervals) {
			int start = interval.start, end = interval.end;
			tmap.put(start, tmap.getOrDefault(start, 0) + 1); // start : positive affect
			tmap.put(end, tmap.getOrDefault(end, 0) - 1); // end : negative affect, can have negative value
		}
		for(int k : tmap.keySet()) {
			cnt += tmap.get(k);
			res = Math.max(res, cnt);
		}
		return res;
	}
}
