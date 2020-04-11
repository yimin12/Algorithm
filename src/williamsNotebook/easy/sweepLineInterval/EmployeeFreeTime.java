/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import williamsNotebook.common.node.Interval;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午2:39:30
* Description:
* 	We are given a list scheduleof employees, which represents the working time for each employee.
* 	Each employee has a list of non-overlappingIntervals, and these intervals are in sorted order.
* 	Return the list of finite intervals representing common, positive-length free time forallemployees, also in sorted order.
* Example:
* 	Input:	 schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
* 	Output:	 [[3,4]]
* Explanation:
* 	There are a total of three employees, and all common free time intervals would be [-inf, 1], [3, 4], [10, inf]. We discard any intervals that contain inf as they aren't finite.	
*/
public class EmployeeFreeTime {

	// Logic is similar with MeetingRoom
	public List<Interval> employFreeTime(List<List<Interval>> schedules){
		List<Interval> res = new ArrayList<Interval>();
		List<Interval> timeLine = new ArrayList<Interval>();
		schedules.forEach(e -> timeLine.addAll(e)); // put all the interval to a single one container
		Collections.sort(timeLine, (a, b)-> a.start - b.start);
		Interval temp = timeLine.get(0);
		for(Interval interval:timeLine) {
			if(temp.end < interval.start) {
				// free time
				res.add(new Interval(temp.end, interval.start));
				temp = interval;
			} else {
				// at least two of employees are working
				temp = temp.end <interval.end ? interval : temp;
			}
		}
		return res;
	}
}
