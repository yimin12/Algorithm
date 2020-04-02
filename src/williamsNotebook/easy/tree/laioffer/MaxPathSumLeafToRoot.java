/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��21�� ����2:30:47
* Description:
* 	Given a binary tree in which each node contains an integer number. Find the 
* 	maximum possible path sum from a leaf to root.
* Assumption: 
* 	The root of given binary tree is not null
* Examples:
          10		The maximum path sum is 10 + 7 = 17.
        /   \
      -2     7
     /  \
    8   -4
*/

public class MaxPathSumLeafToRoot {
//	Method 1: Pass down the prefix sum.
	public int maxPathSum(TreeNode root) {
//		Assumption: root != null
		return maxPathSum(root, 0);
	}
	private int maxPathSum(TreeNode root, int sum) {
		sum+=root.key;
		if(root.left == null && root.right == null) {
			return sum;
		} else if(root.left == null) {
			return maxPathSum(root.right, sum);
		} else if(root.right == null) {
			return maxPathSum(root.left, sum);
		}
		return Math.max(maxPathSum(root.left, sum), maxPathSum(root.right, sum));
	}
//	Method 2�� Bottom up return the max suffix sum
	public int maxPathSumII(TreeNode root) {
//		Assumption: the root is not null
		if(root.left == null && root.right == null) {
			return root.key;
		}
		if(root.left == null) {
			return maxPathSum(root.right) + root.key;
		}
		if(root.right == null) {
			return maxPathSum(root.left) + root.key;
		}
		return root.key + Math.max(maxPathSum(root.left), maxPathSum(root.right));
	}
}
