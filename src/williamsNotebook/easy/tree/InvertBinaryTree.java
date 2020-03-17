/**
 * 
 */
package williamsNotebook.easy.tree;

import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *
 *		Invert a binary tree.			to
 *		 4									4
	   /   \							  /   \
	  2     7							 7     2
	 / \   / \							/ \   / \
	1   3 6   9						   9   6 3   1
 * Algorithm Class
 */
public class InvertBinaryTree {

	public TreeNode invertTree(TreeNode root) {
		if(root == null) return root;
		TreeNode left = invertTree(root.left);
		TreeNode right = invertTree(root.right);
		root.left = left;
		root.right = right;
		return root;
	}
	
}
