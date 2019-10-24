/**
 * 
 */
package BinaryTreeAndBST;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��23�� ����1:04:07
* Description:
* 	In a binary search tree, find the node containing the largest number smaller than the 
* 	given target number. If there is no such number, return -2^31
* Assumption:
* 	The given root is not null
* 	There are no duplicate keys in the binary search tree
* Examples:
* 	    5
  	 /    \
    2     11
        /    \
       6     14
largest number smaller than 1 is Integer.MIN_VALUE(Java) or INT_MIN(c++)
largest number smaller than 10 is 6
largest number smaller than 6 is 5
How is the binary tree represented?
We use the level order traversal sequence with a special symbol "#" denoting the null node.
*/
public class LargestNumberSmallerBST {
	public int largestSmaller(TreeNode root, int target) {
		int result = Integer.MIN_VALUE;
		while(root != null) {
			if(root.key >= target) {
				root = root.left;
			} else {
//			the candidates are all the nodes on the path of searching for target, which is smaller
//			target. and notice that, the later searched node has largest value than the earlier 
//			searched ones
				result = root.key;
				root = root.right;
			}
		}
		return result;
	}
}
