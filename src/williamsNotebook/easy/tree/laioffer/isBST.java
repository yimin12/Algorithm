/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��10�� ����1:45:50
* Description: Determine whether it is an Binary Search Tree
*/

/**
 * @author 61771
 *
 */
public class isBST {
	
	public boolean isBST(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private boolean isBST(TreeNode root, int min, int max) {
		if(root == null) return false;
		if(root.key < min || root.key > max) return false;
		return isBST(root.left, min, root.key - 1) && isBST(root.right, root.key + 1, max);
	}
	
}
