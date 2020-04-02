/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��10�� ����12:38:25
* Description: Given a binary Tree, determine whether it is balanced
*/

public class CheckBalanced {
	
	public boolean isBalanced(TreeNode root) {
		if(root==null)return true;
//		use -1 to denote the tree is not balanced.
//		>= 0 value means the tree balanced and it is height of the tree
		return height(root) != -1;
	}
	private int height(TreeNode root) {
		if(root == null) {
			return 0;
		}
		int leftHeight = height(root.left);
		if(leftHeight == -1) {
			return -1;
		}
		int rightHeight = height(root.right);
		if(rightHeight == -1) {
			return -1;
		}
//		if not balanced, return -1
		if(Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		}
		return Math.abs(leftHeight - rightHeight) + 1;
	}
}
