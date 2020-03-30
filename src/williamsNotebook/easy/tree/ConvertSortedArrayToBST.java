/**
 * 
 */
package williamsNotebook.easy.tree;

import williamsNotebook.common.node.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午4:32:18
* Description:
* 	Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
* 	For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of_every_node never differ by more than 1.
* 	Given the sorted array: [-10,-3,0,5,9],
* 	  0
     / \
   -3   9
   /   /
 -10  5
*/
public class ConvertSortedArrayToBST {

	// Use Recursion and base on the definition
	public TreeNode sortedArrayToBST(int[] nums) {
		if(nums == null || nums.length == 0) return null;
		return buildTree(nums, 0, nums.length - 1);
	}
	public TreeNode buildTree(int[] nums, int left, int right) {
		if(left > right) {
			return null;
		}
		int mid = left + (right - left)/2;
		TreeNode node = new TreeNode(nums[mid]);
		node.left = buildTree(nums, left, mid - 1);
		node.right = buildTree(nums, mid + 1, right);
		return node;
	}
}
