/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午6:02:41
* Description:
* 	Implement a MyCalendarTwo class to store your events. A new event can be added if adding the event will not cause a 
* 	triple booking.
* 	Your class will have one method,book(int start, int end). Formally, this represents a booking on the half open 
* 	interval[start, end), the range of real numbers x such that start <= x < end.
* 	A triple booking happens when three events have some non-empty intersection (ie., there is some time that is common to all 3 events.)
* 	For each call to the methodMyCalendar.book, return true if the event can be added to the calendar successfully 
*   without causing a triple booking. Otherwise, return false and do not add the event to the calendar.
*   MyCalendar();
		MyCalendar.book(10, 20); // returns true
		MyCalendar.book(50, 60); // returns true
		MyCalendar.book(10, 40); // returns true
		MyCalendar.book(5, 15); // returns false
		MyCalendar.book(5, 10); // returns true
		MyCalendar.book(25, 55); // returns true
*/
public class MyCalendarII {

	// Method 1: Brute Force, use two List to avoid triple booking
	List<int[]> calendar;
	List<int[]> overlaps;
	public MyCalendarII() {
		calendar = new ArrayList<int[]>();
		calenderMap = new TreeMap<Integer, Integer>();
	}
	// Time Complexity: O(n^2) where we process n times of booking
	// Time : O(n) each time
	// Space Complexity : O(n)
	public boolean book(int start, int end) {
		for(int[]  iv : overlaps) {
			if(iv[0] < end && iv[1] > start) return false;
		}
		for(int[] iv : calendar) {
			if(iv[0] < end && start < iv[1]) {
				overlaps.add(new int[] {Math.max(start, iv[0]), Math.min(end, iv[1])});
			}
		}
		calendar.add(new int[] {start, end});
		return true;
	}
	
	// Method 2:
	// Time complexity for N times booking is O(n(logn + n)) and the worst case is O(n^2 + nlogn)
	// Space: O(n)
	TreeMap<Integer, Integer> calenderMap;
	public boolean bookTreeMap(int start, int end) {
		calenderMap.put(start, calenderMap.getOrDefault(start, 0) + 1);
		calenderMap.put(end,  calenderMap.getOrDefault(end, 0) -1 );
		int ongoing = 0;
		for(int v : calenderMap.values()) {
			ongoing += v;
			if(ongoing >=3) {
				// reset 
				calenderMap.put(start, calenderMap.get(start) - 1);
				calenderMap.put(end, calenderMap.get(end) + 1);
				// remove the tentative key, part of reset because we add the data to calenderMap at the begining;
				if(calenderMap.get(start) == 0) {
					calenderMap.remove(start);
				}
				if(calenderMap.get(end) == 0) {
					calenderMap.remove(end);
				}
				return false;
			}
		}
		return true;
	}
}
