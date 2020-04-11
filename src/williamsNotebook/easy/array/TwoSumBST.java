/**
 * 
 */
package williamsNotebook.easy.array;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import williamsNotebook.common.node.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月8日 下午3:53:36
* Description:
* 	Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that 
* 	their sum is equal to the given target.
* Example:
* 	Input:		Target = 9     Output: True
	    5 
	   / \
	  3   6
	 / \   \
	2   4   7
* 
*/
public class TwoSumBST {

	// DFS and HashSet
	// Time: O(n) and Space: O(height)
	public boolean findTarget(TreeNode root, int target) {
		if(root == null) return false;
		Set<Integer> set = new HashSet<Integer>();
		return find(root, target, set);
	}
	public boolean find(TreeNode root, int target, Set<Integer> set) {
		if(root == null) return false;
		if(set.contains(target - root.val)) return true;
		set.add(root.val);
		return find(root.left, target, set) || find(root.right, target, set);
	}
	// BFS and HashSet
	public boolean findTargetBFS(TreeNode root, int target) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Set<Integer> set = new HashSet<Integer>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			if(node.left != null) {
				queue.offer(node.left);
			}
			if(node.right != null) {
				queue.offer(node.right);
			}
			if(set.contains(target - node.val)) {
				return true;
			}
			set.add(node.val);
		}
		return false;
	}
}
