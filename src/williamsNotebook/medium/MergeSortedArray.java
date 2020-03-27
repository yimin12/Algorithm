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
	// Method 2: Divide and Conquer, which is binary reduction
	// Time:  O(N log k). Space is O(logK) for calling stack
	public int[] mergeKSortedArraysII(int[][] arrays) {
		if(arrays == null || arrays.length == 0 || arrays[0].length == 0) return new int[0];
		return divide_conquer(arrays);
	}
	private int[] divide_conquer(int[][] arrays) {
		while(arrays.length > 1) {
			int n = arrays.length / 2;
			// auto fill when the number of arrays is odd
			if(arrays.length % 2 == 1) n++;
			// can initialize later
			int temp[][] = new int[n][];
			int index = 0;
			for(int i = 0; i < arrays.length && i + 1 < arrays.length; i+=2) {
				temp[index++] = mergeTwoSortedArrayII(arrays[i], arrays[i+1]);
			}
			if(arrays.length%2 == 1) temp[index++] = arrays[arrays.length - 1];
			arrays = temp;
		}
		return arrays[0];
	}
	private int[] mergeTwoSortedArrayII(int[] one, int[] two) {
		// base case
		if(one == null || one.length == 0) return two;
		if(two == null || two.length == 0) return one;
		int totalLength = one.length + two.length;
		int[] res = new int[totalLength];
		int index = 0;
		int leftBound = 0, rightBount = 0;
		while(leftBound < one.length && rightBount < two.length) {
			if(one[leftBound] <= two[rightBount]) {
				res[index++] = one[leftBound++];
			} else {
				res[index++] = two[rightBount++];
			}
		}
		// do the post processing
		while(leftBound < one.length) {
			res[index++] = one[leftBound++];
		}
		while(rightBount < two.length) {
			res[index++] = two[rightBount++];
		}
		return res;
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
