/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import williamsNotebook.common.node.TreeNode;

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
	
	// Follow Up: Pre Order , In Order , Post Order, Level Order, ZigZag Order, All situation should be Time: O(n) and O(n) for storing result
	// PreOrder Iterative traversal
	public List<Integer> preOrder(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root == null) return res;
		// We use stack to simulate the process
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		while(!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			if(cur.right != null) {
				stack.offerFirst(cur.right);
			}
			if(cur.left != null) {
				stack.offerFirst(cur.left);
			}
			res.add(cur.val);
		}
		return res;
	}
	// InOrder Iterative traversal
	public List<Integer> inOrder(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root == null) return res;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !stack.isEmpty()) {
			// add all left subtree's node to the stack
			if(cur != null) {
				stack.offerFirst(cur);
				cur = cur.left;
			} else {
				// once encounter null, means it can start to print out
				cur = stack.pollFirst();
				res.add(cur.val);
				cur = cur.right;
			}
		}
		return res;
	}
	// Post Order Iterative traversal,
	// Method 1: post order is the reverse of pre-order with traversing right-subtree before traversing left tree
	public List<Integer> postOrder(TreeNode root){
		List<Integer> res = new ArrayList<Integer>();
		if(root == null) return res;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offer(root);
		// step 1: pre order traverse and start with right subtree
		while(!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
			if(cur.left != null) {
				stack.offerFirst(cur.left);
			}
			if(cur.right != null) {
				stack.offerFirst(cur.right);
			}
			res.add(cur.val);
		}
		Collections.reverse(res);
		// step 2: reverse the result
		return res;
	}
	// Method 2: Check Relation between the current node and the previous node to determine which direction should go next
	public List<Integer> postOrderII(TreeNode root){
		List<Integer> result = new ArrayList<Integer>();
		if(root == null) return result;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offer(root);
		TreeNode prev = null;
		while(!stack.isEmpty()) {
			TreeNode cur = stack.peekFirst();
			// case 1. going down
			if(prev == null || cur == prev.left || cur == prev.right) {
				if(cur.left != null) stack.offerFirst(cur.left);
				else if(cur.right != null) stack.offerFirst(cur.right);
				else {
					stack.pollFirst();
					result.add(cur.val);
				}
			} else if(prev == cur.right || prev == cur.left && cur.right == null) {
				// case 2: going up from right subtree or going up from left sub and the right sub is null
				stack.pollFirst();
				result.add(cur.val);
			} else {
				// case 3: we are going up from the left side and we can go down right side.
				stack.offerFirst(cur.right);
			}
		}
		return result;
	}
	// Level Order : Looking from top to bottom,bfs
	public List<List<Integer>> levelOrder(TreeNode root){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(root == null) return res;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offerFirst(root);
		while(!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> curRes = new ArrayList<Integer>();
			for(int i = 0; i < size; i++) {
				TreeNode cur = queue.pollLast();
				if(cur.left != null) {
					queue.offerFirst(cur.left);
				}
				if(cur.right != null) {
					queue.offerFirst(cur.right);
				}
				curRes.add(cur.val);
			}
			res.add(curRes);
		}
		return res;
	}
	// Level Order: dfs Time: O(n) Extra Space: O(height)
	public List<List<Integer>> levelOrderII(TreeNode root){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		levelOrderDFS(root, 0, res);
		return res;
	}
	private void levelOrderDFS(TreeNode root, int depth, List<List<Integer>> res) {
		if(root == null) return;
		if(depth == res.size()) {
			res.add(new ArrayList<Integer>());
		}
		res.get(depth).add(root.val);
		levelOrderDFS(root.left,depth+1,res);
		levelOrderDFS(root.right, depth+1, res);
	}
	// ZigZag : Assume that we print it from left to right at odd level and print it from right to left in 
	public List<List<Integer>> zigzagOrder(TreeNode root){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(root == null) return res;
		Deque<TreeNode> deque = new LinkedList<TreeNode>();
		deque.offerFirst(root);
		int layer = 0;
		while(!deque.isEmpty()) {
			int size = deque.size();
			List<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < size; i++) {
				if(layer == 0) {
					// at even layer, from right to left
					TreeNode temp = deque.pollLast();
					list.add(temp.val);
					if(temp.right != null) {
						deque.offerFirst(temp.right);
					}
					if(temp.left != null) {
						deque.offerFirst(temp.left);
					}
				} else {
					// at odd layer, from left to right.
					TreeNode temp = deque.pollFirst();
					list.add(temp.val);
					if(temp.right != null) {
						deque.offerFirst(temp.right);
					} 
					if(temp.left != null) {
						deque.offerFirst(temp.left);
					}
				}
			}
			layer = 1 - layer;
			res.add(list);
		}
		return res;
	}
	// Vertical View : Given a binary tree, return the vertical order traversal of its nodes' values. 
	// (ie, from top to bottom, column by column). If two nodes are in the same row and column, the order should be from left to right.
	//	Input:  [3,9,20,null,null,15,7]
//			   3
//			  /\
//			 /  \
//			 9  20
//			    /\
//			   /  \
//			  15   7 
//
//
//			Output:
//
//
//			[
//			  [9],
//			  [3,15],
//			  [20],
//			  [7]
//			]
	// Key Insight: The structure should be similar with Pascal Triangle
	// Time: O(n)  Space : Extra Space for (3*n)
	public List<List<Integer>> verticalOrder(TreeNode root){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(root == null) return res;
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> cols = new LinkedList<Integer>();
		queue.add(root);
		cols.add(0);
		// min record the left boundary and max record the right boundary
		int min = 0, max = 0;
		while(!queue.isEmpty()) {
			TreeNode node = queue.poll();
			int col = cols.poll();
			if(!map.containsKey(col)) {
				map.put(col, new ArrayList<Integer>());
			}
			map.get(col).add(node.val);
			if(node.left != null) {
				queue.add(node.left);
				cols.add(col - 1);
				min = Math.min(min, col -1);
			}
			if(node.right != null) {
				queue.add(node.right);
				cols.add(col + 1);
				max = Math.max(max,  col + 1);
			}
		}
		for(int i = min; i <= max; i++) {
			res.add(map.get(i));
		}
		return res;
	}
	public static void main(String[] args) {
		ViewTree solution = new ViewTree();
		TreeNode one = new TreeNode(3);
		TreeNode two = new TreeNode(9);
		TreeNode three = new TreeNode(20);
		TreeNode four = new TreeNode(15);
		TreeNode five = new TreeNode(7);
		one.left = two;
		one.right = three;
		three.left = four;
		three.right = five;
		List<List<Integer>> levelOrder = solution.zigzagOrder(one);
		for(List<Integer> list: levelOrder) {
			System.out.println(list.toString());
		}
	}
}
