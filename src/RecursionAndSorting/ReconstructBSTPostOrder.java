/**
 * 
 */
package RecursionAndSorting;

import BinaryTreeAndBST.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月21日 下午11:06:07
* Description:
* 	Given the postorder traversal sequence of a binary search tree, reconstruct the original tree..
* Assumption:
* 	The given sequence are not null and they have same length
* 	There are no duplicate keys in the binary tree
* Examples:
* 	postorder traversal = {1, 4, 3, 11, 8, 5}	
	the corresponding binary search tree is
		        5
		      /   \
		    3      8
		  /   \     \
		1      4     11

*/

public class ReconstructBSTPostOrder {
	public TreeNode reconstruct(int[] post) {
//		there is no dulicate in the binary search tree. Traversing position of the post order
//		we traverse and construct the binary search tree from the postOrder right to left
		int[] index = new int[]{post.length-1};
		return helper(post,index, Integer.MIN_VALUE);
	}
	private TreeNode helper(int[] postorder, int[] index, int min) {
//		Since it is binary search tree, the "min" is actually the root, and we are using the root
//		value to determine the boundary of left/right subtree
		if(index[0] < 0 || postorder[index[0]] <= min) {
			return null;
		}
		TreeNode root = new TreeNode(postorder[index[0]--]);
		root.right = helper(postorder, index, root.key);
		root.left =helper(postorder, index, min);
		return root;
	}
}
