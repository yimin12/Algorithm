/**
 * 
 */
package williamsNotebook.easy.tree;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午3:43:39
* Description:
* 	Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set toNULL.
* 	Initially, all next pointers are set toNULL.
*    1
   /  \
  2    3
 / \  / \
4  5  6  7
     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
* 
*/

public class PopulatingNextRightPointers {

	static class TreeLinkNode{
		int val;
		TreeLinkNode left, right, next;
		public TreeLinkNode(int _val) {
			val = _val;
		}
	}
	// Follow Up 1: You may only use constant extra space.
	// You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
	// Use Level Start TreeLinkNode O(1) space and O(n) Time
	public void connectIterative(TreeLinkNode root) {
		if(root == null) return;
		TreeLinkNode levelStart = root;
		while(levelStart != null) {
			TreeLinkNode cur = levelStart;
			while(cur != null) {
				if(cur.left != null) cur.left.next = cur.right; // no matter if right is null
				if(cur.right != null && cur.next != null) cur.right.next = cur.next.left;
				cur = cur.next;
			}
			levelStart = levelStart.left;
		}
	}
	// Method 2: Use Recursion
	public void connectRecursion(TreeLinkNode root) {
		if(root == null) return;
		connectHelper(root.left, root.right);
	}
	private void connectHelper(TreeLinkNode one, TreeLinkNode two) {
		if(one == null || two == null) return; // because this is perfect balanced binary tree;
		one.next = two;
		connectHelper(one.left, one.right);
		connectHelper(one.right, two.left);
		connectHelper(two.left, two.right);
	}
	
	// Follow Up 2: What if the given tree is not perfect?
//	     1
//	   /  \
//	  2    3
//	 / \    \
//	4   5    7
//	     1 -> NULL
//	   /  \
//	  2 -> 3 -> NULL
//	 / \    \
//	4-> 5 -> 7 -> NULL
	// Method 1: Level Order traversal by using dummy head
	// Iterative - O(n) time, O(1) space
	public void connectIterativeII(TreeLinkNode root) {
		if(root == null) return;
		while(root != null) {
			TreeLinkNode tempChild = new TreeLinkNode(0); // dummy head
			TreeLinkNode currentChild = tempChild;
			while(root != null) {
				if(root.left != null) {
					currentChild.next = root.left;
					currentChild = currentChild.next;
				}
				if(root.right != null) {
					currentChild.next = root.right;
					currentChild = currentChild.next;
				}
				root = root.next;
			}
			root = tempChild.next;
		}
	}
	// Method 2:
	public void connectRecursionII(TreeLinkNode root) {
		// base case
		if(root == null) return;
		TreeLinkNode dummy = new TreeLinkNode(0);
		// Each level is connected by dummy
		for(TreeLinkNode cur = root, prev = dummy; cur != null; cur = cur.next) {
			if(cur.left != null) {
				prev.next = cur.left;
				prev = prev.next;
			}
			if(cur.right != null) {
				prev.next = cur.right;
				prev = prev.next;
			}
		}
		connectRecursionII(dummy.next);
	}
}
