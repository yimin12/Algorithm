/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yimin Huang
 *	Question1:
 *		Merge K sorted array into one big sorted array in ascending order
 *	FollowUp:
 *		Find common elements in k sorted arrays of similar size (length = n)
 * Algorithm Class
 */
public class MergeSortedArray {

	// linear traverse merge two sorted array 
	// Time: O(m + n), Space: O(m + n)
	public int[] mergeTwoSortedArray(int[] one, int[] two){
		if(one == null || two == null) return new int[] {};
		int[] merge = new int[one.length + two.length];
		int indexOne = 0, indexTwo = 0;
		for(int i = 0; i < merge.length; i++) {
			if(one[indexOne] < two[indexTwo]) {
				merge[i] = one[indexOne++];
			} else {
				merge[i] = two[indexTwo++];
			}
		}
		return merge;
	}
	
	// FollowUp 1
	// merge K sorted Array, you should use best first search, you should encapsulate the x, y of given 2d matrix
	// Time: O(n*k*logk), Space: O(k * n)
	public int[] mergeKSortedList(int[][] arraysOfArrays) {
		// Assumptions: arraysOfArrays is not null, none of the array is null either
		PriorityQueue<Entry> minHeap = new PriorityQueue<MergeSortedArray.Entry>();
		int length = 0;
		// step 1: klogk
		for(int i = 0; i < arraysOfArrays.length; i++) {
			int[] array = arraysOfArrays[i];
			length += array.length;
			if(array.length != 0) {
				minHeap.offer(new Entry(0, 0, array[0]));
			}
		}
		int result[] = new int[length];
		int cur = 0;
		while(!minHeap.isEmpty()) {
			Entry tmp = minHeap.poll();
			result[cur] = tmp.value;
			// best first search, ensure the y is not out of boundary
			if(tmp.y+1 < arraysOfArrays[tmp.x].length) {
				// reuse the Entry Object
				tmp.y++;
				tmp.value = arraysOfArrays[tmp.x][tmp.y];
				minHeap.offer(tmp);
			}
		}
		return result;
	}
	static class Entry{
		int x, y, value;
		Entry(int x, int y, int value){
			this.x = x;
			this.y = y;
			this.value = value;
		}
	}
	static class MyComparator implements Comparator<Entry>{
		
		@Override
		public int compare(Entry e1, Entry e2) {
			if(e1.value == e2.value) {
				return 0;
			}
			return e1.value < e2.value ? -1 : 1;
		}
	}
	
	// FollowUp3: find common elements in k sorted arrays, assume the element is integer
	// Use best first search Time: O(nk*logk) Space: O(k), but the most efficient way is comparing each arrays one by one
	// linear search each element in each array Time: O(kn), Space: O(n). Use HashSet
	public List<Integer> commonKSorted(int[][] arraysOfArrays){
		List<Integer> res = new ArrayList<Integer>();
		// Assumption: arraysOfArrays is not null
		if(arraysOfArrays == null) return res;
		// use swap rather than delete the rest element of set that have not appeared in new coming array
		HashSet<Integer> set = new HashSet<Integer>();
		HashSet<Integer> swap = new HashSet<Integer>();
		for(int i = 0; i < arraysOfArrays[0].length; i++) {
			set.add(arraysOfArrays[0][i]);
		}
		for(int i = 1; i < arraysOfArrays.length; i++) {
			for(int j = 0; j < arraysOfArrays[i].length; j++) {
				if(set.contains(arraysOfArrays[i][j])) {
					swap.add(arraysOfArrays[i][j]);
				}
			}
			System.out.println(swap.toString());
			// if you use reference, the set will be cleared as well when you clear hashset swap
			set = (HashSet<Integer>) swap.clone();
			swap.clear();
		}
		
		for(Integer integer : set) {
			
			res.add(integer);
		}
		return res;
	}
	
	public static void main(String[] args) {
		MergeSortedArray solution = new MergeSortedArray();
		int[][] matrix = new int[][] {{1,2,3,4,5}, {2,3,4,5,6},{3,4,5,6,7},{4,5,6,7,8}};
		List<Integer> commonKSorted = solution.commonKSorted(matrix);
		for(Integer integer : commonKSorted) {
			System.out.print(integer + " ");
		}
	}
}
