/**
 * 
 */
package williamsNotebook.common.segmentTree;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 上午12:29:38
* Description:
* 	For an integer array (index from 0 to n-1, where n is the size of this array), in the corresponding SegmentTree, 
* 	each node stores an extra attribute max to denote the maximum number in the interval of the array 
* 	(index from start to end).
* 	Design a query method with three parameters root, start and end, find the maximum number in the interval 
* 	[start, end] by the given root of segment tree
* 	Example: For array [1, 4, 2, 3], the corresponding Segment Tree is:
* 			  [0, 3, max=4]
             /             \
      [0,1,max=4]        [2,3,max=3]
      /         \        /         \
[0,0,max=1] [1,1,max=4] [2,2,max=2], [3,3,max=3]
	query(root, 1, 1), return 4
	query(root, 1, 2), return 4
	query(root, 2, 3), return 3
	query(root, 0, 2), return 4
*/

public class MaximumQuery {

	// There four possible situations
	// 1. included - recursively dive deeper
	// 2. excluded - stop traversing deeper
	// 3. partitioned - split in two part
	// 4. exactly match - return the value;
	
	static class SegmentTreeNode{
		int start, end, max;
		SegmentTreeNode left, right;
		public SegmentTreeNode(int start, int end, int max) {
			this.start = start;
			this.end = end;
			this.max = max;
			this.left = null;
			this.right = null;
		}
	}
	public SegmentTreeNode build(int[] array) {
		return buildTree(0, array.length - 1, array);
	}
	private SegmentTreeNode buildTree(int start, int end, int[] array) {
		// base case
		if(start > end) {
			return null;
		}
		// leaf node
		if(start == end) {
			return new SegmentTreeNode(start, end, array[start]);
		}
		SegmentTreeNode node = new SegmentTreeNode(start, end, array[start]);
		int mid = start + (end - start)/2;
		// recursion rule
		node.left = buildTree(start, mid + 1, array);
		node.right = buildTree(mid+1, end, array);
		
		if(node.left != null && node.left.max > node.max) {
			node.max = node.left.max;
		}
		if(node.right != null && node.right.max > node.max) {
			node.max = node.right.max;
		}
		return node;
	}
	public int query(SegmentTreeNode root, int start, int end) {
		if(start == root.start && root.end == end) {
			return root.max;
		}
		int mid = start + (end - start)/2;
		int leftMax = Integer.MIN_VALUE, rightMax = Integer.MIN_VALUE;
		// if start <= mid, it is necessary to traverse left subtree
		if(start <= mid) {
			if(mid < end) { 
			 	// 3. partitioned - split in two part
				leftMax = query(root.left, start, mid);
			} else {
				// 1. included - recursively dive deeper
				leftMax = query(root.left, start, end);		
			}
		}
		// if end > mid, it is necessary to traverse right subtree
		if(end > mid) {
		 	// 3. partitioned - split in two part
			if(start <= mid) {
				rightMax = query(root.right, mid + 1, end);
			} else {
				// 1. included - recursively dive deeper
				rightMax = query(root.right, start, end);			
			}
		}
		return Math.max(leftMax, rightMax);
	}
	// trade off: heap can not modify the elements, hash heap and balance binary tree can
	// in segment tree, the modify consume exactly log(n) time to find the specific interval and change the value
	// Time: O(logn)
	public void modify(SegmentTreeNode root, int index, int value) {
		// find specific leaf node, base case
		if(root.start == index && root.end == index) {
			root.max = value;
			return;
		}
		// recursion rule
		int mid = root.start +(root.end - root.start)/2;
		if(root.start <= index && index <= mid) { // search for index
			modify(root.left, index, value);
		}
		if(mid <= index && index < root.end) {
			modify(root.right, index, value);
		}
		root.max = Math.max(root.left.max, root.right.max);
		
	}
}
