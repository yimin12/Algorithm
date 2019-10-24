/**
 * 
 */
package RecursionAndSorting;

import BinaryTreeAndBST.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月17日 下午12:32:46
* Description:
* 	Given two nodes in a binary tree, find their lowest common ancestor.
  Assumptions:
	There is no parent pointer for the nodes in the binary tree
	The given two nodes are guaranteed to be in the binary tree
  Examples:
        5				The lowest common ancestor of 2 and 14 is 5
      /   \
     9    12			The lowest common ancestor of 2 and 9 is 9
   /  \    \
  2    3   14
*/
public class LowestCommonAncestorI {
//	return : 
//		null - there is no one or two in the subtree
//		non-null :
//		 1) if there is only one/two in the sub-tree, just return itself
//	 	 2) if there both on/two in the subtree, return LCA
//			a) one is two's descendant, return one.
//			b) two is one's descendant, return two.
//			c) otherwise, return the lowest node with one and two in the two different subtrees
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
//		Assumption: root is not null, one and two guaranteed to be in the tree and not null
//		if it dive to the deepest leaf, it means that we do not find one or two		
		if(root == null) {
			return null;
		}
//		if root is one or two, we can ignore the later recursion, if we find one or two, just return
		if(root == one || root == two) {
			return root;
		}
		TreeNode ll = lowestCommonAncestor(root.left, one, two);
		TreeNode lr = lowestCommonAncestor(root.right, one, two);
//		determining condition: if we find one and two already, return it as the LCA
		if(ll != null && lr != null) {
			return root;
		}
//		this show one is two's descendant or two is one's descendant  
		return ll == null ? lr:ll;
	}
}
