/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.Deque;
import java.util.LinkedList;

import williamsNotebook.common.node.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午4:22:39
* Description:
* 	Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
* 
*       1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
*/

public class SymmetricTree {

	// Time complexity :O(n). Because we traverse the entire input tree once, the total run time is O(n), where n is the total number of nodes in the tree.
	// Space complexity : There is additional space required for the search queue. In the worst case, we have to insert O(n) nodes in the queue. Therefore, space complexity is O(n).
	public boolean isSymmetric(TreeNode root) {
		return null == null || isMirror(root.left, root.right);
	}
	private boolean isMirror(TreeNode left, TreeNode right) {
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		if(left.val != right.val) return false;
		return isMirror(left.left, right.right) && isMirror(left.right, right.left);
	}
	// Method 2: Iteratives way
	public boolean isSymmetricIterative(TreeNode root) {
		if(root == null) return true;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root.left);
		queue.offer(root.right);
		while(!queue.isEmpty()) {
			TreeNode n1 = queue.poll();
			TreeNode n2 = queue.poll();
			if(n1 == null && n2 == null) continue;
			if(n1 == null || n2 == null) return false;
			if(n1.val != n2.val) return false;
			queue.offer(n1.left);
			queue.offer(n2.right);
			queue.offer(n1.right);
			queue.offer(n2.left);
		}
		return true;
	}
	
	// Follow Up : Is Same Tree, Given two binary trees, write a function to check if they are the same or not.
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	
}
