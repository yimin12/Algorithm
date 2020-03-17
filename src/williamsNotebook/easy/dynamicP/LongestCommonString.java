/**
 * 
 */
package williamsNotebook.easy.dynamicP;

import java.util.Arrays;

/**
 * @author yimin Huang
 * 
 *	Write a function to find the longest common prefix string amongst an array of strings.
 *	
 * Algorithm Class
 */
public class LongestCommonString {
	// Time ~ O(N*M) worst case where M is the length of given strs, Space ~ O(N) worst case where N is the average of each string 
	public String longestCommonPrefix(String[] strs) {
	    if (strs.length == 1)   return strs[0];
	    
	    // Compare each two adjacent pairs
	    String prefix = "";
	    int minPrefix = Integer.MAX_VALUE;
	    for (int i = 1; i < strs.length; i++) {            
	        int maxPrefix = 0;
	        // compare two adjacent pairs
	        int m = strs[i - 1].length(), n = strs[i].length();
	        for (int j = 0; j < Math.min(Math.min(m, n), minPrefix); j++) {
	            if (strs[i - 1].charAt(j) == strs[i].charAt(j))     maxPrefix++;
	            else                                                break;
	        }
	        if (maxPrefix < minPrefix) {
	            minPrefix = maxPrefix;
	            prefix = strs[i - 1].substring(0, minPrefix); // this will cause extra space
	        }
	    }
	    return prefix;
	}
	
	// Follow Up 1: DP without optimizing the space Time= O(MN), Space = O(M*N) you can do the space optimization
	public String longestCommonSubarray(String target, String pattern) {
		// In this question, it requires to return longest string
		if(target == null || pattern == null) throw new IllegalArgumentException("The input is wrong");
		if(target.length() * pattern.length() == 0) return null;
		int[][] dp = new int[target.length()][pattern.length()];
		int longest = 0, start = 0;
		for(int i = 0; i < target.length(); i++) {
			for(int j = 0; j < pattern.length(); j++) {
				if(target.charAt(i) == pattern.charAt(j)) {
					// you should fill out the left boundary and up boundary with 1 
					if(i == 0 || j == 0) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = dp[i-1][j-1] + 1;
 					}
					if(dp[i][j] > longest) {
						longest = dp[i][j];
						start = i - longest + 1;
					}
				}
			}
		}
		return target.substring(start, start + longest);
	}
	
	// Follow Up 2: DP without optimizing the space Time= O(MN), Space = O(M*N) you can do the space optimization
	public int longestCommonSubsequence(String str1, String str2) {
		// return the longest value of subsequence
		if(str1 == null || str2 == null) throw new IllegalArgumentException("The input is wrong");
		if(str1.length() * str2.length() == 0) return 0;
		int[][] dp = new int[str1.length()+1][str2.length()+1];
		// "add 1 index" will make the base case easier to handle, the 0th row and 0th col will be all zero
		for(int i = 1; i <= str1.length(); i++) {
			for(int j = 1; j <= str2.length(); j++) {
				if(str1.charAt(i-1) == str2.charAt(j - 1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[str1.length()][str2.length()];
		
	}
	
	// Follow Up 3: Given a string S and a string T, count the number of distinct subsequences of T in S. 
	// A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the 
	// characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
	// Here is an example:
	// S = "rabbbit", T = "rabbit", Return 3.
	// 1. 2-d DP: Time ~ O(ST), Space ~ O(ST)
	// Let dp(i, j) be the number of distinct subsequences of T[0, j - 1] in S[0, i - 1].
	// base case: dp[0][j] = 0; and dp[i][0] = 1
	// induction rule:	dp[i][j] = dp[i-1][j] + (s.charAt(i-1) == t.charAt(j-1) ? dp[i-1][j-1] : 0); 
	public int numDistinct(String s, String t) {
		if(s == null || t == null || t.length() > s.length()) return 0;
		int m = s.length(), n = t.length();
		int[][] dp = new int[m+1][n+1];
		for(int i = 0; i <= m; i++) {
			for(int j = 0; j <= Math.min(i, n); j++) {
				if(i == 0 && j == 0) dp[i][j] = 1;
				else if(j == 0) dp[i][j] = 1;
				else {
					dp[i][j] = dp[i-1][j] + (s.charAt(i-1) == t.charAt(j-1) ? dp[i-1][j-1] : 0); 
				}
			}
		}
		return dp[m][n];
	}
	
	// Follow Up 4 : Given a string S, count the number of distinct, non-empty subsequences of S
	// Since the result may be large, return the answer modulo 10^9 + 7.
	// Input: "abc"  Output: 7
	// Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".
	// Time Complexity: O(N)O(N), where NN is the length of S. Space Complexity: O(N)O(N). It is possible to adapt this solution to take O(1)O(1) space
	public int distinctSubsequence(String s) {
		int MOD = 1_000_000_007;
		int n = s.length();
		int dp[] = new int[n+1];
		dp[0] = 1;
		int[] dict = new int[26];
		Arrays.fill(dict, -1);
		
		for(int i = 0; i < n; i++) {
			int x = s.charAt(i) - 'a';
			dp[i+1] = dp[i] * 2	% MOD;
			if(dict[x] > 0) dp[i+1] -= dp[dict[x]];
			dp[i+1]%= MOD;
			dict[x] = i;
		}
		dp[n]--;
		if(dp[n] < 0) dp[n] += MOD;
		return dp[n];
	}
	 
	
	
	public static void main(String[] args) {
		LongestCommonString solution = new LongestCommonString();
		String longestCommonPrefix = solution.longestCommonPrefix(new String[] {"abc","abcd","abcdef"});
		System.out.println(longestCommonPrefix);
		
		// test for commonsubarray
		int longestCommonSubarray = solution.longestCommonSubsequence("student", "sweden");
		System.out.println(longestCommonSubarray);
		
		int numDistinct = solution.numDistinct("rabbbit","rabbit");
		System.out.println(numDistinct);
	}
}
