/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import williamsNotebook.common.node.KnaryTreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午2:34:55
* Description:
* 	Given an n-ary tree, return thepreordertraversal of its nodes' values.
* 
*/

public class KnaryTreeTraverse {

	// Follow 1: PreOrder Traversal
	// Method 1: Recursive-DFS
	// Time: O(n)  Space: O(height)
	public List<Integer> preOrderDFS(KnaryTreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root == null) return res;
		preOrderHelper(root, res);
		return res;
	}
	private void preOrderHelper(KnaryTreeNode root, List<Integer> res) {
		if(root == null) {
			return;
		}
		res.add(root.val);
		for(KnaryTreeNode node : root.children) {
			preOrderHelper(node, res);
		}
	}
	// Method 2: Iterative 
	public List<Integer> preOrderIterative(KnaryTreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root == null) {
			return res;
		}
		Deque<KnaryTreeNode> stack = new ArrayDeque<KnaryTreeNode>();
		stack.push(root);
		while(!stack.isEmpty()) {
			KnaryTreeNode node = stack.pop();
			res.add(node.val);
			if(node.children != null) {
				Collections.reverse(node.children);
				for(KnaryTreeNode c : node.children) {
					stack.push(c);
				}
			}
		}
		return res;
	}
	
	// Follow Up 2: Post Order Traverse;
	// Method 1:  Recursive-DFS
	// Time: O(n)  Space: O(height)
	public List<Integer> postOrderDFS(KnaryTreeNode root){
		List<Integer> res = new LinkedList<Integer>();
		if(root == null) return res;
		postOrderHelper(root, res);
		return res;
	}
	private void postOrderHelper(KnaryTreeNode root, List<Integer> res) {
		if(root == null) return;
		if(root.children != null) {
			for(KnaryTreeNode child : root.children) {
				postOrderHelper(child, res);
			}
		}
		res.add(root.val);
	}
	// Method 2: Iterative 
	// Key Insight: postOrder is reverse manipulation of preOrder with slightly difference
	// step 1: preOrder push stack
	// step 2: reverse the result
	// Time: O(n)  Space: O(n) for stack
	public List<Integer> postOrderIterative(KnaryTreeNode root){
		List<Integer> res = new LinkedList<Integer>();
		if(root == null) return res;
		LinkedList<KnaryTreeNode> stack = new LinkedList<KnaryTreeNode>();
		stack.push(root);
		while(!stack.isEmpty()) {
			KnaryTreeNode node = stack.pop();
			res.add(0, node.val);
			if(node.children != null) {
				for(KnaryTreeNode c : node.children) {
					if(c != null) stack.push(c);
				}
			}
		}
		return res;
	}
	
	// Follow Up 3: Level Order Traverse
	// Use BFS
	public List<List<Integer>> levelOrder(KnaryTreeNode root){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(root == null) {
			return res;
		}
		Queue<KnaryTreeNode> queue = new LinkedList<KnaryTreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			int levelSize = queue.size();
			ArrayList<Integer> level = new ArrayList<Integer>();
 			for(int i = 0; i < levelSize; i++) {
 				KnaryTreeNode node = queue.poll();
 				if(node.children != null) {
 					for(KnaryTreeNode c : node.children) {
 						queue.offer(c);
 					}
 				}
 				level.add(node.val);
 			}
 			res.add(level);
		}
		return res;
	}
}
