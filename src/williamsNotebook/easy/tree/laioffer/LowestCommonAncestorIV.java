/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��21�� ����4:00:01
* Description:
* 	Given K nodes in a binary tree, find their lowest common ancestor.
* Assumption:
* 	K > 2
* 	There is no parent pointer for the nodes in the binary tree
* 	The given K nodes are guaranteed to be in the binary tree
* Examples:
* 		5				The lowest common ancestor of 2, 3, 14 is 5
      /   \				The lowest common ancestor of 2, 3, 9 is 9
     9     12
   /  \      \
  2    3      14
*/

public class LowestCommonAncestorIV {
	public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
//		Assumption: the list of nodes is not null or not empty, all the nodes in the list are
//		guaranteed to be in the tree
		Set<TreeNode> set = new HashSet<TreeNode>(nodes);
		return helper(root, set);
	}
	private TreeNode helper(TreeNode root, Set<TreeNode> set) {
//		There are two kinds of base case, one is null and one is found
		if(root == null) {
			return null;
		}
		if(set.contains(root)) {
			return root;
		}
		TreeNode l = helper(root.left, set);
		TreeNode r = helper(root.right, set);
		if(l != null && r != null) {
			return root;
		}
		return l != null ? l :r ; 
	}
}
