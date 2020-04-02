/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��17�� ����12:01:06
* Description: 
* 	Given a binary tree, find the node with the max difference in the total number 
* 	descendents in it left sub-tree and right sub-tree
*/

public class MaxDiffNode {
	public TreeNode maxDiffNode(TreeNode root) {
		if(root == null) {
			return null;
		}
		TreeNode[] nodes = new TreeNode[1];
		int[] diff = new int[1];
		diff[0] = -1;
		numNodes(root, nodes, diff);
		return nodes[0];
	}
//	return the # of nodes in the sub-tree
	private int numNodes(TreeNode root, TreeNode[] node, int[] diff) {
		if(root == null) {
			return 0;
		} 
		int leftNum = numNodes(root.left, node, diff);
		int rightNum = numNodes(root.right, node, diff);
		if(Math.abs(leftNum - rightNum) > diff[0]) {
//			update the root that contains the max difference between left and right subtree
			node[0] = root;
//			update the max difference
			diff[0] = Math.abs(leftNum - rightNum);
		}
		return leftNum + rightNum + 1;
	}
}
