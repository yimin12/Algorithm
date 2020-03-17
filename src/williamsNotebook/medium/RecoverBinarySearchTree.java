/**
 * 
 */
package williamsNotebook.medium;

import java.util.Deque;
import java.util.LinkedList;
import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *	
 *		Two elements of a binary search tree (BST) are swapped by mistake.
 *		Recover the tree without changing its structure.
 *		A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 *
 * Algorithm Class
 */
public class RecoverBinarySearchTree {

	// Method 1: key insights: Inorder Traversal of BST yields an ascending order, Time ~ O(N), Space ~ O(N) Not constant space!
	private Deque<TreeNode> q = new LinkedList<TreeNode>(); // store the inorder traversal result in a queue, and find the wrong pair and swap
	public void recoverTree(TreeNode root) {
		inorder(root);
		TreeNode first = null, second = null;
		if(!q.isEmpty()) {
			TreeNode prev = q.poll();
			while(!q.isEmpty()) {
				TreeNode cur = q.poll();
				if(prev.val > cur.val) {
					if(first == null) {
						first = prev;
						second = cur;
					} else {
						second = cur;
						break;
					}
				}
				prev = cur;
			}
		}
		swap(first, second);
	}
	private void inorder(TreeNode root) {
		if(root == null) return;
		Deque<TreeNode> shuffle = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !shuffle.isEmpty()) {
			if(cur != null) {
				shuffle.offerFirst(cur);
				cur = cur.left;
			} else {
				q.offerLast(shuffle.pollFirst());
				shuffle.offerFirst(cur.right);
			}
		}
	}
	private void swap(TreeNode first, TreeNode second) {
		if(first == null || second == null) return;
		int temp = first.val;
		first.val = second.val;
		second.val = temp;
	}
	
	// Method 2: swap the value while the recursion, Time ~ O(N), Space ~ O(N) Not constant space!
	TreeNode first = null, second = null;
	private void recoverTreeI(TreeNode root) {
		inorderI(root);
		swap(first, second);
	}
	private void inorderI(TreeNode root) {
		// base case
		if(root == null) return;
		if(root.left != null) inorderI(root.left);
		if(!q.isEmpty()) {
			if(q.peekFirst().val > root.val) {
				if(first == null) {
					first = q.peekFirst();
					second = root;
				} else {
					second = root;
				}
			}
		}
		q.offerFirst(root);
		if(root.right != null) inorderI(root.right);
	}
	// Method 3: Morris Inorder Traversal, Time ~ O(N), Space ~ O(1)
	public void recoverTreeMirror(TreeNode root) {
		TreeNode prev = null, cur = root, first = null, second = null;
		while(cur != null) {
			if(cur.left != null) {
				TreeNode p = cur.left;
				while(p.right != null && p.right != cur) {
					p = p.right;
				}
				if(p.right == null) {
					p.right = cur;
					cur = cur.left;
				} else {
					p.right = null;
					if(prev.val > cur.val) {
						if(first == null) {
							first = prev;
							second = cur;
						}
					}
				}
			} else {
				 if (prev != null && prev.val > cur.val) {
		                if (first == null)  first = prev;
		                second = cur;
		            }
	            prev = cur;
	            cur = cur.right;
			}
		}
		 swap(first, second);
	}
	
}
