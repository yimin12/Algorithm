/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.Arrays;
import java.util.Comparator;

import williamsNotebook.common.node.Interval;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 上午11:35:00
* Description:
* 	Given an array of meeting time intervals consisting of start and end times[[s1,e1],[s2,e2],...](si< ei), determine 
* 	if a person could attend all meetings.
* Input: [[0,30],[5,10],[15,20]]
* 	Output: false
* Input: [[7,10],[2,4]]
* 	Output: true;
*/
public class MeetingRoomsI {

	// When you encounter Interval problem, preferred sorted first
	// Then, go through the meetings one by one and make sure that each meeting ends before the next one starts.
	// Time complexity : O(nlogn). The time complexity is dominated by sorting. Once the array has been sorted, only O(n) time is taken to go through the array and determine if there is any overlap
	// Space complexity : O(1). Since no additional space is allocated.
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				// TODO Auto-generated method stub
				return o1.start - o2.start;
			}
		});
		for(int i = 0; i < intervals.length; i++) {
			if(intervals[i].end > intervals[i+1].start) return false;
		}
		return true;
	}
	// Version 2 with sorting, lambda expression
	public boolean canAttendMeetingsII(Interval[] intervals) {
	    // Sort the intervals by start time
	    Arrays.sort(intervals, (x, y) -> x.start - y.start);
	    for (int i = 1; i < intervals.length; i++)
	        if (intervals[i-1].end > intervals[i].start)
	            return false;
	    return true;
	}
}
