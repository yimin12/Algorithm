/**
 * 
 */
package williamsNotebook.easy.dynamicP;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年4月6日 下午4:00:34
* Description:
* 	Given an input string (s) and a pattern (p), implement regular expression matching with support for'.'and'*'
* 	'.' Matches any single character.
* 	'*' Matches zero or more of the preceding element.
* Input: s = "mississippi"	p = "mis*is*p*." return false
* Input: s = "aab"  p = "c*a*b", return true
*/
public class RegularExpressionMatching {

	// Assumptions: '*' will never be the first character
	// dp[i][j] represent the first i characters in s matches first j characters in p
	// base case dp[0][0] = true; dp[0][i] = dp[0][-1], but there is a special case (a*) in p
	// 		so 	dp[0][i] = dp[0][i-2] if pattern[i-1] == '*'
	// Induction rule:
	// 	if p.charAt(j) == s.charAt(i), dp[i][j] = dp[i-1][j-1];
	// 	if p.charAt(j) == '.', dp[i][j] = dp[i-1][j-1];
	// 	if p.charAt(j) == '*', two case possible
	//		case 1: if p.charAt(j-1) != s.charAt(i), dp[i][j] = dp[i][j-2] ("*" counts as empty)
	//		case 2: if p.charAt(j-1) == s.charAt(i) or p.charAt(j-1) = '.';
	//			case 2.1 dp[i][j] = dp[i-1][j], count as multiple a
	//			case 2.2 dp[i][j] = dp[i-1][j-1], count as single a
	//			case 2.3 dp[i][j] = dp[i][j-2] , count as empty
	public boolean isMatch(String s, String p) {
		if(s == null || p == null) return true;
		char[] text = s.toCharArray();
		char[] pattern = p.toCharArray();
		int m = text.length;
		int n = pattern.length;
		boolean[][] dp = new boolean[m+1][n+1];
		dp[0][0] = true;
		
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(pattern[j-1] == '.' || pattern[j-1] == text[i-1]) {
					// case 1 and case 2
					dp[i][j] = dp[i-1][j-1];
				} else if(pattern[j-1] == '*') {
					dp[i][j] = dp[i][j-2];
					if(pattern[j-2] == '.' || pattern[j-2] == text[i-1]) {
						dp[i][j] = dp[i][j] || dp[i-1][j] || dp[i-1][j-1];
					}
				} else {
					dp[i][j] = false;
				}
			}
		}
		return dp[m][n];
	}
}
