/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import williamsNotebook.common.node.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午4:43:09
* Description:
* 	Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the 
* 	path equals the given sum.
* 	  5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
	return true, as there exist a root-to-leaf path5->4->11->2which sum is 22.
*/

public class PathSum {

	// Follow Up 1: from root to leaf
	// DFS recursion Time: O(n) and extra Space is O(height);
	public boolean hasPathSum(TreeNode root, int target) {
		if(root == null) {
			return false;
		}
		// termination condition
		if(root.val == target && root.left == null && root.right == null) {
			return true;
		}
		return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
	}
	
	// Follow UP 2: Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
	public List<List<Integer>> pathSum(TreeNode root, int sum){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		traverse(root, sum, new ArrayList<Integer>(), res);
		return res;
	}
	private void traverse(TreeNode root, int sum, List<Integer> sumPath, List<List<Integer>> res) {
		if(root == null) return;
		sumPath.add(root.val);
		if(root.left == null && root.right == null && root.val == sum) {
			res.add(new ArrayList<Integer>(sumPath));
		} else {
			traverse(root.left, sum - root.val, sumPath, res);
			traverse(root.right, sum - root.val, sumPath, res);
		}
		sumPath.remove(sumPath.size() - 1);
	}
	
	// Follow Up 3: You are given a binary tree in which each node contains an integer value.
	// Find the number of paths that sum to a given value.
	// The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
	// Use HashMap<Integer, frequency>, A good way to use prefix sum
	public int pathSumII(TreeNode root, int sum) {
		HashMap<Integer, Integer> preSum = new HashMap<Integer, Integer>();
		preSum.put(0, 1);
		return helper(root, 0, sum, preSum);
	}
	public int helper(TreeNode root, int curSum, int target, HashMap<Integer, Integer> preSum) {
		if(root == null) return 0;
		curSum += root.val;
		int res = preSum.getOrDefault(curSum - target, 0);
		preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
		res += helper(root.left, curSum, target, preSum) + helper(root.right, curSum, target, preSum);
		preSum.put(curSum, preSum.get(curSum) - 1);
		return res;
	}
}
