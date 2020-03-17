/**
 * 
 */
package williamsNotebook.medium;

/**
 * @author yimin Huang
 * 	
 *	You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. 
 *	The binary tree has the following definition:
 *	Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Algorithm Class
 */
public class PopulatingNextRightPointer {
	
	static class Node{
		int val;
		Node left;
		Node right;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	public Node connect(Node root) {
		if(root == null) return root;
		Node leftmost = root;
		while(leftmost.left != null) {
			Node head = leftmost;
			while(head != null) {
				head.left.next = head.right;
				if(head.next!= null) {
					head.right.next = head.next.left;
				}
				head = head.next;
			}
			leftmost = leftmost.left;
		}
		return root;
	}
	
	// What if the given tree could be any binary tree. Use dfs to solve the problem node by node
	public void connectDFS(Node root) {
		if(root == null) return;
		Node dummy = new Node(0);
		Node prev = dummy, cur = root;
		while(cur != null) {
			if(cur.left != null) {
				prev.next = cur.left;
				prev = prev.next;
			}
			if(cur.right != null) {
				prev.next = cur.right;
				prev = prev.next;
			}
			cur = cur.next;
		}
		connect(dummy.next);
	}

}
