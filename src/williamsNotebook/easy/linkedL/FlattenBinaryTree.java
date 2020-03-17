/**
 * 
 */
package williamsNotebook.easy.linkedL;
import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *
 *	Given a binary tree, flatten it to a linked list in-place.
 *	If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
 *  1					step 1:			1					step 2: 		1				step 3:			1
   / \								  /   									 \								 \	
  2   5								 2										  2								  2
 / \   \							/ \										 / \	 					     /
3   4   6						   3   4									3	4							3
										\										 \							 \	
step 4:									 5										  5 						  4
1 									      \										   \						   \
 \		                                   6										6							5
  2																												 \	
   \																											  6	
    3
     \
      4
       \
        5
         \
          6 
 *	
 * Algorithm Class
 */
public class FlattenBinaryTree {

	// Recursion: Time ~ O(N), Space ~ O(logN)
	// Two Steps:
	// 1) recursively flatten leftSubTree and flatten rightSubTree;
	// 2) merge leftLink and rightLink （注意这部分）:
	public void flatten(TreeNode root) {
	    if (root == null)   return;
	    // recursion
	    flatten(root.left);
	    flatten(root.right);   
	    // because we need to use p = root.left, and then find the right most of p. if p is null, it will cause NPE
	    if (root.left == null)  return;
	    // merge: root-left-right
	    TreeNode p = root.left;
	    while (p.right != null) p = p.right;
	    p.right = root.right;
	    root.right = root.left;
	    root.left = null;
	}

	// Iteration: Time ~ O(N), Space ~ O(1)
	public void flattenIterative(TreeNode root) {
		if(root == null) return;
        TreeNode node = root;
        while(node != null){
            // if the node has left child
            if(node.left != null){
                // Find the rightmost node
                TreeNode rightmost = node.left;
                while(rightmost.right != null){
                    rightmost = rightmost.right;
                }
                // rewire teh connection
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
	}
	
}
