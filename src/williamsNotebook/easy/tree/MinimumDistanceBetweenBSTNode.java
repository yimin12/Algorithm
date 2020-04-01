/**
 * 
 */
package williamsNotebook.easy.tree;

import williamsNotebook.common.node.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午8:09:00
* Description:
* 	Given a Binary Search Tree (BST) with the root noderoot, return the minimum difference between the values of any two different nodes in the tree.
* Example:								Output:
* 	Input: root = [4,2,6,1,3,null,null]			1;
*/
public class MinimumDistanceBetweenBSTNode {

	// Time Complexity: O(N), where N is the number of nodes in the tree. The complexity comes from the sorting operation.
	// Space Complexity:O(N), the size of vals.
	Integer prev, ans;
	public int minDiffInBST(TreeNode root) {
		prev = null;
		ans = Integer.MAX_VALUE;
		dfs(root);
		return ans;
	}
	// in order traverse
	public void dfs(TreeNode root) {
		if(root == null) return;
		dfs(root.left);
		if(prev != null) {
			ans = Math.min(ans, root.val - prev);
		}
		prev = root.val;
		dfs(root.right);
	}
}
