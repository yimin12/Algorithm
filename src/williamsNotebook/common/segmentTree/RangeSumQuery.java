/**
 * 
 */
package williamsNotebook.common.segmentTree;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月29日 下午7:41:00
* Description:
* 	Given an integer array nums, find the sum of the elements between indices i and j (i≤j), inclusive.
* 	The update(i, val) function modifies nums by updating the element at index i to val.
* 	Given nums = [1, 3, 5]
* 	sumRange(0, 2) -> 9  update(1, 2)  sumRange(0, 2) -> 8
*/

public class RangeSumQuery {

	static class SegmentTreeNode{
		int start, end, sum;
		SegmentTreeNode left, right;
		public SegmentTreeNode(int start, int end, int sum) {
			this.start = start;
			this.end = end;
			this.sum = sum;
			this.left = null;
			this.right = null;
		}
	}
	static class SegmentTree{
		SegmentTreeNode root;
		public SegmentTree(int[] nums) {
			root = build(0, nums.length - 1,nums);
		}
		public SegmentTreeNode build(int start, int end, int[] nums) {
			if(start > end) {
				return null;
			}
			SegmentTreeNode root = new SegmentTreeNode(start, end, 0);
			if(start == end) {
				root.sum = nums[start];
			} else {
				int mid = start + (end - start) / 2;
				root.left = build(start, mid, nums);
				root.right = build(mid + 1, end, nums);
				root.sum = root.left.sum + root.right.sum;
			}
			return root;
		}
		public int query(SegmentTreeNode root, int start, int end) {
			if(root.start == start && root.end == end) {
				return root.sum;
			}
			int mid = root.start + (root.end - root.start)/2;
			int leftSum = 0, rightSum = 0;
			// find the left boundary
			if(start <= mid) {
				if(mid < end) {
					leftSum = query(root.left, start, mid);
				} else {
					leftSum = query(root.left, start, end);
				}
			}
			// find the rig
			if(mid < end) {
				if(start <= mid) {
					rightSum = query(root.right, mid + 1, end);
				} else {
					rightSum = query(root.right, start, end);
				}
			}
			return leftSum + rightSum;
		}
		public void modify(SegmentTreeNode root, int index, int value) {
			if(root.start == index && root.end == index) {
				root.sum = value;
			}
			int mid = root.start + (root.end - root.start)/2;
			if(index <= mid && root.start <= mid) {
				modify(root.left, index, value);
			} else if(index > mid && root.end >= mid) {
				modify(root.right, index, value);
			}
			root.sum = root.left.sum + root.right.sum;
		}
	}
	SegmentTree st;
	public RangeSumQuery(int[] nums) {
		this.st = new SegmentTree(nums);
	}
	public void update(int i, int val) {
		st.modify(st.root, i, val);
	}
	public int sumRange(int i, int j) {
		return st.query(st.root, i, j);
	}
	
}
class NumberArray{
	private int[] arr, bit;
	public NumberArray(int nums[]) {
		arr = new int[nums.length];
		bit = new int[nums.length + 1];
		for(int i = 0; i < nums.length; i++) {
			update(i, nums[i]);
		}
	}
	public void update(int index, int val) {
		int delta = val-arr[index];
		arr[index] = val;
		
		for(int i = index + 1; i <= arr.length; i= lowbit(i)) {
			bit[i] += delta;
		}
	}
	public int getPrefixSum(int index) {
		int sum = 0;
		for(int i = index + 1; i > 0; i = i-lowbit(i)) {
			sum += bit[i];
		}
		return sum;
	}
	private int lowbit(int x) {
		return x & (-x);
	}
	public int getRange(int left, int right) {
		return getPrefixSum(right) - getPrefixSum(left - 1);
	}
	
}
	
