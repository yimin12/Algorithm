/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author yimin Huang
 *	
 *	Given a collection of intervals, merge all overlapping internvals
 *	Sample :
 *		Input: [1,3],[2,6],[8,10],[15,18]
 *		Output: [1,6],[8,10],[15,18]
 *	Assumption: The given itervals is valid
 *
 * Algorithm Class
 */
public class MergeIntervals {
	
	// Method 1. Naive solution, try to merge pair of interval until we can not find any lap interval (key insight, find every intervals that has overlap)
	// Time : O(n^2) and Space: O:(n) - everytime mergeHelper creates an extra list for temp result
	// we try every single pair of it because it is not sorted
	public List<Interval> merge(List<Interval> intervals){
		// sanity check
		if(intervals == null || intervals.size() == 0) return intervals;
		// merge it inplace
		while(mergeHelper(intervals));
		return intervals;
	}
	// if nothing change, return false. the main function will break the while loop and return
	private boolean mergeHelper(List<Interval> intervals) {
		List<Interval> result = new ArrayList<Interval>();
		Set<Integer> merged = new HashSet<Integer>();
		boolean isChange = false;
		for(int i = 0; i < intervals.size(); i++) {
			if(merged.contains(i)) {
				continue;
			}
			Interval newInterval = intervals.get(i);
			for(int j = i + 1; j < intervals.size(); j++) {
				// two possible for overlap: [1,3][2,6] ; [2,6][1,3]
				if((newInterval.start <= intervals.get(j).start && newInterval.end >= intervals.get(j).start) 
						|| (newInterval.start >= intervals.get(j).start && newInterval.start <= intervals.get(j).end)) {
					newInterval = new Interval(Math.min(newInterval.start, intervals.get(j).start), Math.max(intervals.get(j).end, newInterval.end));
					merged.add(j);
					isChange = true;
				}
			}
			result.add(newInterval);
		}
		if(isChange) {
			intervals.clear();
			intervals.addAll(result);
		}
		return isChange;
	}	
	// Method 2: sort the Interval first and the Time complexity will reduced to O(nlogn), Space : O(n) for storing the result.
	public List<Interval> mergeSorted(List<Interval> intervals){
		// sort the intervals with O(nlogn)
		Collections.sort(intervals, new IntervalComparator());
		LinkedList<Interval> merged = new LinkedList<Interval>();
		// Iterate all the interval O(n)
		for(Interval interval : intervals) {
			// if merged is empty or Last interval.end < interval.start which means that it is isolated
			if(merged.isEmpty() || merged.getLast().end < interval.start) {
				merged.add(interval);
			}
			// otherwise, there is overlap, so we merge the current and previous intervals.
			else {
				merged.getLast().end = Math.max(merged.getLast().end, interval.end);
			}
		}
		return merged;
	}
	// create private nest class
	private class IntervalComparator implements Comparator<Interval>{
		// sort the interval by interval.start in ascending order
		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.start < o2.start ? -1 : o1.start == o2.start ? 0 : 1;
		}
		
	}
	
	
	
	public static void main(String[] args) {
		MergeIntervals solution = new MergeIntervals();
		List<Interval> intervals = new LinkedList<Interval>();
		intervals.add(new Interval(1,3));
		intervals.add(new Interval(2,6));
		intervals.add(new Interval(8,10));
		intervals.add(new Interval(15,18));
		List<Interval> merge = solution.merge(intervals);
		List<Interval> merge2 = solution.mergeSorted(intervals);
		System.out.println(merge.toString());
		System.out.println(merge2.toString());
	}
}
// helper class to encapsulate Intervals
class Interval{
	
	int start, end;
	public Interval() {
		super();
	}
	public Interval(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	@Override
	public String toString() {
		return "[start=" + start + ", end=" + end + "]";
	}
	
}
