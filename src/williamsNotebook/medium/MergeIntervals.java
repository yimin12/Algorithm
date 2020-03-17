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
	
	// Follow Up 1: Missing Ranges, find missing intervals
	// Given a sorted integer array where the range of elements are [lower, upper] inclusive, return the missing range
	// For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
	// Time ~ O(N), Space ~ O(1)
	public List<String> findMissingRange(int[] array, int low, int high){
		// Assume that there are no duplicate there
		List<String> res = new ArrayList<String>();
		if(array == null || array.length == 0) return res;
		// step 1: find the lower bound and the high bound
		int lowIndex = 0, highIndex = array.length - 1;
		while(array[lowIndex] < low) {
			lowIndex++;
		}
		while(array[highIndex] > high) {
			highIndex--;
		}
		int prev = low - 1;
		// valid range is from array[lowIndex--,highIndex++].
		for(int i = lowIndex; lowIndex <= highIndex + 1; lowIndex++) {
			int curr = (i == highIndex) ? high + 1 : array[i];
			if(curr - prev > 2) res.add(getRange(prev+1, curr-1));
			prev = curr;
		}
		return res;
	}
	private String getRange(int from, int to) {
		return (from == to) ? Integer.toString(from):from + "->"+to;
	}
	
	// Follow Up 2: Summary Ranges: Form intervals
	// Given a sorted integer array without duplicates, return the summary of its ranges.
	// For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	// Time ~ O(N), Space ~ O(1)
	public List<String> summaryRange(int[] array){
		List<String> ranges = new ArrayList<String>();
		int n = array.length;
		if(n == 0) return ranges;
		int from = array[0], to = array[0], prev = array[0];
		for(int i = 1; i < n; i++) {
			int cur = array[i];
			if(cur - prev == 1) {
				// continuous element
				to = cur;
			} else {
				ranges.add(getRange(from, to));
				from = cur;
				to = cur;
			}
			prev = cur;
		}
		ranges.add(getRange(from, to));
		return ranges;
	}
	
	// Follow Up 3: Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
	// You may assume that the intervals were initially sorted according to their start times.
	// Example 1:
	// Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	// Example 2:
	// Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16]. This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	// Insert with Array: Time ~ O(2logN), Space ~ O(1)
	public List<Interval> insert(List<Interval> intervals, Interval newInterval){
		// Assume that given intervals is sorted
		int start = searchInsert(intervals, newInterval.start);
		int end = searchInsert(intervals, newInterval.end);
		Interval addInterval = new Interval();
		int removeStart = 0, removeEnd = 0;
		if((start & 1) == 1) {
			addInterval.start = getIntervalSlide(intervals, start - 1);
			removeStart = start /2;
		} else {
			addInterval.start = newInterval.start;
			removeStart = start/2;
		}
		if((end & 1) == 1) {
			addInterval.end = getIntervalSlide(intervals, end);
			removeEnd = end/2;
		} else {
			if (end < 2 * intervals.size() && newInterval.end == getIntervalSlide(intervals, end)) {
				addInterval.end = getIntervalSlide(intervals, end + 1);
				removeEnd = end/2;
			} else {
				addInterval.end = newInterval.end;
				removeEnd = end/2 - 1;
			}
		}
		for(int i = removeStart; i <= removeEnd; i++) {
			intervals.remove(removeStart);
			
		}
		intervals.add(removeStart, addInterval);
		return intervals;
	}
	private int searchInsert(List<Interval> intervals, int target) {
		if(intervals.size() == 0) return 0;
		int left = 0, right = 2 * intervals.size() -1;
		while(left < right) {
			int mid = left + (right - left);
			int val = getIntervalSlide(intervals, mid);
			if(val == target) return mid;
			else if(val < target) left = mid + 1;
			else right = mid;
		}
		int val = getIntervalSlide(intervals, left);
		return (val >= target) ? left : left + 1;
	}
	private int getIntervalSlide(List<Interval> intervals, int index) {
		if((index & 1) == 0) {
			return intervals.get(index/2).start;
		} else {
			return intervals.get(index/2).end;
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
		
		List<String> findMissingRange = solution.findMissingRange(new int[] {0, 1, 3, 50, 75}, 0, 99);
		System.out.println(findMissingRange.toString());
		
		List<String> summaryRange = solution.summaryRange(new int[] {0,1,2,4,5,7});
		System.out.println(summaryRange);
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
