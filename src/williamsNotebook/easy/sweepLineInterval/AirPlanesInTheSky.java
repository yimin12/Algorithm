/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import williamsNotebook.common.node.Interval;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午2:09:44
* Description:
* 	Given an interval list which are flying and landing time of the flight. How many airplanes are on the sky at most?
* 	If landing and flying happens at the same time, we consider landing should happen at first.
* Example:                  Return :3
*   [
	  [1,10],
	  [2,3],
	  [5,8],
	  [4,7]
	]
*/
public class AirPlanesInTheSky {

	// This question is similar with MeetingRoomsII
	// Method 1: Sweep Line
	// Time : O(nlogn) for extra sorting algorithm and Space: O(n)
	class Point{
		int time, delta;
		Point(int time, int delta){
			this.time = time;
			this.delta = delta;
		}
	}
	public int countOfAirPlanes(List<Interval> airplanes) {
		if(airplanes == null || airplanes.isEmpty()) return 0;
		List<Point> timeLine = new ArrayList<Point>(airplanes.size() * 2);
		for(Interval plane : airplanes) {
			timeLine.add(new Point(plane.start, 1));
			timeLine.add(new Point(plane.end, -1));
		}
		Collections.sort(timeLine, (a, b) -> a.time == b.time ? a.delta - b.delta : a.time - b.time);
		int max = 0, sum = 0;
		for(Point point : timeLine) {
			sum += point.delta;
			max = Math.max(max, sum);
		}
		return max;
	}
	// Method 2: Use HashMap for time optimization(do not need to sort)
	// Time : O(k) Space: O(k) k is the flight period from the first plane departing to the last plane landing
	public int countAirplanesII(List<Interval> airplanes) {
		if (airplanes == null || airplanes.size() == 0) {  
            return 0;  
        }  
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        for(Interval flight : airplanes) {
        	int start = flight.start;
        	int end = flight.end;
        	for(int i = start; i < end; i++) {
        		map.put(i, map.getOrDefault(i, 0) + 1);
        		max = Math.max(max,  map.get(i));
        	}
        }
        return max;
	}
}
