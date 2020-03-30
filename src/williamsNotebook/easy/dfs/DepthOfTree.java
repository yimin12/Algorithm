/**
 * 
 */
package williamsNotebook.easy.dfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import williamsNotebook.common.node.TreeNode;

/**
 * @author yimin Huang
 *
 *	Given a binary tree, find its maximum depth
 *	The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node
 *	
 * Algorithm Class
 */
public class DepthOfTree {

	// Situation 1: Get the hight of tree
	// Time ~ O(N), Space ~ O(height)
	public int getHeight(TreeNode root) {
		if(root == null) return 0;
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}
	// Method 2: Simulate DFS to get Height By Stack
	public int getHeightDFS(TreeNode root) {
		if(root == null) return 0;
		Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
		Deque<Integer> value = new ArrayDeque<Integer>();
		stack.push(root);
		value.push(1); // initial height
		int max = 0;
		while(!stack.isEmpty()) {
			TreeNode node = stack.pop();
			int temp = value.pop();
			max = Math.max(temp,  max);
			if(node.left != null) {
				stack.push(node.left);
				value.push(temp + 1);
			} 
			if(node.right != null) {
				stack.push(node.right);
				value.push(temp + 1);
			}
		}
		return max;
	}
	// Method 3: BFS
	public int getHeightBFS(TreeNode root) {
		if(root == null) {
			return 0;
		}
		Deque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);
		int count = 0; // record the height
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				TreeNode node = queue.poll();
				if(node.left != null) {
					queue.offer(node.left);
				}
				if(node.right != null) {
					queue.offer(node.right);
				}
			}
			count++;
		}
		return count;
	}
	// Follow Up 2: Get the minimum depth of given binary tree
	// Time ~ O(N), Space ~ O(height) by DFS
	// 注意minDepth与maxDepth的区别，当仅有一边子树为空时，minDepth是另一边子树的depth。
	public int minDepthDFS(TreeNode root) {
		if(root == null) return 0;
		else if(root.left == null) return minDepthDFS(root.right) + 1;
		else if(root.right == null) return minDepthDFS(root.left) + 1;
		return Math.min(minDepthDFS(root.left), minDepthDFS(root.right)) + 1;
	}
	// Method 2: BFS
	// Time ~ O(N), Space ~ O(height)
	public int minDepthBFS(TreeNode root) {
		if(root == null) return 0;
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		TreeNode rightmost = root;
		int depth = 1;
		while(!queue.isEmpty()) {
			TreeNode temp = queue.poll();
			// pruning, when you first find leaf, that is the minDepth
			if(temp.left == null && temp.right == null) break;
			if(temp.left != null) queue.offer(temp.left);
			if(temp.right != null) queue.offer(temp.right);
			if(temp == rightmost) {
				depth++;
				rightmost = (temp.right != null) ? temp.right : temp.left;
			}
		}
		return depth;
	}
}
