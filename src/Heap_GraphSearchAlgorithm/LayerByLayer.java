/**
 * 
 */
package Heap_GraphSearchAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import BinaryTreeAndBST.TreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月11日 上午10:25:27
* Description: Get Keys In Binary Tree Layer By Layer(print the binary tree in level order)
*/


public class LayerByLayer {
	public List<List<Integer>> layerByLayer(TreeNode root){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if(root == null) {
			return list;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while(!queue.isEmpty()) {
//			create an current list to store all nodes on the current level
			List<Integer> curLayer = new ArrayList<Integer>();
//			record the size of current level, we can know when to stop generate child node
			int size = queue.size();
//			traverse all the nodes on the current level and prepare for the next level
			for(int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				curLayer.add(cur.key);
//				prepare for the next level
				if(cur.left!=null) {
					queue.offer(cur.left);
				}
				if(cur.right!=null) {
					queue.offer(cur.right);
				}
			}
			list.add(curLayer);
		}
		return list;
	}
}
