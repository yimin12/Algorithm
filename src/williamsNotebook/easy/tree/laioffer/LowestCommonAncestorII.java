/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��21�� ����3:44:17
* Description:
* 	Given two nodes in a binary tree (with parent pointer available), 
* 	find their lowest common ancestor.
* Assumption:
* 	There is parent pointer for the nodes in the binary tree
* 	The given two nodes are not guaranteed to be in the binary tree
* Examples:
* 		  5				The lowest common ancestor of 2 and 14 is 5
        /   \			The lowest common ancestor of 2 and 9 is 9
      9     12			The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
    /  \      \
  2    3      14
*/

public class LowestCommonAncestorII {
	public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
		int l1 = length(one);
		int l2 = length(two);
//		This is a small trick that can guarantee when calling mergeNode(), the first list
//		is the shorter list, the second list is the longer one.
		if(l1 <= l2) {
			return mergeNode(one, two, l2-l1);
		} else {
			return mergeNode(two, one, l1-l2);
		}
	}
	
	private TreeNodeP mergeNode(TreeNodeP shorter, TreeNodeP longer, int diff) {
		while(diff > 0) {
			longer = longer.parent;
			diff--;
		}
		while(longer != shorter) {
			longer = longer.parent;
			shorter = shorter.parent;
		}
		return longer;
	}
//	get the length of the list from the node to the root of the tree 
//	along the path using parent pointers
	private int length(TreeNodeP node) {
		int length = 0;
		while(node != null) {
			length++;
			node = node.parent;
		}
		return length;
	}
}
