/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *
 *	Given a binary tree, imagine yourself standing on the different side of it, return values of the nodes you can see ordered from top to bottom.
 *	Examples:  	the right view =  [1, 3, 7, 8, 11]
           1
        /    \
       2      3
      / \    /  \
     4   5   6  7
    /            \
    9             8
  /  \
 10  11
 * Algorithm Class
 */
public class ViewTree {

	// Solution 1: use dfs, traverse all node , Time: O(n) and extra space for call stack O(height)
	public List<Integer> rightViewDFS(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root == null) return res;
		dfsRightView(root, res, 0);
		return res;
	}
	private void dfsRightView(TreeNode root, List<Integer> res, int height) {
		// base case
		if(root == null) {
			return;
		}
		if(height == res.size()) {
			// meet the first node in current layer
			res.add(root.val);
		}
		dfsRightView(root.right,res,height+1);
		dfsRightView(root.left, res, height+1);
		// we must not delete the element from current layer like the other back tracking did before.
		// we only care about who I saw first in current layer
	}
	// Solution 2: use bfs, use priority to record the information of each layer
	// Time: O(n) and extra space O(n)
	public List<Integer> rightViewBFS(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root == null) return res;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				// check before adding new element in new layer
				if(i == size - 1) {
					res.add(cur.val);
				}
				if(cur.left != null) {
					queue.offer(cur.left);
				}
				if(cur.right != null) {
					queue.offer(cur.right);
				}
			}
		}
		return res;
	}
	
	// Symmetric problem, left view with bfs and dfs
	
	// Follow Up: Pre Order , In Order , Post Order, Level Order, ZigZag Order
}
