/**
 * 
 */
package williamsNotebook.medium;

import java.util.Arrays;

/**
 * @author yimin Huang
 *
 *		Given a string s, return the longest palindrome subsequence.
 *		Example: s = "abca"  Output: 3
 *		Explanation: The longest palindrome subsequences could be "aba" or "aca".
 *
 * Algorithm Class
 */
public class LongestPalindromicSubquence {
	
	// naive dynamic programming solution
	// dp[i][j] = the length of the longest palidromic subsequence in s[i...j]
	// index 01234
	//  s = "bbbab"
	// Base case: if no character were selected
	// dp[i][i-1] = 0 (size = 0)
	// dp[i][i] = 1 (size = 1)
	// Induction Rule: after filling the size = 0 and size = 1
	// size = 2
	// dp[0][1] = 2 ("bb")
	// dp[1][2] = 2 ("bb")
	// dp[2][3] = max(dp[2][2],dp[3][3]) = 1 ("ba")
	// dp[3][4] = max(dp[3][3],dp[4][4]) = 1 ("ab")
	// size = 3 (check whether the first char match the last char)
	// dp[0][2] = 2 + dp[1][1] = 3 ("bbb") match
	// dp[1][3] = max(dp[1,2],dp[2][3]) = 2, dont match
	// dp[2][4] = 2 + dp[3][3] = 3 match
	// Generalized:
	// Case 1: if s[i] == s[j], dp[i][j] = 2 + dp[i+1][j-1]
	// Case 2: if s[i] != s[j], max(dp[i][j-1], dp[i+1][j])
	// M 0 1 2 3 4 
	// 0 x x x x x	
	// 1 x x i j x
	// 2 x x i i x
	// 3 x x x x x
	// 4 x x x x x
	// if you need to fill cell of j, you need three cells' information
	// Time: O(n^2)  Space: O(n^2)
	public int findLongest(String s) {
		if(s == null) return 0;
		int[][] dp = new int[s.length()+1][s.length()+1];
		// base case
		for(int i = 1; i <= s.length(); i++) {
			dp[i][i-1] = 0;
			dp[i][i] = 1;
		}
		// induction rule
		for(int i = s.length(); i >= 1; i--) {
			for(int j = i + 1; j <= s.length(); j++) {
				if(s.charAt(i-1) == s.charAt(j-1)) {
					dp[i][j] = dp[i+1][j-1] + 2;
				} else {
					dp[i][j]= Math.max(dp[i+1][j], dp[i][j-1]); 
				}
			}
		}
		return dp[1][s.length()];
	}
	
	// Method 2: all kind of dp, you can optimize the space to O(n)
	// The logic is exactly the same as the Method 1, naive dp solution
	// Time: O(n^2)  Space: O(n)
	public int findLongestOptimize(String s) {
		if(s == null) return 0;
		int[] dp = new int[s.length() + 1];
		for(int i = s.length(); i >= 1; i--) {
			dp[i] = 1;
			int mIPlusOneJMinusOne = 0;
			for(int j = i + 1; j <= s.length(); j++) {
				int mIJ; 
				if(s.charAt(i - 1) == s.charAt(j-1)) {
					mIJ = 2 + mIPlusOneJMinusOne;
				} else {
					mIJ = Math.max(dp[j - 1], dp[j]);
				}
				mIPlusOneJMinusOne = dp[j];
				dp[j] = mIJ;
			}
		}
		return dp[s.length()];
	}
	
	public static void main(String[] args) {
		LongestPalindromicSubquence solution = new LongestPalindromicSubquence();
		int findLongest = solution.findLongestOptimize("    ");
		System.out.println(findLongest);
	}
}
