/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午4:42:15
* Description:
* 	Implement aMyCalendarclass to store your events. A new event can be added if adding the event will not cause a double booking.
* 	Your class will have the method,book(int start, int end). Formally, this represents a booking on the half open 
* 	interval[start, end), the range of real numbersxsuch thatstart <= x < end.
* 	Adouble bookinghappens when two events have some non-empty intersection (ie., there is some time that is common to both events.)
* 	For each call to the methodMyCalendar.book, returntrueif the event can be added to the calendar successfully 
*   without causing a double booking. Otherwise, returnfalseand do not add the event to the calendar.
*   Your class will be called like this:
*   	MyCalendar cal = new MyCalendar();
*   	MyCalendar.book(start, end)
* Example:
* 	MyCalendar();
		MyCalendar.book(10, 20); // returns true
		MyCalendar.book(15, 25); // returns false
		MyCalendar.book(20, 30); // returns true
  Explanation:
  	The first event can be booked.  The second can't because time 15 is already booked by another event.
	The third event can be booked, as the first event takes every time less than 20, but not including 20.
*/
public class MyCalendarI {
	// Method 1: Naive ArrayList Implementation
	// Time: O(N) time for each book() operation, N is existing booking events in the list
	// Space: O(N) extra space
	List<int[]> calender;
	public MyCalendarI() {
		calender = new ArrayList<int[]>();	// for method 1	
		calenderMap = new TreeMap<Integer, Integer>(); //  for method 2
	}
	public boolean book(int start, int end) {
		for(int[] i : calender) {
			// i[0] > end || i[1] < start return true. 
			if(i[0] < end && i[1] > start) {
				return false;
			}
		}
		calender.add(new int[] {start, end});
		return true;
	}
	// Method 2: TreeMap
	// Time: O(logn)
	// Space: O(n)
	TreeMap<Integer, Integer> calenderMap;
	public boolean bookTreeMap(int start, int end) {
		Integer prev = calenderMap.floorKey(start); // largest value smaller than start
		Integer next = calenderMap.ceilingKey(end); // smallest value larger than end
		if((prev == null || start >= calenderMap.get(prev)) && (next == null || end <= next)) {
			calenderMap.put(start,  end);
			return true;
		}
		return false;
	}
}
