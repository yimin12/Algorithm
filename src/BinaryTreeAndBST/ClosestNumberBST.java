/**
 * 
 */
package BinaryTreeAndBST;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月23日 下午12:18:46
* Description:
* 	In a binary search tree, find the node containing the closest number to 
* 	the given target number.
* Assumption:
* 	The given root is not null
* 	There are no duplicate keys in the binary search tree
* Examples:
	    5				closest number to 4 is 5
	  /    \			closest number to 10 is 11
	2      11			closest number to 6 is 6
	     /    \
	    6     14
*/

public class ClosestNumberBST {
	public int closest(TreeNode root, int target) {
//		Assumption: the given binary search tree is not null
		
		int result = root.key;
//		the closest number has to be on the path of finding target value in the binary search tree
		while(root!=null) {
			if(root.key == target) {
				return root.key;
			} else {
				if(Math.abs(root.key - target) < Math.abs(result - target)) {
//					update the minimum difference
					result = root.key;
				}
				if(root.key < target) {
					root = root.right;
				} else {
					root = root.left;
				}
			}
		}
		return result;
	}
}
