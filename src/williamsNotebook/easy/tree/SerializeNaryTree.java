/**
 * 
 */
package williamsNotebook.easy.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

import williamsNotebook.common.node.KnaryTreeNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月30日 下午9:03:37
* Description:
* 	Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
* 	Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your
* 	serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.
* 	
*/
public class SerializeNaryTree {
	
	// Encodes a tree a single string
	// Small difference with Serialize and Deserialize  Binary Tree, you need to add the children size after you construct a new node
	// It is much more easier for deserialize
	public String serialize(KnaryTreeNode root) {
		StringBuilder sb = new StringBuilder();
		serial(sb, root);
		return sb.toString();
	}
	private void serial(StringBuilder sb, KnaryTreeNode root) {
		if(root == null) {
			sb.append("#");
			sb.append(",");
		} else {
			sb.append(root.val);
			sb.append(",");
			if(root.children != null) {
				sb.append(root.children.size());
				sb.append(",");
				for(KnaryTreeNode child : root.children) {
					serial(sb, child);
				}
			}
		}
	}
	public KnaryTreeNode deserialize(String data) {
		Deque<String> queue = new LinkedList<String>(Arrays.asList(data.split(",")));
		return buildTree(queue);
	}
	private KnaryTreeNode buildTree(Deque<String> queue) {
		String val = queue.poll();
		if(val.equals("#")) {
			return null;
		}
		KnaryTreeNode root = new KnaryTreeNode(Integer.parseInt(val));
		int childrenCount = Integer.parseInt(queue.poll());
		root.children = new ArrayList<KnaryTreeNode>();
		for(int i = 0; i < childrenCount; i++) {
			root.children.add(buildTree(queue));
		}
		return root;
	}
	public static void main(String[] args) {
		SerializeNaryTree solution = new SerializeNaryTree();
		KnaryTreeNode root = new KnaryTreeNode(1);
		KnaryTreeNode root1 = new KnaryTreeNode(2);
		KnaryTreeNode root2 = new KnaryTreeNode(3);
		KnaryTreeNode root3 = new KnaryTreeNode(4);
		KnaryTreeNode root4 = new KnaryTreeNode(5);


		root.children.add(root1);
		root.children.add(root2);
		root.children.add(root3);
		root.children.add(root4);
		
		KnaryTreeNode one1 = new KnaryTreeNode(6);
		KnaryTreeNode one2 = new KnaryTreeNode(7);
		KnaryTreeNode one3 = new KnaryTreeNode(8);
		KnaryTreeNode one4 = new KnaryTreeNode(9);
		
		root1.children.add(one1);
		root1.children.add(one2);
		root2.children.add(one3);
		root3.children.add(one4);
		
		String serialize = solution.serialize(root);
		System.out.println(serialize);
	}
}
