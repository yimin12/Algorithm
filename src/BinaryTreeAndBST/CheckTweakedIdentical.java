/**
 * 
 */
package BinaryTreeAndBST;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月10日 下午1:35:01
* Description : 
* 	Determine whether two given binary trees are identical assuming any number of 
* 	‘tweak’s are allowed. A tweak is defined as a swap of the children of one node 
* 	in the tree.
* 
* Example : 
								  and
        			 5                               5
                                           
                  /    \                          /    \
	
    			3        8                      8        3

  			 /   \                                     /   \

           1      4                                   1     4

*/ 


public class CheckTweakedIdentical {
	
//	if you add an root and determine whether it subtree is identical, 
//	it exactly the same with Check Symmetric
	public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
		if(one == null && two == null) {
			return true;
		} else if(one == null || two == null) {
			return false;
		} else if(one.key != two.key) {
			return false;
		}
		return isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left)
				|| isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right);
	}
}
