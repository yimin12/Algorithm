/**
 * 
 */
package williamsNotebook.common.segmentTree;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月29日 下午5:12:06
* Description:
* 	The structure of Segment Tree is a binary tree which each node has two attributes start and end denote an segment / interval.
* 	start and end are both integers, they should be assigned in following rules:
* 	The root's start and end is given by build method.
* 	The left child of node A has start=A.left, end=(A.left + A.right) / 2.
* 	The right child of node A has start=(A.left + A.right) / 2 + 1, end=A.right. if start equals to end, there will be no children for this node.
* 	Implement a build method with two parameters start and end, so that we can create a corresponding segment tree with every node has the correct start and end value, return the root of this segment tree.
*/
public class SegmentTree {
	
	// There four possible situations
	// 1. included - recursively dive deeper
	// 2. excluded - stop traversing deeper
	// 3. partitioned - split in two part
	// 4. exactly match - return the value;

	static class SegmentTreeNode{
		public int start, end;
		public SegmentTreeNode left,right;
		public SegmentTreeNode(int start, int end) {
			this.start = start; this.end = end;
			this.left = this.right = null;
		}
	}
	
	public SegmentTreeNode build(int start, int end) {
		if(start > end) return null;
		SegmentTreeNode root = new SegmentTreeNode(start, end);
		if(start != end) {
			int mid = start + (start - end)/2;
			root.left = build(start, mid);
			root.right = build(mid+1, end);
		}
		return root;
	}
}
