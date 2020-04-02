/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

import java.util.ArrayList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��10�� ����1:50:35
* Description:	Get keys in binary search tree in given range
*/

public class GetRange {
	public List<Integer> getRange(TreeNode root, int min, int max){
		List<Integer> list = new ArrayList<Integer>();
		getRange(root, min, max, list);
		return list;
	}
	private void getRange(TreeNode root, int min, int max, List<Integer> list) {
//		This pattern is inorder pattern
//		If you print the bst by following inorder pattern, it would be an array with ascending order
		if(root == null)return;
//		1.determine if left subtree should be traversed, only when root.key > min
//		we should traverse the left subtree
		if(root.key > min) {
			getRange(root.left, min, max, list);
		}
//		2.determine if root should be traversed
		if(root.key >= min && root.key <= max) {
			list.add(root.key);
		}
//		3.determine if right subtree should be traversed
		if(root.key < max) {
			getRange(root.right, min, max, list);
		}
	}
}
