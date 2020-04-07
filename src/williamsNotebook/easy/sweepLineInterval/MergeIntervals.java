/**
 * 
 */
package williamsNotebook.easy.sweepLineInterval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import williamsNotebook.common.node.Interval;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月7日 下午1:46:32
* Description:
* 	Given a collection of intervals, merge all overlapping intervals.
* Example:
* 	Input:  [[1,3],[2,6],[8,10],[15,18]]
* 	Output:	 [[1,6],[8,10],[15,18]]
* Explanation:
* 	 Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].	
*/
public class MergeIntervals {

	// Key Insight: If we sort the intervals by their start value, then each set of intervals that can be merged will appear as a contiguous "run" in the sorted list.
	public List<Interval> merge(List<Interval> intervals){
		List<Interval> ans = new ArrayList<Interval>();
		if(intervals == null) {
			return ans;
		}
		intervals.sort(Comparator.comparing(i -> i.start));
		Interval last = null;
		// Idea is similar with two cursor operation with linked list
		for(Interval item : intervals) {
			if(last == null || last.end < item.start) {
				ans.add(item);
				last = item;
			} else {
				last.end = Math.max(last.end, item.end);
			}
		}
		return ans;
	}
	// Version 2:
	public List<Interval> mergeII(List<Interval> intervals){
		Collections.sort(intervals,((a, b) ->  a.start < b.start ? -1 : a.start == b.start ? 0 : 1));
		LinkedList<Interval> merged = new LinkedList<Interval>();
		for(Interval interval : intervals) {
			if(merged.isEmpty() || merged.getLast().end < interval.start) {
				merged.add(interval);
			} else {
				merged.getLast().end = Math.max(interval.end, merged.getLast().end);
			}
		}
		return merged;
	}
}
