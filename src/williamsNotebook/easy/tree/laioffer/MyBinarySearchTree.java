/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

import williamsNotebook.easy.linkedL.laioffer.ListNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��11��4�� ����9:23:30
* Description:
* 	implement the basic function of binary search tree
*/
public class MyBinarySearchTree {
	static class TreeNode{
		int key;
		TreeNode left;
		TreeNode right;
		public TreeNode(int key) {
			this.key = key;
		}
	}
	public TreeNode searchRecursive(TreeNode root, int target) {
//		1. process root
		if(root == null || root.key == target) {
			return root;
		}
//		2.check left node if target less the root.key
		if(target < root.key) {
			return searchRecursive(root.left, target);
		}
//		3.check right node if target greater then root.key
		else {
			return searchRecursive(root.right, target);
		}
	}
	public TreeNode searchIterative(TreeNode root, int target) {
		TreeNode currentNode = root;
		while(currentNode!= null && currentNode.key != target) {
			if(target < currentNode.key) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
//		exit while loop, currentNode == null || currentNode.key == target
		return currentNode;
	}
	public TreeNode insertRecursive(TreeNode root, int key) {
		if(root == null) {
			TreeNode newRoot = new TreeNode(key);
			return newRoot;
		}
		if(root.key < key) {
			root.right = insertRecursive(root.right, key);
		} else if(root.key > key) {
			root.left = insertRecursive(root.left, key);
		}
		return root;
	}
	public TreeNode insertIterative(TreeNode root, int target) {
		TreeNode newNode = new TreeNode(target);
		if(root == null) {
			return newNode;
		}
		TreeNode current = root;
		while(current.key != target) {
			if(current.key > target) {
				if(current.left!=null) {
					current = current.left;
				} else {
					current.left.left = newNode;
					break;
				}
			} else {
				if(current.right != null) {
					current = current.right;
				} else {
					current.right = newNode;
					break;
				}
			}
		}
		return root;
	}
	public TreeNode insert(TreeNode root, int target) {
		if(root == null) {
			return new TreeNode(target);
		}
		TreeNode returnRoot = root;
		TreeNode pre = null;
		while(root != null) {
			pre = root;
			if(root.key <target) {
				root = root.right;
			} else if(root.key > target) {
				root = root.left;
			} else {
				return returnRoot;
			}
		}
		if(pre.key < target) {
			pre.right = new TreeNode(target);
		} else if(pre.key > target) {
			pre.left = new TreeNode(target);
		}
		return returnRoot;
	}
	
	
}
