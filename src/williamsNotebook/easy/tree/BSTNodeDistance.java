/**
 * 
 */
package williamsNotebook.easy.tree;

import williamsNotebook.common.node.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午7:28:42
* Description:
* 	Given a list of numbers, construct a BST from it(you need to insert nodes one-by-one with the given 
* 	order to get the BST) and find the distance between two given nodes
* 	If two nodes do not appear in the BST, return -1 We guarantee that there are no duplicate nodes in BST 
* 	The node distance means the number of edges between two nodes
* input:
* 	numbers = [2,1,3] node1 = 1, node2 = 3
* output: 2	
* one edge consume 1
*/

public class BSTNodeDistance {
	
	// Analysis:
	// Step 1: construct binary search tree; Time: O(n) Space: O(height)
	// Step 2: find lowest common ancestor: Time: O(logn) Space: O(1)
	// step 3: compute the distance; Time: O(logn) Space: O(1)
	public int bstDistance(int[] numbers, int one, int two) {
		if(numbers == null || numbers.length < 2) return -1;
		TreeNode root = buildBST(numbers);
		TreeNode ancestor = LCA(root, one, two);
		int dist1 = dist(ancestor, one); // if one is not in the bst, ancestor is null
		int dist2 = dist(ancestor, two); // if two is not in the bst, ancestor is null
		if(dist1 < 0 || dist2 < 0) {
			return  -1;
		}
		return dist1 + dist2;
	}
	private TreeNode buildBST(int[] array) {
		TreeNode root = new TreeNode(array[0]);
		for(int i = 1; i < array.length; i++) {
			addTreeNode(root, array[i]);
		}
		return root;
	}
	private void addTreeNode(TreeNode root, int val) {
		if(val < root.val) {
			if(root.left == null) {
				root.left = new TreeNode(val);
			} else {
				addTreeNode(root.left, val);
			}
		} else {
			if(root.right == null) {
				root.right = new TreeNode(val);
			} else {
				addTreeNode(root.right, val);
			}
		}
	}
	// Find LCA in binary search tree
	private TreeNode LCA(TreeNode root, int one, int two) {
		if(root == null) return root;
		if(one > root.val && two > root.val) {
			return LCA(root.right, one, two);
		} else if(one < root.val && two < root.val) {
			return LCA(root.left, one, two);
		} else {
			return root;
		}
	}
	private int dist(TreeNode root, int v) {
		if(root == null) return -1;
		if(root.val == v) {
			return 0;
		}
		if(root.val < v) {
			int right = dist(root.right, v);
			if(right < 0) {
				return -1;
			}
			return right + 1;
		} else {
			int left = dist(root.left, v);
			if(left < 0) {
				return -1;
			}
			return left+1;
		}
	}
}
