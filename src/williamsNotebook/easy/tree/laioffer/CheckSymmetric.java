/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��10�� ����1:04:59
* Description: Determine whether the binary is symmetric
*/

/**
 * @author 61771
 *
 */
public class CheckSymmetric {
	public boolean isSymmetric(TreeNode root) {
		if(root == null) {
			return true;
		}
		return isSymmetric(root.left, root.right);
	}
	private boolean isSymmetric(TreeNode left, TreeNode right) {
		if(left == null && right == null) {
			return true;
		} else if (left == null || right == null) {
			return false;
		} else if(left.key != right.key) {
			return false;
		}
		return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
	}
}
