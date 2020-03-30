/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.List;

import williamsNotebook.common.node.TreeNode;

/**
 * @author yimin Huang
 *	
 *		Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *		For example,
 *		Given n = 3, there are a total of 5 unique BST's
 * 		1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
 *
 * Algorithm Class
 */
public class UniqueBinarySearchTree {

	//	https://leetcode.com/problems/unique-binary-search-trees/discuss/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i)
	// Solution 1: dynamic programming, logic is similar with cutting rope
	// dp[i] represent that there three nodes in the subtree.
	// base case : dp[0] = 1, dp[1] = 1, dp[2] = dp[0]dp[1] + dp[1]dp[0], dp[3] = dp[0]dp[2] + dp[1]dp[1] + dp[2]dp[0]
	// induction rule: dp[i] = sum(dp[k]dp[i-k-1]) where k = 0 to (i-1)
	// The essential process is: to build a tree, we need to pick a root node, then we need to know how many possible
	// left sub trees and right sub trees can be held under that node, finally multiply them.
	// To build a tree contains {1,2,3,4,5}. First we pick 1 as root, for the left sub tree, there are none; for the 
	// right sub tree, we need count how many possible trees are there constructed from {2,3,4,5}, apparently it's the same
	// number as {1,2,3,4}. So the total number of trees under "1" picked as root is dp[0] * dp[4] = 14. (assume dp[0] =1). 
	// Similarly, root 2 has dp[1]*dp[3] = 5 trees. root 3 has dp[2]*dp[2] = 4, root 4 has dp[3]*dp[1]= 5 and root 5 has dp[0]*dp[4] = 14. 
	// Finally sum the up and it's done.
	// 1-d DP: Time ~ O(N^2), Space ~ O(N)	
	public int numsTree(int n) {
		if(n == 0 || n == 1) return 1;
		int[] dp = new int[n + 1];
		dp[0] = 1; dp[1] = 1;
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j < i; j++) {
				dp[i] += dp[j]*dp[i-j-1];
			}
		}
		return dp[n];
	}
	
	// Follow Up 2: Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
	public List<TreeNode> generate(int n){
		return generate(1, n);
	}
	private List<TreeNode> generate(int start, int end){
		List<TreeNode> subTree = new ArrayList<TreeNode>();
		if(start > end) {
			subTree.add(null);
			return subTree;
		}
		if(start == end){
			subTree.add(new TreeNode(start));
            return subTree;
        }
		for(int k = start; k <= end; k++) {
			List<TreeNode> leftSubs = generate(start, k - 1);
			List<TreeNode> rightSubs = generate(k + 1, end);
			for(TreeNode left: leftSubs) {
				for(TreeNode right: rightSubs) {
					TreeNode node = new TreeNode(k);
					node.left = left;
					node.right = right;
					subTree.add(node);
				}
			}
		}
		return subTree;
	}
}
