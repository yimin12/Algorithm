/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��23�� ����1:17:32
* Description:
* 	Delete the target key K in the given binary search tree if the binary search 
* 	tree contains K. Return the root of the binary search tree.
* 	Find your own way to delete the node from the binary search tree, after deletion 
* 	the binary search tree's property should be maintained.
* Assumption:
* 	There are no duplicate keys in the binary search tree, the smallest larger node is first 
*	candidate after deletion 
*/

public class DeleteBST { 
	public TreeNode delete(TreeNode root, int key) {
		if(root == null) {
			return null;
		}
//		base case
		if(key == root.key) {
			if(root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			} else if(root.right.left == null) {
				root.right.left = root.left;
				return root.right;
			} else {
				TreeNode newRoot = deteleSmallest(root.right);
				newRoot.left = root.left;
				newRoot.right = root.right;
				return newRoot;
			}
		}
		if(key < root.key) {
			root.left = delete(root.left, key);
		} else {
			root.right = delete(root.right, key);
		}
		return root;
	}
	private TreeNode deteleSmallest(TreeNode root) {
		while(root.left.left != null) {
			root = root.left;
		}
		TreeNode smallest = root.left;
		root.left = root.left.right;
		return smallest;
	}
}
