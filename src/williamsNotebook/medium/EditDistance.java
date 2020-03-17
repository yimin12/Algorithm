/**
 * 
 */
package williamsNotebook.medium;

/**
 * @author yimin Huang
 *	
 *	Given two strings S and T, determine if they are both one edit distance apart.
 *	
 * Algorithm Class
 */
public class EditDistance {
	
	// Follow 1 : determine if they are both at most one edit distance apart
	// Three different cases:
	// 	1. Delete: S = "abcdeX", T = "abcde"
	// 	2. Modify: S = "abcde", T = "abXde"
	//	3. Insert: S = "abcde", T = "abcXde"
	// Time ~ O(N), Space ~ O(1)
	public boolean oneEditDistance(String s, String t) {
		if(s == null && t == null) return true;
		if(s == null || t == null) return false;
		int n = s.length(), m = t.length();
		// Three different cases
		if(n == m) {
			return isOneChangeDistance(s, t);
		} else if(n - m == 1) {
			return isOneRemoveDistance(s, t);
		} else if(m - n == 1) {
			return isOneRemoveDistance(t, s);
		}
		return false;	
	}
	private boolean isOneChangeDistance(String s, String t) {
		int dif = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == t.charAt(i)) {
				continue;
			}
			dif++;
		}
		return dif <= 1;
	}
	private boolean isOneRemoveDistance(String s, String t) {
		boolean flag = false;
		int slow = 0, fast = 0;
		for(; fast < s.length() - 1; slow++,fast++) {
			if(s.charAt(fast) != t.charAt(slow)) {
				if(flag) return false;
				slow--;
				flag = true;
			}
		}
		return slow == fast ? true : !flag;
	}
	
	// Follow Up 2: Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
	// You have the following 3 operations permitted on a word: Insert, Delete, Replace
	// dp[i][j] represent minimum steps need from s1[0,i] to s2[0,j]
	// base case: dp[0][0] = 0, dp[0][j] = j, dp[i][0] = i
	// Induction rule:
	// 	if s[i] != s[j] : dp[i][j] = min(dp[i-1][j] + 1,dp[i][j-1] + 1, dp[i][j] + 1)
	//  else dp[i][j] = dp[i-1][j-1]
	// 2D DP: Time ~ O(N1*N2), Space ~ O(N1*N2)
	public int minDistance(String s1, String s2) {
		// Assumptions:
		if(s1 == null || s2 == null) return -1;
		int[][] dp = new int[s1.length() + 1][s2.length()+1];
		for(int i = 0; i <= s1.length(); i++) {
			for(int j = 0; j <= s2.length(); j++) {
				if(i == 0 || j == 0) {
					dp[i][j] = (i == 0) ? j: i;
				} else if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = Math.min(dp[i-1][j] + 1, Math.min(dp[i][j-1] + 1, dp[i-1][j-1] + 1));
				}			
			}
		}
		return dp[s1.length()][s2.length()];
	}
	// Optimized the space complexity
	// 2D DP: Time ~ O(N1*N2), Space ~ O(min(m,n)), it can be optimized to O(1)
	public int minDistanceOptimized(String word1, String word2) {
		int n1 = word1.length();
		int n2 = word2.length();
		if(n1 < n2) {
			return minDistance(word2, word1);
		}
		int[] dp = new int[n2 + 1];
		int upperLeft = 0; // to store dp[i-1][j-1]
		// base case
		for(int i = 0; i <= n1; i++) {
			for(int j = 0; j <= n2; j++) {
				int temp = dp[j];
				if(i == 0 || j == 0) {
					dp[j] = (i == 0) ? j: i;
				} else if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[j] = upperLeft;
					upperLeft = temp;
				} else {
					dp[j] = Math.min(dp[j-1], Math.min(upperLeft, dp[j])) + 1;
					upperLeft = temp;
				}
			}
		}
		return dp[n2];
	}
	
	public static void main(String[] args) {
		EditDistance solution = new EditDistance();
		boolean oneEditDistance = solution.oneEditDistance("a", "");
		System.out.println(oneEditDistance);
		
		int minDistance = solution.minDistanceOptimized("abcd", "abcdefcd");
		System.out.println(minDistance);
		
	}
}
