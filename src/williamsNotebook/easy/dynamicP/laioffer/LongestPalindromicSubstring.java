/**
 * 
 */
package williamsNotebook.easy.dynamicP.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��27�� ����10:26:00
* Description:
* 	Given a string s, find the longest palindromic substring in s. 
* 	You may assume that the maximum length of s is 1000.
* Examples:
* 	Input : "babad"
* 	Output : "bab"
* 	Node : "aba" is also a valid answer
* 	
* 	Input : "cbbd"
* 	Output : "bb"
*/

public class LongestPalindromicSubstring {
	public String longestPalindrome(String s) {
		int n = s.length();
		String string = null;
		int palindromeStartIndex = 0;
		int maxLen = 0;
//		dp[i][j] indicates whether substring s start at index i and ending j is palindrome
		boolean[][] dp = new boolean[n][n];
		
		for(int i = n - 1; i >= 0; i--) {
			for(int j = i; i < n; j++) {
//				Corner case: i=j; should  return true;
//				Check whether substring between(i, j) is palindrome, and the least length of palindrome is 2 
				if(s.charAt(i) == s.charAt(j)) {
					dp[i][j] = (j-i < 3) || dp[i+1][i-1];
				}
//				determine whether should update the maxLen
				if(dp[i][j] && (j-i+1>maxLen)) {
					palindromeStartIndex = i;
					maxLen = j-i+1;
				}
			}
		}
		return s.substring(palindromeStartIndex, palindromeStartIndex+maxLen);
		
	}
}
