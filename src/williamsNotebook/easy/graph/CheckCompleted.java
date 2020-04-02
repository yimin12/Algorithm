/**
 * 
 */
package williamsNotebook.easy.graph;

import java.util.LinkedList;
import java.util.Queue;

import williamsNotebook.easy.tree.laioffer.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��11�� ����10:57:32
* Description: Determine whether the binary is completed
*/

public class CheckCompleted {
	public boolean isCompleted(TreeNode root) {
		if(root == null) {
			return true;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
//		if the flag is set true, there should not be any child nodes after word
		boolean flag = false;
		queue.offer(root);
		while(!queue.isEmpty()) {
			TreeNode cur = queue.poll();
//			if any of the child is not present, set the flag to true;
			if(cur.left==null) {
				flag = true;
			} else if(flag) {
//			if flag is set but we still see cur has left child, the tree is not complete
				return false;
			} else {
//				if flag is not set and left child is present
				queue.offer(cur.left);
			}
//			same logic applied to the right child
			if(cur.right == null) {
				flag = true;
			} else if(flag) {
				return false;
			} else {
				queue.offer(cur.right);
			}
		}
		return true;
		
	}
}
