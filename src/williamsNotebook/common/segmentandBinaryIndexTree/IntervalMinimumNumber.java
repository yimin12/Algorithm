/**
 * 
 */
package williamsNotebook.common.segmentandBinaryIndexTree;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 上午11:20:34
* Description:
* 	Given an integer array (index from 0 to n-1, where n is the size of this array), and an query list. 
* 	Each query has two integers [start, end]. For each query, calculate the minimum number between index 
* 	start and end in the given array, return the result list.
* 	We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.
* 	For array [1,2,7,8,5], and queries [(1,2),(0,4),(2,4)], return [2,1,5]
*/

public class IntervalMinimumNumber {

	// step 1: construct the SegmentTreeNode
	private class SegmentTreeNode{
		int start, end, min;
		SegmentTreeNode left, right;
		public SegmentTreeNode(int start, int end, int min) {
			this.start = start;
			this.end = end;
			this.min = min;
			this.left = right = null;
		}
	}
	// step 2: implement common APIs
	public SegmentTreeNode build(int[] array) {
		if(array == null || array.length == 0) return null;
		return buildTree(0, array.length - 1, array);
	}
	private SegmentTreeNode buildTree(int start, int end, int[] array) {
		if(start > end) return null; // corner case
		if(start == end) return new SegmentTreeNode(start, end, array[start]); // base case
		SegmentTreeNode root = new SegmentTreeNode(start, end, Integer.MAX_VALUE);
		int mid = start + (end - start)/2;
		// recursion rule
		root.left = buildTree(start, mid, array);
		root.right = buildTree(mid+1, end, array);
		root.min = Math.min(root.left.min, root.right.min);
		return root;
	}
	public int query(SegmentTreeNode root, int start, int end) {
		if(start == root.start && end == root.end) {
			return root.min;
		}
		int mid = root.start + (root.end - root.start)/2;
		int leftMin = Integer.MAX_VALUE, rightMin = Integer.MAX_VALUE;
		if(start <= mid) {
			if(mid < end) { // split
				leftMin = query(root.left, start, mid);
			} else { // include
				leftMin = query(root.left, start, end);
			}
		}
		if(end > mid) {
			if(start <= mid) {
				rightMin = query(root.right, mid + 1, end);
			} else {
				rightMin = query(root.right, start, end);
			}
		}
		// exclude
		return Math.min(leftMin, rightMin);
	}
	// Step 3: query for you requirement
	// Time : O(k log n) k for queries times and n for the number of elements in given array
	public List<Integer> intervalMinNumber(int[] A, List<List<Integer>> queries){
		SegmentTreeNode root = build(A);
		List<Integer> res = new ArrayList<Integer>();
		for(List<Integer> list : queries) {
			res.add(query(root, list.get(0), list.get(1)));
		}
		return res;
	}
}
