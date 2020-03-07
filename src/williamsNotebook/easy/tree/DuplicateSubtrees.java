/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *	
 *	Give a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *	Two trees are duplicate if they have the same structure with same node values.
 *	Example:		the following are two duplicate subtrees   2 and 4.
 *			1												  /
 *		   / \												 4
 *		  2   3
 *		 /   / \
 *		4	2	4
 *		   /
 *		  4
 * Algorithm Class
 */
// Have problem here
public class DuplicateSubtrees {

	// Solution 1: naive DFS
	// step 1: Collect all tree nodes
	// step 2: Check whether each pair of nodes have the same structure.
	public List<TreeNode> duplicateDFS(TreeNode root){
		List<TreeNode> res = new ArrayList<TreeNode>();
		if(root == null) return res;
		helperDFS(root, res);
		return res;
	}
	private void helperDFS(TreeNode root, List<TreeNode> res) {
		if(root == null) return;
		
	}
	
	// Solution 2 -- serialization
	// step 1: Serialize each node in post order
	// step 2: Count the nodes that have the same encoding
	// Time: O(n^2) and Extra Space: O(n^2)
	public List<TreeNode> findDuplicateSubtrees(TreeNode root){
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<TreeNode> res = new ArrayList<TreeNode>();
		if(root == null) return res;
		StringBuilder sb = new StringBuilder();
		serialize(root, map, res, sb);
		return res;
	}
	private String serialize(TreeNode root, Map<String, Integer> map, List<TreeNode> res, StringBuilder sb) {
		// base case
		if(root == null) return "#";
		sb.append(root.val).append(",");
		serialize(root.left, map, res, sb);
		serialize(root.right, map, res, sb);
		int count = map.getOrDefault(sb.toString(), 0);
		if(++count == 2) {
			res.add(root);
		}
		return sb.toString();	
	}
	
	// Solution 3: Global Value Numbering
	// Time: O(n) and Space: extra space O(n)
	
	
	public static void main(String[] args) {
		DuplicateSubtrees solution = new DuplicateSubtrees();
		TreeNode one = new TreeNode(1);
		TreeNode two = new TreeNode(2);
		TreeNode three = new TreeNode(3);
		TreeNode four = new TreeNode(4);
		TreeNode five = new TreeNode(2);
		TreeNode six = new TreeNode(4);
		TreeNode seven = new TreeNode(4);
		one.left = two;
		one.right = three;
		two.left = four;
		three.left = five;
		three.right = six;
		five.left = seven;
		List<TreeNode> findDuplicateSubtrees = solution.findDuplicateSubtrees(one);
		System.out.println(findDuplicateSubtrees.toString());
	}
}
