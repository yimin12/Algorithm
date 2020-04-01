/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.ArrayList;
import java.util.List;

import williamsNotebook.common.node.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午5:08:31
* Description:
* 	Given a binary search tree, write a functionkthSmallestto find the kth smallest element in it.
* 	You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
* 	In order traversal of BST actually returns the element in ascending order, thus intuitively, 
* 	traverse the BST with in-order, and return the kth element in the result, would be the kth smallest element in a BST.
* 	Input:	root = [3,1,4,null,2], k = 1  Output:  1
* 	3
  / \
 1   4
  \
   2
*/

public class KthSmallestBST {

	// Key Insight :
	// In order traversal of BST actually returns the element in ascending order, thus intuitively, traverse the BST 
	// with in-order, and return the kth element in the result, would be the kth smallest element in a BST.
	public int kthSmallest(TreeNode root, int k) {
		if(root == null) return 0;
		List<Integer> topK = new ArrayList<Integer>();
		helper(root, topK, k);
		return topK.get(k-1);
	}
	private void helper(TreeNode root, List<Integer> list, int k) {
		// base case 
		if(root == null) return;
		// recursion rule: inorder traverse
		helper(root.left, list, k);
		if(list.size() < k) {
			list.add(root.val);
		} else {
			return;
		}
		helper(root.right, list, k);
	}
}
