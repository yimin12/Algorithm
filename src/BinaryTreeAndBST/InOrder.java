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
* @version Create Time：2019年10月10日 上午11:33:06
* Description:
* 	Implement an iterative, in-order traversal of a given bianry tree, return the list
* 	of keys of each node in the tree as it in-order traversed
* 
* 		Examples:								

             5

      	  /    \

    	3        8

  	  /   \        \

	1      4        11

	post-order traversal is [1, 3, 4, 5, 11, 8]
	
	Corner Cases: What if the given binary tree is null? Return an empty list in this case.
	
	We use the level order traversal sequence with a special symbol "#" denoting the null node.
*/

public class InOrder {
	
	public List<Integer> inOrdre(TreeNode root){
		List<Integer> inOrder = new ArrayList<Integer>();
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode cur = root;
		while(cur != null || !stack.isEmpty()) {
//			always try to go to left side to see if there is any node
//			should be traversed before the cur node, cur node is stored on stack
//			since it has not been traverse
			if(cur!=null) {
				stack.offerFirst(cur);
				cur = cur.left;
			} else {
//				if can not go left, meaning all the nodes on the left side of 
//				the top node on stack have been traversed, the next traversing 
//				node should be the top node on stack
				cur = stack.pollFirst();
				inOrder.add(cur.key);
				cur = cur.right;
			}
		}
		return inOrder;
	}
}
