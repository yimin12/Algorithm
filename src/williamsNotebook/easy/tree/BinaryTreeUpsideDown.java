/**
 * 
 */
package williamsNotebook.easy.tree;

import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *
 *	Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) 
 *	or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 *	For example:
 *		Given a binary tree {1,2,3,4,5},
 *		   1
	      / \
	     2   3
	    / \
	   4   5
	   	return the root of the binary tree [4,5,2,#,#,3,1].
	   	4
  	   / \
      5   2
         / \
        3   1
 *
 * Algorithm Class
 */
public class BinaryTreeUpsideDown {

	// Method 1: Iterative way, Top Down Time : O(N) and O(1) Extra space
	public TreeNode UpsideDownBinaryTree(TreeNode root) {
		TreeNode prev = null, prevRight = null;
		while(root != null) {
			TreeNode next = root.left;
			TreeNode right = root.right;
			root.right = prevRight;
			root.left = prev;
			prev = root;
			root = next;
		}
		return prev;
	}
	// Method 2: Recursion, Bottom Up  Time: O(N) and O(logN) extra space
	public TreeNode reverse(TreeNode root) {
		if(root == null || root.left == null) return root;
		TreeNode newRoot = reverse(root.left);
		root.left.right = root.right;
		root.left.left = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}
}
