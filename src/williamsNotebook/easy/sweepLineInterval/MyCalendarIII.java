/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.TreeMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午7:42:18
* Description:
* 	Implement a MyCalendarThree class to store your events. A new event can always be added.
* 	Your class will have one method, book(int start, int end). Formally, this represents a booking on the half open interval[start, end), the range of real numbers x such that start <= x < end.
* 	AK-bookinghappens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)
* 	For each call to the methodMyCalendar.book, return an integer K representing the largest integer such that there exists aK-booking in the calendar.
* 	Your class will be called like this:
* 		MyCalendarThree cal = new MyCalendarThree();
* 		MyCalendarThree.book(start, end)
* Example:
* 	MyCalendarThree.book(10, 20); // returns 1
	MyCalendarThree.book(50, 60); // returns 1
	MyCalendarThree.book(10, 40); // returns 2
	MyCalendarThree.book(5, 15); // returns 3
	MyCalendarThree.book(5, 10); // returns 3
	MyCalendarThree.book(25, 55); // returns 3
*/
public class MyCalendarIII {

	// Boundary Count by TreeMap and Sweep Line
	// A K-booking happens when K events have some non-empty intersection (ie., there is some time that is common to all K events.)
	TreeMap<Integer, Integer> calendar;
	
	public MyCalendarIII() {
		calendar = new TreeMap<Integer, Integer>();
	}
	// each booking() operation (n + logn)
	// Space: O(n)
	public int book(int start, int end) {
		calendar.put(start,  calendar.getOrDefault(start, 0) + 1);
		calendar.put(end, calendar.getOrDefault(end, 0) - 1);
		int ongoing = 0, max = 0;
		for(int v : calendar.values()) {
			ongoing += v;
			max = Math.max(max, ongoing);
		}
		return max;
	}
	
	
}
