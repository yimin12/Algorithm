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
* @version Create Time：2020年4月7日 下午2:01:34
* Description:
* 	Given a set of _non-overlapping _intervals, insert a new interval into the intervals (merge if necessary).
* 	You may assume that the intervals were initially sorted according to their start times.
* Example:
* 	Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
* 	Output: [[1,5],[6,9]]
* 	Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
* 	Output: [[1,2],[3,10],[12,16]]	
* 	Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*/

public class InsertInterval {

	// Follow Up for MergeInterval, And Assuming the given intervals is already sorted
	public List<Interval> insert(List<Interval> intervals, Interval newIterval){
		List<Interval> res = new ArrayList<Interval>();
		int index = 0;
		// Step 1: find the position
		while(index < intervals.size()) {
			if(newIterval.start > intervals.get(index).start) {
				index++;
			} else {
				break;
			}
		}
		intervals.add(index, newIterval);
		Interval last = null;
		for(Interval interval :intervals) {
			if(last == null || last.end < interval.start) {
				res.add(interval);
				last = interval;
			} else {
				last.end = Math.max(last.end, interval.end);
			}
		}
		return res;
	}
}
