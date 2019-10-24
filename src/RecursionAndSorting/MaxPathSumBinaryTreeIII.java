/**
 * 
 */
package RecursionAndSorting;

import BinaryTreeAndBST.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月21日 下午3:08:21
* Description:
* 	Given a binary tree in which each node contains an integer number. Find the maximum 
* 	possible subpath sum(both the starting and ending node of the subpath should be on 
* 	the same path from root to one of the leaf nodes, and the subpath is allowed to contain 
* 	only one node).
* Assumptions:
* 	The root of given binary tree is not null
* Examples:
* 	  -5			The maximum path sum is 11 + 14 = 25
  	/    \
   2      11
        /    \
	   6     14
             /
		   -3
  How is the binary tree represented?
  	We use the level order traversal sequence with a special symbol "#" denoting the null node.
  	For example:
  		The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
	Representing:	  
		  	1
		  /   \
		 2     3
		      /
		    4
*/

public class MaxPathSumBinaryTreeIII {
	public int maxPathSum(TreeNode root) {
//		Assumption: root is not null
		int[] max = new int[] {Integer.MIN_VALUE};
		helper(root, max);
		return max[0];
	}
	private int helper(TreeNode root, int[] max) {
		if(root == null) {
			return 0;
		}
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		int sin = Math.max(Math.max(left, right), 0) + root.key;
		max[0] = Math.max(max[0], sin);
		return sin;
	}
}
