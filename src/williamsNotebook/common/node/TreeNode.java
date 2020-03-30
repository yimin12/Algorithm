/**
 * 
 */
package williamsNotebook.common.node;

/**
 * @author yimin Huang
 *
 *	Definition for a binary treeNode 
 *	
 * Algorithm Class
 */

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	/**
	 * 
	 */
	public TreeNode(int val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
	}
	
}
