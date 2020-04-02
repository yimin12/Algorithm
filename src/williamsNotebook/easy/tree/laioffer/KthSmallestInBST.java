/**
 * 
 */
package williamsNotebook.easy.tree.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��30�� ����9:30:47
* Description:
* 	Find the kth smallest value in BST
* Assumption:
* 	The BST is not null and the k >=0
* Examples:
	    5				
	  /    \			
	2      11			
	     /    \
	    6     14
*/

public class KthSmallestInBST {
//	Because it is a binary search tree, if we traverse it by in-order, the result will
//	be sorted in ascending order, then we find the kth smallest
	TreeNode node = null;
	int i = 0;
	public TreeNode kthSmallestBST(TreeNode root, int k) {
		if(root == null || k < 0) return null;
		kthSmallestBST(root.left, k);
		System.out.println(root.key + ":" + k--);
		
		if(0 < k) kthSmallestBST(root.right, k);
		return node;
	}
	public static void main(String[] args) {
		TreeNode one = new TreeNode(5);
		TreeNode two = new TreeNode(2); 
		TreeNode three = new TreeNode(11);
		TreeNode four = new TreeNode(6);
		TreeNode five = new TreeNode(14);
		one.left = two;
		one.right = three;
		three.left = four;
		three.right = five;
		
		KthSmallestInBST kthSmallestInBST = new KthSmallestInBST();
		System.out.println(kthSmallestInBST.kthSmallestBST(one, 2).key);
	}
}
