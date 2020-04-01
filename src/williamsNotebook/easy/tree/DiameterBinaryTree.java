/**
 * 
 */
package williamsNotebook.easy.tree;
import williamsNotebook.common.node.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午8:39:57
* Description:
* 	Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a 
* 	binary tree is the length of the longest path between any two nodes in a tree. This path may or may 
* 	not pass through the root.
* Given a binary tree:  Return3, which is the length of the path [4,2,1,3] or [5,2,1,3].
* 		  1
         / \
        2   3
       / \     
      4   5
*/

public class DiameterBinaryTree {

	// DFS to calculate each height difference between two subTrees
	// Time: O(nlogn) Space : O(height)
	public int diameterOfBinaryTree(TreeNode root) {
		if(root == null || (root.left == null && root.right == null)) {
			return 0;
		}
		int[] max = new int[] {Integer.MIN_VALUE};
		maxDepth(root, max);
		return max[0];
	}
	private int maxDepth(TreeNode root, int[] max) {
		if(root == null) return 0;
		int left = maxDepth(root.left, max);
		int right = maxDepth(root.right, max);
		max[0] = Math.max(max[0], left + right);
		return Math.max(left, right) + 1;
	}
	// Optimized Implementation
	// The above implementation can be optimized by calculating the height in the same recursion rather than calling a 
	// height() separately. Thanks to Amar for suggesting this optimized version. This optimization reduces time complexity 
	// to O(n).
	private class Height{
		int h;
	}
	TreeNode root;
	int diameterOpt(TreeNode root, Height height) {
		Height lh = new Height(), rh = new Height();
		if(root == null) {
			height.h = 0;
			return 0;
		}
		int ldiameter = diameterOpt(root.left, lh);
		int rdiameter = diameterOpt(root.right, rh);
		height.h = Math.max(lh.h, rh.h) + 1;
		return Math.max(lh.h + rh.h + 1, Math.max(ldiameter, rdiameter));
	}
	int diameter() {
		Height height = new Height();
		return diameterOpt(root, height);
	}
	int height(TreeNode root) {
		if(root == null) return 0;
		return (1 + Math.max(height(root.left), height(root.right)));
	}
}
