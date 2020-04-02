/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

import java.util.HashMap;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��21�� ����8:40:35
* Description:
* 	Given the preorder and inorder traversal sequence of a binary tree, 
* 	reconstruct the original tree.
* Assumption:
* 	The given sequences are not null and they have the same length
* 	There are no duplicate keys in the binary tree
* Examples:
* 	preorder traversal = {5, 3, 1, 4, 8, 11}
* 	inorder traversal = {1, 3, 4, 5, 8, 11}
* 	the corresponding binary tree is
		         5
		      /    \
		     3      8
		  /   \      \
		1      4      11
  How is the binary tree represented?
    We use the pre order traversal sequence with a special symbol "#" denoting the null node.
*/

public class ReconstructBTInPre {
//	Method 1: Utilizing the inOrder sequence to determine the size of left/right subtrees
	public TreeNode reconstruct(int[] in, int[] pre) {
//		Assumption: pre, in are not null, there is no duplicates in the binary tree, the length of
//		pre an in are guaranteed to be the same
		Map<Integer, Integer> inIndex = indexMap(in);
		return helper(pre, inIndex,0, in.length - 1, 0, pre.length - 1);
	}
	private Map<Integer, Integer> indexMap(int[] in){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < in.length; i++) {
			map.put(in[i],i);
		}
		return map;
	}
	private TreeNode helper(int[] pre, Map<Integer, Integer> inIndex, int inLeft, int inRight, 
			int preLeft, int preRight) {
		if(inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(pre[preLeft]);
//		get the position of the root in inOrder sequence, so that we will know the size of left/right subtrees
		int inMid = inIndex.get(root.key);
		root.left = helper(pre, inIndex, inLeft, inMid-1, preLeft+1, preLeft + inMid - inLeft);
		root.right = helper(pre, inIndex, inMid+1, inRight, preLeft+inMid-inRight+1, preRight);
		return root;
	}
//	Method 2: Another linear Solution with traversing and constructing the binary tree using
//	preOrder and inOrder at the same time
	public TreeNode recnstructII(int[] in, int[] pre) {
		int[] preIndex = new int[] {0};
		int[] inIndex = new int[] {0};
		return helperII(preIndex, in, preIndex, inIndex, Integer.MAX_VALUE);
	}
	private TreeNode helperII(int[] pre, int[] in, int[] preIndex, int[] inIndex, int target) {
//		Traversing and construct the binary tree using preOrder and inOrder, the preOrder
//		is [root][left subtree][right subtree] from the preOrder, we know the root of the binary tree
//		the inOrder is [left subtree][root][right subtree] when we know the root, we actually 
//		know the boundary of the left/right subtree.
//		The "target" is actually the root, and we are using inOrder to identify the boundary of left subtree
		if(inIndex[0] >= in.length || in[inIndex[0]] == target) {
			return null;
		}
		TreeNode root = new TreeNode(pre[pre[preIndex[0]]]);
		preIndex[0]++;
		root.left = helperII(pre, in, preIndex, inIndex, root.key);
		return root;
	}
}
