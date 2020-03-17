/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *		
 *		Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *		Calling next() will return the next smallest number in the BST.
 *		next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *		
 * Algorithm Class
 */
public class BSTIterator {

	
	// Time ~ O(1), Space ~ O(logN)
	// print it with in order traversal
	public Deque<TreeNode> stack = new LinkedList<TreeNode>();
	public BSTIterator(TreeNode root) {
		pushLeftChildren(root);
	}
	// push all the left subnodes to stack until reaching the first node in inorder (the leftmost node)
	private void pushLeftChildren(TreeNode curr) {
		while(curr != null) {
			stack.offerLast(curr);
			curr = curr.left;
		}
	}
	// whether we have a next smallest number
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	public int next() {
		if(!hasNext()) throw new NoSuchElementException("All nodes has been polled out");
		TreeNode res = stack.pollLast();
		if(res.right != null) stack.offerLast(res.right);
		if(res.left != null) stack.offerLast(res.left);
		return res.val;
 	}
	
	// Preorder: root-left-right
	// Time ~ O(1), Space ~ O(logN)
	public void BSTIteratorPretOrder(TreeNode root) {
		if(root != null) stack.offerLast(root); // find the first node (zigzag down to bottom) to start, and store the trace
	}
	private int nextPre() {
		if(!hasNext()) throw new NoSuchElementException("All nodes has been polled out");
		TreeNode res = stack.pollLast();
		if(res.right != null) stack.offerLast(res.right);
		if(res.left != null) stack.offerLast(res.left);
		return res.val;
	}
	
	// PostOrder: left - right - root
	public void BSTIteratorPostOrder(TreeNode root) {
		findNextLeaf(root); // fint the first node (zigzag down to bottom) to start, and store the trace
	}
	private void findNextLeaf(TreeNode cur) {
		while(cur != null) {
			stack.offerLast(cur);
			if(cur.left != null) cur = cur.left;
			else cur = cur.right;
		}
	}
	public int nextPostOrder() {
		if(!hasNext()) throw new NoSuchElementException("All nodes has been polled out");
		TreeNode res = stack.pollLast();
		if(!stack.isEmpty()) {
			TreeNode parent = stack.peekLast();
			if(res == parent.left) {
				findNextLeaf(parent.right);
			}
		}
		return res.val;
	}
}
