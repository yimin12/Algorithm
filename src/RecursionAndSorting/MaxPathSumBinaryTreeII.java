/**
 * 
 */
package RecursionAndSorting;

import BinaryTreeAndBST.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月21日 下午3:20:40
* Description:
* 	Given a binary tree in which each node contains an integer number. Find the maximum 
* 	possible sum from any node to any node (the start node and the end node can be the same). 
* Assumption:
* 	The root of the given binary tree is not null
* Examples:
* 	-1				one example of paths could be -14 -> 11 -> -1 -> 2
  /    \			another example could be the node 11 itself
2      11			The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
     /    \
    6    -14
  How is the binary tree represented?	
	We use the level order traversal sequence with a special symbol "#" denoting the null node.
  For Example: 
  	The sequence [1, 2, 3, #, #, 4] represents the following binary tree:	
		    1
		  /   \
		 2     3
		      /
		    4
*/

public class MaxPathSumBinaryTreeII {
	public int maxPathSum(TreeNode root) {
//		max stores the global maximum path sum and will be updated during the recursion
		int[] max = new int[] {Integer.MIN_VALUE};
		helper(root, max);
		return max[0];
	}
//	return the maximum path sum of the 'single' path
	private int helper(TreeNode root, int[] max) {
		if(root == null) {
			return 0;
		}
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		left = left < 0 ? 0 : left;
		right = right < 0 ? 0 : right;
//		one difference between II and III is that :
//		 	II can from left to root then to right
//			III requires from top to bottom
		max[0] = Math.max(root.key + left + right, max[0]);
		return root.key + Math.max(left, right);
	}
	
}
