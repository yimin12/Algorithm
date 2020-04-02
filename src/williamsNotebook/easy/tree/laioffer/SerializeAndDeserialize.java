/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

import java.util.LinkedList;
import java.util.Queue;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��28�� ����11:34:07
* Description:
* 	Serialization and de-serialization of an binary tree
*/

public class SerializeAndDeserialize {
	String Serilize(TreeNode root) {
		if(root==null) return null;
		StringBuffer sb = new StringBuffer();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		if(root!= null) {
			queue.offer(root);
		}
		while(!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			if(cur.left != null) {
				queue.offer(cur.left);
			} else if(cur.right != null) {
				queue.offer(cur.right);
			} else {
				sb.append("#"+",");
			}
		}
		if(sb.length() != 0) {
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	TreeNode Deserialize(String str) {
		TreeNode headNode = null;
		if(str == null || str.length() == 0) return headNode;
		String[] nodes = str.split(",");
		TreeNode[] treeNodes = new TreeNode[nodes.length];
		for(int i = 0; i < nodes.length; i++) {
			if(!nodes[i].equals("#")) treeNodes[i] = new TreeNode(Integer.valueOf(nodes[i]));
		}
		for(int i = 0, j=1; i < treeNodes.length; i++) {
			if(treeNodes[i]!= null) {
				treeNodes[i].left = treeNodes[j++];
				treeNodes[i].right = treeNodes[j++];
			}
		}
		return treeNodes[0];
	}
}
