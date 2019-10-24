/**
 * 
 */
package BinaryTreeAndBST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;



/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月10日 上午11:58:11
* Description:	Binary Tree Post-Order Iterative Traversal
* 	Implement an iterative, post-order traversal of a given bianry tree, return the list
* 	of keys of each node in the tree as it post-order traversed
* 
* 		Examples:								

             5

      	  /    \

    	3        8

  	  /   \        \

	1      4        11

	post-order traversal is [1, 4, 3, 11, 8, 5]
	
	Corner Cases: What if the given binary tree is null? Return an empty list in this case.
	
	We use the level order traversal sequence with a special symbol "#" denoting the null node.
*/

public class PostOrder {

//	Method 1: post-order it he reverse order of pre-order with traversing right
//	subtree before traversing left subtree;
	public List<Integer> postOrderI(TreeNode root){
		List<Integer> postOrder = new ArrayList<Integer>();
		if(root == null)return postOrder;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
//		Pre-Order traverse with right subtree before traverse left subtree
		while(!stack.isEmpty()) {
			TreeNode cur = stack.pollFirst();
//			conduct the result for the special pre-order traversal
			postOrder.add(cur.key);
//			in pre-order the right subtree will be traversed before the left subtree
//			In this case, we push left child first
			if(cur.left!=null) {
				stack.offerFirst(cur.left);
			} 
			if(cur.right!=null) {
				stack.offerFirst(cur.right);
			}
		}
//		Reverse Order of Pre-Order
		Collections.reverse(postOrder);
		return postOrder;
	}
	
//	Method 2: check the relation between the current node and the previous node to 
//			  determine which direction should go next;
	public List<Integer> postOrderII(TreeNode root){
		List<Integer> result = new ArrayList<Integer>();
		if(root == null)return result;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
//		to record the previous node on the way of DFS so that 
//		we can determine the direction
		TreeNode prev = null;
//		You should focus on whether the traversing is going down or going up
		while(!stack.isEmpty()) {
			TreeNode cur = stack.peekFirst();
//			Case 1.0 : if we going down, either left/right direction(this mainly dealing the leaf node)
			if(prev == null || cur == prev.left || cur == prev.right) {
				if(cur.left != null) {
					stack.offerFirst(cur.left);
				} else if(cur.right != null) {
					stack.offerFirst(cur.right);
				} else {
//					if it has no child, we should add it at this time
					stack.pollFirst();
					result.add(cur.key);
				}
			}
//			Case 2.0: if going up from right child or it only has left child
			else if(prev == cur.right || prev == cur.left && cur.right == null) {
//				if we are going up from the right side or going up from left side
//				but we can not go right afterwards
				stack.pollFirst();
				result.add(cur.key);
			}
//			Case 2.1: if going up from left child
			else {
//				othewise, we are going up from the left side and we can go down right side
				stack.offerFirst(cur.right);
			}
			prev = cur;
		}
		return result;
		
	}
}
