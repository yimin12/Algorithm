/**
 * 
 */
package williamsNotebook.easy.dynamicP;

import java.util.ArrayList;
import java.util.List;

import williamsNotebook.common.node.TreeNode;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午3:17:20
* Description:
* 	Input: 3	
* Output:
* 	[
	  [1,null,3,2],
	  [3,2,null,1],
	  [3,1,null,null,2],
	  [2,1,3],
	  [1,null,2,null,3]
	]
  Explanation:
	The above output corresponds to the 5 unique BST's shown below:
	1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

public class UniqueBinarySearchTreeII {
	
//	Similar thinking to Unique Binary Search Trees, the core of building a BST from sorted array 1, ..., n is to select a root, then build left subtrees and right subtrees recursively. The tricky part in this problem however, is to combine different subtrees and root node to unique BSTs.
//	Three loops
//	loop over start, ..., end and select i for the root node; (recursively) build left subtrees with left subsequence start, ..., i - 1; build right subtrees with right subsequence i + 1, ..., end
//	loop over left subtrees, with root node for left subtrees; TreeNode leftNode
//	loop over right subtrees, with root node for right subtrees; TreeNode rightNode
//	Note: create a new root has to be inside the 3rd loop, and then set root.left = leftNode, root.right = rightNode, and finally add to result list.
	// Time Complexity: O({4^n}/{n^{1/2})  space complexity: O({4^n}/{n^{1/2})
	public List<TreeNode> generateTrees(int n){
		if(n == 0) return new ArrayList<TreeNode>();
		return generateTrees(1, n);
	}
	private List<TreeNode> generateTrees(int start, int end){
		List<TreeNode> trees = new ArrayList<TreeNode>();
		if(start > end) {
			trees.add(null);
			return trees;
		}
		// select root, and generate sub trees recursively\
		for(int i = start; i <= end; i++) {
			// step 1: devide 
			List<TreeNode> leftSubTrees = generateTrees(start, i-1);
			List<TreeNode> rightSubTrees = generateTrees(i+1, end);
			// step 2: conquer
			for(TreeNode left:leftSubTrees) {
				for(TreeNode right: rightSubTrees) {
					// 	把每个不同形状的同素异形体，拼装再root上，然后放置再结果之中。
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					trees.add(root);
				}
			}
		}
		return trees;
	}
}
