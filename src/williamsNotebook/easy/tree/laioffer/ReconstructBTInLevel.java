/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��22�� ����12:39:58
* Description:
*  	Given the levelorder and inorder traversal sequence of a binary tree, 
*  	reconstruct the original tree.
* Assumption:
* 	The given sequence is not null
* 	There are no duplicate key in the binary search tree
* Examples:
* 	levelorder traversal = {5, 3, 8, 1, 4, 11}
	inorder traversal = {1, 3, 4, 5, 8, 11}
	the corresponding binary tree is
		         5
		      /    \
		    3       8
		  /   \      \
		1      4      11
*/

public class ReconstructBTInLevel {
	public TreeNode reconstruct(int[] level, int[] in) {
//		Assumption: level, in are not null, there is no duplicate in the binary tree
		Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
		for(int i = 0; i < in.length; i++) {
			inMap.put(in[i],i);
		}
		List<Integer> lList = new ArrayList<Integer>();
		for(int num : level) {
			lList.add(num);
		}
		return helper(lList, inMap);
	}
	private TreeNode helper(List<Integer> level, Map<Integer, Integer> inMap) {
		if(level.isEmpty()) {
			return null;
		}
		TreeNode root = new TreeNode(level.remove(0));
		List<Integer> left = new ArrayList<Integer>();
		List<Integer> right = new ArrayList<Integer>();
		for(int num : level) {
			if(inMap.get(num) < inMap.get(root.key)) {
				left.add(num);
			} else {
				right.add(num);
			}
		}
		root.left = helper(left, inMap);
		root.right = helper(right, inMap);
		return root;
	}
}
