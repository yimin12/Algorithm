/**
 * 
 */
package Common;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import BinaryTreeAndBST.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月22日 下午5:00:08
* Description:
* 	Get the list of keys in a given binary tree layer by layer in zig-zag order.
* Examples:
		        5
		      /    \
		    3        8
		  /   \        \
		 1     4        11
  the result is [5, 3, 8, 11, 4, 1]
  Corner Cases：
  	What if the binary tree is null? Return an empty list in this case.
*/

public class ZigZagLayerByLayer {
	public List<Integer> zigZag(TreeNode root){
		if(root==null) {
			return new LinkedList<Integer>();
		}
		Deque<TreeNode> deque = new LinkedList<TreeNode>();
		List<Integer> list = new LinkedList<Integer>();
		deque.offer(root);
		int layer = 0;
		while(!deque.isEmpty()) {
//			The size of current level should be extracted at the first place, because size of the deque
//			is changing all the time
			int size = deque.size();
			for(int i = 0; i < size; i++) {
				if(layer==0) {
					// at even layer, from right to left
					TreeNode tempNode = deque.pollLast();
					list.add(tempNode.key);
					if (tempNode.right != null) {
						deque.offerFirst(tempNode.right);
					}
					if(tempNode.left != null) {
						deque.offerFirst(tempNode.left);
					}
				} else {
//					at odd layer, from left to right
					TreeNode tempNode = deque.pollFirst();
					list.add(tempNode.key);
					if(tempNode.left != null) {
						deque.offerLast(tempNode.left);
					}
					if(tempNode.right != null) {
						deque.offerLast(tempNode.right);
					}
				}
			}
//			small trick, if you want an binary option, use 1 - x and x
			layer = 1 - layer;
		}
		return list;
	}
}
