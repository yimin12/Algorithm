/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��21�� ����2:45:34
* Description:
* 	Given a binary tree and a target sum, determine if the tree has a root-to-leaf path 
* 	such that adding up all the values along the path equals the given target.
* Examples:
* 	Given the below binary tree and target = 16,
              5		return true, as there exist a root-to-leaf path 5-8-3 which sum is 16.
             / \
            4   8
           /   / \
          1    3  4
         /  \      \
        7    2      1
*/

public class BinaryTreePathSumToTarget {
	public boolean exist(TreeNode root, int sum) {
		if(root == null) return false;
//		pass down the prefix of the path
		List<TreeNode> path = new ArrayList<TreeNode>();
		return helper(root, path, 0);
	}
	private boolean helper(TreeNode root, List<TreeNode> path, int sum) {
		path.add(root);
//		check if can find a subpath ended at root node, the sum of the subpath is target
		int temp = 0;
		for(int i = path.size() - 1; i >= 0; i--) {
			temp += path.get(i).key;
			if(temp == sum) {
				return true;
			}
		}
		if(root.left != null && helper(root.left, path, sum)) {
			return true;
		}
		if(root.right != null && helper(root.right, path, sum)) {
			return true;
		}
		path.remove(path.size() - 1);
		return false;
	}
//	Method 2: an O(n) solution
//	Think about the related problem, how do you find if there is a subarray sum to target value
//	If there is an O(n) solution to the above problem, we can extend it to each
//	of the root -> left paths of the binary tree
	public boolean existII(TreeNode root, int sum) {
		if(root == null) {
			return false;
		}
		Set<Integer> prefixSum = new HashSet<>();
		prefixSum.add(0);
		return helperII(root, prefixSum, 0, sum);
	}
	private boolean helperII(TreeNode root, Set<Integer> prefixSums, int prevSum, int sum) {
		prevSum += root.key;
		// this step is the key of cutting branches
		if(prefixSums.contains(prevSum - sum)) {
			return true;
		}
//		Adds the specified element to this set if it is not already present, then return true
//		If this set already contains the element, the call leaves the set unchanged and returns false.
		boolean needRemove = prefixSums.add(prevSum);
		if(root.left!=null && helperII(root.left, prefixSums, prevSum, sum)) {
			return true;
		}
		if(root.right!=null && helperII(root.right, prefixSums, prevSum, sum)) {
			return true;
		}
		if(needRemove) {
//			only delete the value just added in this current level at back tracking
			prefixSums.remove(prevSum);
		}
		return false;
	}
}
