/**
 * 
 */
package williamsNotebook.medium;

/**
 * @author yimin Huang
 * 
 *		Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *		For example,
 *		Given: s1 = "aabcc", s2 = "dbbca",
 *		When s3 = "aadbbcbcac", return true.
 *		When s3 = "aadbbbaccc", return false.
 *
 * Algorithm Class
 */
public class InterleavingString {
	
	// solution 1: DFS to traverse all possible situation
	// there are total len(s3) level and each level have two branches
	// Time: O(2^n)
	public boolean isInterleave(String s1, String s2, String s3) {
		if(s1.length() + s2.length() != s3.length()) return false;
		return isInterleave(s1.toCharArray(), 0, s2.toCharArray(), 0, s3.toCharArray(), 0);
	}
	private boolean isInterleave(char[] s1, int n1, char[] s2, int n2, char[] s3, int n3) {
		if(n1 == s1.length && n2 == s2.length && n3 == s3.length) return true;
		if(n1 == s1.length) return (s2[n2] == s3[n3] && isInterleave(s1, n1, s2, n2 + 1, s3, n3 + 1));
		if(n2 == s2.length) return (s1[n1] == s3[n3] && isInterleave(s1, n1 + 1, s2, n2 , s3, n3 + 1));
		return (s2[n2] == s3[n3] && isInterleave(s1, n1, s2, n2 + 1, s3, n3 + 1))
			||	(s1[n1] == s3[n3] && isInterleave(s1, n1 + 1, s2, n2 , s3, n3 + 1));
	}
	// Solution 2: Dynamic Programming 
	// dp[i][j] represent whether it match the first (i+j) chars of s3
	// base case: dp[0][0] = true;
	// induction rule:
	// if s3.charAt(i+j) == s1.charAt(i), dp[i][j] = dp[i-1][j]
	// if s3.charAt(i+j) == s2.charAt(j), dp[i][j] = dp[i][j-1]
	public boolean isInterleaveII(String s1, String s2, String s3) {
		if(s1 == null && s2 == null && s3 == null) return true;
		if(s1 == null || s2 == null || s3 == null || s1.length() + s2.length() != s3.length()) return false;
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true;
		// initialize the first row and first column
		for(int i = 1; i <= s1.length(); i++) {
			if(s1.charAt(i-1) == s3.charAt(i-1)) {
				dp[i][0] = dp[i-1][0];
 			}
		}
		for(int j = 1; j <= s2.length(); j++) {
			if(s2.charAt(j-1) == s3.charAt(j-1)) {
				dp[0][j] = dp[0][j-1];
			}
		}
		for(int i = 1; i <= s1.length(); i++) {
			for(int j = 1; j <= s2.length(); j++) {
				dp[i][j] = (dp[i-1][j] == true && s1.charAt(i-1) == s3.charAt(i + j - 1)) 
						|| (dp[i][j-1] == true && s2.charAt(j-1) == s3.charAt(i + j - 1));
			}
		}
		return dp[s1.length()][s2.length()];
	}
	// Solution 3: Rolling array by optimize the dynamic solution
	// Time ~ O(N1*N2), Space ~ O(min{N1, N2})
	public boolean isInterleaveIIOptimized(String s1, String s2, String s3) {
		if(s1 == null && s2 == null && s3 == null) return true;
		if(s1 == null || s2 == null || s3 == null || s1.length() + s2.length() != s3.length()) return false;
		int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
		if(n1 < n2) return isInterleaveIIOptimized(s2, s1, s3);
		boolean[] match = new boolean[n2 + 1]; // generate the smaller capacity
		match[0] = true;
		for(int j = 1; j <= n2; j++) {
			match[j] = match[j-1] && s1.charAt(j-1) == s3.charAt(j-1);
		}
		for(int i = 1; i <= n1; i++) {
			match[0] = match[0] == true && s1.charAt(i-1) == s3.charAt(i-1);
			for(int j = 1; j <= n2; j++) {
				match[j] = (match[j-1] == true && s2.charAt(j-1) == s3.charAt(i+j-1)) || (match[j] == true && s1.charAt(i-1) == s3.charAt(i+j-1));
			}
		}
		return match[n2];
	}
		
	public static void main(String[] args) {
		InterleavingString solution = new InterleavingString();
		boolean interleave = solution.isInterleaveIIOptimized("aabcc", "dbbca", "aadbbcbcac");
		System.out.println(interleave);
	}
}
