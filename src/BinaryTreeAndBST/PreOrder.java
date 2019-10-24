/**
 * 
 */
package BinaryTreeAndBST;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月10日 上午10:57:44
* Description:
* 	Implement an iterative, pre-order traversal of a given bianry tree, return the list
* 	of keys of each node in the tree as it pre-order traversed
* 
* 		Examples:								

             5

      	  /    \

    	3        8

  	  /   \        \

	1      4        11

	Pre-order traversal is [5, 3, 1, 4, 8, 11]
	
	Corner Cases: What if the given binary tree is null? Return an empty list in this case.
	
	We use the level order traversal sequence with a special symbol "#" denoting the null node.
*/

public class PreOrder {
//	Binary tree pre-order iterative traversal
	public List<Integer> preOrder(TreeNode root){
		List<Integer> preOrder = new ArrayList<Integer>();
		if(root==null) return preOrder;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		while(!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
//			the left subtree should be traverse before right subtree.
//			since stack is LIFO, we should push right into the stack first
//			so for the next step the top element of stack is the left subtree
			if(cur.right != null) {
				stack.offerFirst(cur.right);
			} 
			if(cur.left != null) {
				stack.offerFirst(cur.left);
			}
			preOrder.add(cur.key);
		}
		return preOrder;
	}
	
}
