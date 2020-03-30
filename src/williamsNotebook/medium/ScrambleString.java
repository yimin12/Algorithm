/**
 * 
 */
package williamsNotebook.medium;

import williamsNotebook.common.node.TreeNode;

/**
 * @author yimin Huang
 *	
 *		Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 *		Below is one possible representation of s1 = "great":
 *		To scramble the string, we may choose any non-leaf node and swap its two children.
 *		For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *		We say that "rgeat" is a scrambled string of "great".
 *		Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *		We say that "rgtae" is a scrambled string of "great".
 *		Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1
 *
 * Algorithm Class
 */
public class ScrambleString {
	
	// Situation 0: Is Binary Tree Same
	public boolean isSame(TreeNode one, TreeNode two) {
		if(one == null && two == null) return true;
		if(one == null || two == null || one.val != two.val) return false;
		return isSame(one.left, two.left) && isSame(one.right, two.right);
	}
	
	// Situation 1: Is Binary Tree Symmetric
	// n is number of node, Time: O(n), extra space: O(logn) for call stack
	public boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		return isSymmetric(root.left, root.right);
	}
	private boolean isSymmetric(TreeNode one, TreeNode two) {
		// base case 
		if(one == null && two == null) return true;
		if(one == null || two == null) return false;
		if(one.val != two.val) return false;
		return isSymmetric(one.left, two.right) && isSymmetric(one.right, two.left);
	}
	
	// Follow Up 1: Is Binary Tree Tweaked Identical
	// Time O(n)  Space: O(height)
	public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
		if(one == null && two == null) return true;
		else if(one == null || two == null) return false;
		else if(one.val != two.val) return false;
		return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right) ||
				isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left);
	}
	
	// Follow Up 2.
	// 1. DFS: Time ~ O(2^N) 
	// Assumption, the given s1 and s2 is not null and all the char is lower case
	public boolean isScramble(String s1, String s2) {
		// base case
		if(s1 == null && s2 == null) return true;
		if(s1 == null || s2 == null || s1.length() != s2.length()) return false;
		if(s1.equals(s2)) return true;
		// post processing
		int[] letters = new int[26];
		int len = s1.length();
		for(int i = 0; i < len; i++) {
			letters[s1.charAt(i) - 'a']++;
			letters[s2.charAt(i) - 'a']--;
		}
		for(int i = 0; i < 26; i++) {
			if(letters[i] != 0) return false;
		}
		// logic is like isSymmetric, recursion rule
		for(int i = 1; i < len; i++) {
			if(isScramble(s1.substring(0,i), s2.substring(0,i)) && isScramble(s1.substring(i,len), s2.substring(i,len))) return true;
			if(isScramble(s1.substring(0,i), s2.substring(len-i)) && isScramble(s1.substring(i,len), s2.substring(0, len - i))) return true;
		}
		return false;
	}
	// **********************
	//3-d DP: Time ~ O(N^4) Space:O(n^3)
	public boolean isScrambleII(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length())   return false;
        if (s1.equals(s2))  return true;
        
        // a table of matches
        // T[i][j][k] = true iff s1.substring(i,i+k+1) and s2.substring(j,j+k+1) scrambled pairs 
        boolean[][][] scrambled = new boolean[n][n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                scrambled[i][j][0] = s1.charAt(i) == s2.charAt(j);
        
        for (int k = 1; k < n; k++) // k: length
            for (int i = 0; i < n - k; i++)   // i: index in s1
                for (int j = 0; j < n - k; j++) { // j: index in s2
                    for (int p = 0; p < k; p++) {   // p: split into [0..p] and [p+1..k]
                        scrambled[i][j][k] 
                                = (scrambled[i][j][p] && scrambled[i + p + 1][j + p + 1][k - p - 1])
                               || (scrambled[i][j + k - p][p] && scrambled[i + p + 1][j][k - p - 1]);
                        if (scrambled[i][j][k]) break;
                    }
                }
        
        return scrambled[0][0][n - 1];
    }
}
