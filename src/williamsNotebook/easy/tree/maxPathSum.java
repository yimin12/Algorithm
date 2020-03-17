package williamsNotebook.easy.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class maxPathSum {
	
	// Situation 1: Max Path Sum Binary Tree (path from leaf to root)
	// Method 1: Pass down the prefix sum
	// Time: O(n)  Space: O(height)
	public int maxPathSumStraight(TreeNode root) {
		if(root == null) throw new IllegalArgumentException("root should not be null");
		return dfsStraight(root , 0);
	}
	// return the max Straight path of binary tree
	private int dfsStraight(TreeNode root, int sum) {
		sum += root.val;
		// base case 
		if(root.left == null && root.right == null) {
			return sum;
		} else if(root.left == null) {
			return dfsStraight(root.right, sum);
		} else if(root.right == null) {
			return dfsStraight(root.left, sum);	
		}
		// if both of left and right subtree is not null
		return Math.max(dfsStraight(root.left, sum), dfsStraight(root.right, sum));
	}
	// Method 2: Bottom up return the max suffix sum
	// Time: O(n)  Space: O(height)
	public int maxPathStraightII(TreeNode root) {
		if(root == null) throw new IllegalArgumentException("root should not be null");
		if(root.left == null && root.right == null) {
			return root.val;
		} 
		if(root.left == null) {
			return maxPathStraightII(root.right) + root.val;
		}
		if(root.right == null) {
			return maxPathStraightII(root.left) + root.val;
		}
		return root.val + Math.max(maxPathStraightII(root.left), maxPathStraightII(root.right));
	}
	
	// Follow UP 1:
	// Find the maximum possible subpath sum(both the starting and ending node of the subpath should be on the 
	// same path from root to one of the leaf nodes, and the subpath is allowed to contain only one node).
	// Time: O(n)  Space: O(height)
	public int maxPathSumSubStraight(TreeNode root) {
		if(root == null) throw new IllegalArgumentException("root should not be null");
		int[] max = new int[] {Integer.MIN_VALUE};
		helperSubStright(root, max);
		return max[0];
	}
	private int helperSubStright(TreeNode root, int[] max) {
		// base case
//		显式的返回条件
//		if(root == null) {
//			return 0;
//		}
		// 以下三目算法已经包括了隐式返回条件，当left = null 时， 返回0，并不往下继续递归
		int left = root.left != null ? Math.max(helperSubStright(root.left, max),0) : 0;
		int right = root.right != null ? Math.max(helperSubStright(root.right, max),0) : 0;
		// the only difference between the follow Up 2 is the following
		int sin = root.val + Math.max(left, right);
		max[0] = Math.max(max[0], sin);
		return sin;
	}
	
	// Follow Up 2: Find the maxium possible subpath sum from any node to any node 
	// The logic is exactly the same as the Follow Up1, but return value changes
	// Time: O(n)  Space: O(height)
	public int maxPathSumAny(TreeNode root) {
		if(root == null) throw new IllegalArgumentException("root should not be null");
		int[] max = new int[] {Integer.MIN_VALUE};
		helperAny(root, max);
		return max[0];
	}
	// return the straight path sum
	private int helperAny(TreeNode root, int[] max) {
		// base case
		int left = root.left != null ? Math.max(helperAny(root.left, max), 0) : 0; 
		int right = root.right != null ? Math.max(helperAny(root.right, max), 0) : 0;
		int sin = root.val + right + left;
		max[0] = Math.max(max[0], sin);
		return root.val + Math.max(left, right);
	}
	
	// Follow Up 3:Binary Tree Path Sum to Target (The path can only be from one node to itself to any of its descendants)
	// Time: worst case O(n^2)  and space: O(height)
	public boolean exist(TreeNode root, int target) {
		if(root == null) {
			return false;
		}
		List<TreeNode> path = new ArrayList<TreeNode>();
		return dfsExist(root, path, target);
	}
	private boolean dfsExist(TreeNode root, List<TreeNode> path, int target) {
		path.add(root);
		// check whether can find a subpath ended at root node, the sum of the subpath is target
		int tmp = 0;
		for(int i = path.size() - 1; i >= 0; i--) {
			tmp += path.get(i).val;
			if(tmp == target) {
				return true;
			}
		}
		// recursion rule, return true if target is already found
		if(root.left != null && dfsExist(root.left, path, target)) {
			return true;
		}
		if(root.right != null && dfsExist(root.right, path, target)) {
			return true;
		}
		// handle the back tracking 
		path.remove(path.size() - 1);
		return false;
	}
	// Method 2: optimized the time complexity by using extra space (use the method of prefix sum)
	// Time: O(n) , Space: O(n)
	public boolean existII(TreeNode root, int target) {
		if(root == null) return false;
		Set<Integer> prefixSum = new HashSet<Integer>();
		prefixSum.add(0);
		return helperII(root, target, 0, prefixSum);
	}
	private boolean helperII(TreeNode root, int sum, int prevSum, Set<Integer> prefixSum) {
		prevSum += root.val;
		if(prefixSum.contains(prevSum - sum)) {
			return true;
		}
		// add the current prevSum to prefixSum set and set a flag to remove the
		boolean needRemove = prefixSum.add(prevSum);
		if(root.left != null && helperII(root.left, sum, prevSum, prefixSum)) {
			return true;
		}
		if(root.right != null && helperII(root.right, sum, prevSum, prefixSum)) {
			return true;
		}
		// if you add prevSum in current layer of stack, remember to remove it for back tracking
		if(needRemove) {
			prefixSum.remove(prevSum);
		}
		return false;
	}
	
	// Follow Up 4: given a binary tree, find the node with the max difference in the total number descents in its left subtree and right subtree
	// Time: O(n) , Space: O(height)
	public TreeNode maxDiffNode(TreeNode root) {
		if(root == null) return null;
		TreeNode[] nodes = new TreeNode[1];
		int[] diff = new int[1];
		diff[0] = -1;
		helperDiff(root, nodes, diff);
		return nodes[0];
	}
	// return how many nodes it current subtree
	private int helperDiff(TreeNode root, TreeNode[] nodes, int[] diff) {
		// base case 
		if(root == null) {
			return 0;
		}
		int leftNum = helperDiff(root.left, nodes, diff);
		int rightNum = helperDiff(root.right, nodes, diff);
		if(Math.abs(leftNum - rightNum) > diff[0]) {
			nodes[0] = root;
			diff[0] = Math.abs(leftNum - rightNum);
		}
		return leftNum + rightNum + 1;		
	}
}
