/**
 * 
 */
package williamsNotebook.easy.tree;

import williamsNotebook.common.TreeNode;

/**
 * @author yimin Huang
 *		
 *		Given a complete binary tree, count the number of nodes.
 *		
 * Algorithm Class	
 */
public class CountCompleteTreeNodes {

	// Method 1: Recursion O(N). and O(logN) extra space
	public int countNodes(TreeNode root) {
		if(root == null) return 0;
		return  1 + countNodes(root.right) + countNodes(root.left);
	}
	
	// Binary Search
	// Time: O(d^2) = O(log^2 N) where d is the tree depth
	// Space: O(1)
	// Insight: We just need to know how many nodes in the last level of complete binary tree, and do the calculation
	public int countNodesBinarySearch(TreeNode root) {
        // if the tree is empty
        if(root == null) return 0;
        int d = computeDepth(root);
        // if the tree contains only 1 node (root)
        if(d == 0) return 1;
        int left = 1, right = (int)Math.pow(2,d) - 1;
        int pivot;
        while(left <= right){
            pivot = left + (right - left) / 2;
            if(exists(pivot, d, root)) left = pivot + 1;
            else  right = pivot - 1;
        }
        return (int)Math.pow(2,d) - 1 + left;
    }
    private int computeDepth(TreeNode root){
        int d = 0;
        while(root.left != null){
            root = root.left;
            d++;
        }
        return d;
    }
    private boolean exists(int idx, int d, TreeNode root){
        int left = 0, right = (int)Math.pow(2, d) - 1;
        int pivot;
        for(int i = 0; i < d; i++){
            pivot = left + (right - left) / 2;
            if(idx <= pivot){
                root = root.left;
                right = pivot;
            }
            else {
                root = root.right;
                left = pivot + 1;
            }
        }
        return root != null;
    }
}
