/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
/**
 * @author yimin Huang
 *  
	Given k sorted integer arrays, pick k elements (one element from each of sorted arrays), what is 
	the smallest range.
	We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
	/*
	 * // Assumption:k >= 2 and None of the k arrays is null or empty Examples:
	 * 
	 * { { 1, 4, 6 },
	 * 
	 * { 2, 5 },
	 * 
	 * { 8, 10, 15} }
	 * 
	 * pick one element from each of 3 arrays, the smallest range is {5, 8} (pick 6
	 * from the first array, pick 5 from the second array and pick 8 from the third
	 * array).
	 
 * Algorithm Class
 */
public class SmallestRange {
	
	// Method 1. Brute force, iterate all combination and search validate the answer
	// Time: O((n*m)^3); Space: O(1)
	public int[] smallesRangetBrute(List<List<Integer>> nums) {
		int minX = 0, minY = Integer.MAX_VALUE;
		for(int i = 0; i < nums.size(); i++) {
			for(int j = 0; j < nums.get(i).size(); j++) {
				for(int k = i; k < nums.size(); k++) {
					for(int l = (k == i ? j : 0); l < nums.get(k).size(); l++) {
						int min = Math.min(nums.get(i).get(j), nums.get(k).get(l));
						int max = Math.max(nums.get(i).get(j), nums.get(k).get(l));
						int n, m;
						for(m = 0; m < nums.size(); m++) {
							for(n = 0; n < nums.get(m).size(); n++) {
								if(nums.get(m).get(n) >= min && nums.get(m).get(n) <= max) {
									// found
									break;
								}
							}
							if(n == nums.get(m).size()) {
								// going to the end of a list
								break;
							}
						}
						if(m == nums.size()) {
							if(minY - minX > max - min || (minY - minX == max - min && minX > min)) {
								minY = max;
								minX = min;
							}
						}
					}
				}
			}
		}
		return new int[] {minX, minY};
	}
	
	// Method 2: Similar with solution 1 but use binary search to validate every potential pair of number
	// Time: O((n*m)^2*log(n)*m), Space: O(1)
	public int[] smallesRangetBinarySearch(List<List<Integer>> nums) {
		int minX = 0, minY = Integer.MAX_VALUE;
		for(int i = 0; i < nums.size(); i++) {
			for(int j = 0; j < nums.get(i).size(); j++) {
				for(int k = i; k < nums.size(); k++) {
					for(int l = (k == i ? j : 0); l < nums.get(k).size(); l++) {
						int min = Math.min(nums.get(i).get(j), nums.get(k).get(l));
						int max = Math.max(nums.get(i).get(j), nums.get(k).get(l));
						int n, m;
						for(m = 0; m < nums.size(); m++) {
							n = Collections.binarySearch(nums.get(m), min);
							if(n < 0) {
								n = -1 - n;
							}
							if(n == nums.get(m).size() || nums.get(m).get(n) < min || nums.get(m).get(n) > max) {
								break;
							}
						}
						if(m == nums.size()) {
							if(minY - minX > max - min || (minY - minX == max - min && minX > min)) {
								minY = max;
								minX = min;
							}
						}
					}
				}
			}
		}
		return new int[] {minX, minY};
	}
	// Method 3: use k pointer to optimize the time complexity by using extra space
	// Time: O((n*m)*m), Space: O(m)
	public int[] smallestRangeKPointers(List<List<Integer>> nums) {
		int minX = 0, minY = Integer.MAX_VALUE;
		// store the k pointers
		int[] next = new int[nums.size()];
		boolean flag = true;
		for(int i= 0; i < nums.size() && flag; i++) {
			for(int j = 0; j < nums.get(i).size() && flag; j++) {
				// store the max index that hold the smallest value
				int min_i = 0, max_i = 0;
				for(int k = 0; k < nums.size(); k++) {
					// update min_i
					if(nums.get(min_i).get(next[min_i]) > nums.get(k).get(next[k])) {
						min_i = k;
					}
					// update max_i
					if(nums.get(max_i).get(next[max_i]) < nums.get(k).get(next[k])) {
						max_i = k;
					}
				}
				if(minY - minX > nums.get(max_i).get(next[max_i]) - nums.get(min_i).get(next[min_i])) {
					minY = nums.get(max_i).get(next[max_i]);
					minX = nums.get(min_i).get(next[min_i]);
				}
				// move the smallest value of listOfLists 
				next[min_i]++;
				if(next[min_i] == nums.get(min_i).size()) {
					flag = false;
				}
			}
		}
		return new int[] {minX, minY};
	}
	// Method 4 use best first search with priority queue:
	// Time: O(m*n*logm) Space: O(m)
	public int[] smallestRangeBFS(List<List<Integer>> nums) {
		int minX = 0, minY = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		// record n pointers
		int[] next = new int[nums.size()];
		boolean flag = true;
		// Compare by the current pointing number
		PriorityQueue<Integer> min_queue = new PriorityQueue<Integer>((i,j) -> nums.get(i).get(next[i]) - nums.get(j).get(next[j]));
		for(int i = 0; i < nums.size(); i++) {
			min_queue.offer(i);
			max = Math.max(max, nums.get(i).get(0));
		}
		for(int i = 0; i < nums.size() && flag; i++) {
			for(int j = 0; j < nums.get(i).size(); j++) {
				int min_i = min_queue.poll();
				if(minY - minX > max - nums.get(min_i).get(next[min_i])) {
					minX = nums.get(min_i).get(next[min_i]);
					minY = max;
				}
				next[min_i]++;
				if(next[min_i] == nums.get(min_i).size()) {
					flag =  false;
					break;
				}
				min_queue.offer(min_i);
				max = Math.max(max, nums.get(min_i).get(next[min_i]));
			}
		}
		return new int[] {minX, minY};
	}
	
	
	public static void main(String[] args) {
		SmallestRange solution = new SmallestRange();
		List<List<Integer>> listOfLists = new ArrayList<List<Integer>>();
		int[] array = new int[] {1,2,3};
		List<Integer> one = Arrays.asList(1,4,6);
		List<Integer> two = Arrays.asList(2, 5);
		List<Integer> three = Arrays.asList(8,10,15);
		listOfLists.add(one);
		listOfLists.add(two);
		listOfLists.add(three);
		
		int[] smallesRangetBrute = solution.smallestRangeBFS(listOfLists);
		for(int i : smallesRangetBrute) {
			System.out.print(i + " ");
		}
	}
}
