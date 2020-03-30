package williamsNotebook.easy.tree;

import williamsNotebook.common.node.TreeNode;

/**
 * @author yimin Huang
 *		
 *		Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *		An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *		Find the total sum of all root-to-leaf numbers.
 *		For example,
 *		1
   	   / \
      2   3
      The root-to-leaf path 1->2 represents the number 12.
      The root-to-leaf path 1->3 represents the number 13.
	  Return the sum = 12 + 13 = 25.
	  
 * Algorithm Class
 */
public class SumRootToLeaf {
	
	public int sumNumbers(TreeNode root) {
		return dfs(root, 0);
	}
	private int dfs(TreeNode root, int sum) {
		if(root == null) return 0;
		sum = sum * 10 + root.val;
		if(root.left == null && root.left == null) return sum;
		return dfs(root.left, sum) + dfs(root.right, sum);
	}
	
	public static void main(String[] args) {
		SumRootToLeaf solution = new SumRootToLeaf();
		TreeNode root = new TreeNode(1);
		TreeNode one = new TreeNode(2);
		TreeNode two = new TreeNode(3);
		root.left = one;
		root.right = two;
		
		int sumNumbers = solution.sumNumbers(root);
		System.out.println(sumNumbers);
	}
}
