/**
 * 
 */
package williamsNotebook.medium;

import java.util.HashMap;
import java.util.Map;

import williamsNotebook.common.node.ListNode;
import williamsNotebook.common.node.TreeNode;

/**
 * @author yimin Huang
 * 		
 * 		Given preorder and inorder traversal of a tree, construct the binary tree.
 * 		Note: You may assume that duplicates do not exist in the tree.
 * 
 * Algorithm Class
 */
public class ReconstructBinaryTree {
	
	// Given InOrder and PreOrder sequence
	// Recursion: Time ~ O(N), Space ~ O(logN)
	private Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
	public TreeNode constructInPre(int[] InOrder, int[] PreOrder) {
		int len = InOrder.length;
		if(len < 1) return null;
		// construct the inOrderMap first
		for(int i = 0; i < len; i++) {
			inorderMap.put(InOrder[i], i);
		}
		return constructInPre(PreOrder, 0, 0, len - 1);
	}
	private TreeNode constructInPre(int[] preOrder, int cur, int start, int end) {
		TreeNode root = new TreeNode(preOrder[cur]);
		if(start < end) {
			int mid = inorderMap.get(preOrder[cur]);
			if(mid > start) {
				root.left = constructInPre(preOrder, cur+1, start, mid - 1);
			} 
			if(mid < end) {
				root.right = constructInPre(preOrder, cur + mid - start + 1, mid + 1, end);
			}
		}
		return root;
	}
	// Follow Up 1: Given inorder and postorder traversal of a tree, construct the binary tree.
	// Recursion: Time ~ O(N), Space ~ O(logN)
	public TreeNode constructInPost(int[] InOrder, int[] PostOrder) {
		int len = InOrder.length;
		if(len < 1) return null;
		// construct the inOrderMap first
		for(int i = 0; i < len; i++) {
			inorderMap.put(InOrder[i], i);
		}
		// comparing with the situation 1, it starts from the last element
		return constructInPost(PostOrder, len - 1, 0, len - 1);
	}
	private TreeNode constructInPost(int[] postOrder, int cur, int start, int end) {
		TreeNode root = new TreeNode(postOrder[cur]);
		if(start < end) {
			int mid = inorderMap.get(postOrder[cur]);
			if(mid > start) root.left = constructInPost(postOrder, cur-(end-mid)-1, start, mid - 1);
			if(mid < end) root.right = constructInPost(postOrder, cur - 1, mid + 1, end);
		}
		return root;
	}
	
	// Follow Up 2: Convert Sorted Array to Binary Search Tree
	// Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
	// Recursion: Time ~ O(N), Space ~ O(logN)
	public TreeNode sortedArrayBST(int[] array) {
		if(array == null || array.length == 0) return null;
		return dfsSortedArrayToBST(array, 0, array.length - 1);
	}
	private TreeNode dfsSortedArrayToBST(int[] array, int start, int end) {
		if(start > end) return null;
		// pick the mid value from the ascending sorted array and use it as root
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(array[mid]);
		root.left = dfsSortedArrayToBST(array, start, mid - 1);
		root.right = dfsSortedArrayToBST(array, mid+1, end);
		return root;
	}
	
	// Follow Up 3: Convert Sorted List to Binary Search Tree
	// Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
	// Recursion: Time ~ O(N), Space ~ O(logN)
	// Bottom-up approach: Since we use linked list here, top-down approach will result in O(N^2) Time.
	// should create global variable, because java is parsing by value
	ListNode list;
	public TreeNode sortedListToBST(ListNode head) {
		
		int n = 0;
		ListNode p = head;
		while(p != null) {
			n++;
			p = p.next;
		}
		list = head;
		return sortedListToBST(0, n - 1);
	}
	private TreeNode sortedListToBST(int start, int end) {
		if(start > end) return null;
		int mid = start + (end - start)/2;
		TreeNode leftChild = sortedListToBST(start, mid - 1);
		TreeNode parent = new TreeNode(list.key);
		parent.left = leftChild;
		list = list.next;
		parent.right = sortedListToBST(mid+1, end);
		return parent;
	}
}
