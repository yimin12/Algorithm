/**
 * 
 */
package RecursionAndSorting;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月17日 上午11:26:11
* Description:
* 	Given a binary tree, count the number of nodes in each node’s left subtree, 
* 	and store it in the numNodesLeft field.
* Examples:
                  1(6)  			The numNodesLeft is shown in parentheses.
               /       \
             2(3)      3(0)
           /      \
        4(1)     5(0)
       /   \        \
	6(0)   7(0)    8(0)
*/


public class NumNodesLeft {
	static class TreeNode{
		int key;
		TreeNode left;
		TreeNode right;
//		store the # of nodes in left sub-tree
		int num;
		public TreeNode(int key) {
			this.key = key;
		}
	}
	public void numNodesLeft(TreeNode root) {
		numNodes(root);
	}
//	#return the # of nodes in the sub tree
	private int numNodes(TreeNode root) {
		if(root == null) return 0;
//		left Num is the # of nodes in subtree of left root
		int leftNum = numNodes(root.left);
//		rightNum is the # of nodes in sub-tree of root.right
		int rightNum = numNodes(root.right);
		root.num = leftNum;
		return leftNum + rightNum + 1;
	}
}
