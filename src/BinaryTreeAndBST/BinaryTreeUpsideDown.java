/**
 * 
 */
package BinaryTreeAndBST;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月23日 下午2:14:50
* Description:
* 	Given a binary tree where all the right nodes are leaf nodes, flip it upside 
* 	down and turn it into a tree with left leaf nodes as the root.
* Examples:
		         1            is reversed to 		3
		      /    \							 /     \
		    2        5						    2       4
		  /   \								  /   \
		3      4							1      5
*/

public class BinaryTreeUpsideDown {
//	Method 1: Recursion
	public TreeNode reverse(TreeNode root) {
		if(root == null || root.left == null) {
			return root;
		}
		TreeNode newRoot = reverse(root.left);
		root.left.right = root.right;
		root.left.left = root;
		root.left = null;
		root.right = null;
		return newRoot;
	}
//	Method 2: Iterative
	public TreeNode reverseI(TreeNode root) {
		TreeNode prev = null;
		TreeNode prevRight = null;
		while(root != null) {
			TreeNode next = root.left;
			TreeNode right = root.right;
			root.right = prevRight;
			root.left = prev;
			prevRight = right;
			prev = root;
			root = next;
		}
		return prev; 
	}
}
