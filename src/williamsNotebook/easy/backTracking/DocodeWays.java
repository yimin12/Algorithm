/**
 * 
 */
package williamsNotebook.easy.backTracking;

import java.util.Arrays;

/**
 * @author yimin Huang
 *	
 *		A message containing letters from A-Z is being encoded to numbers using the following mapping
 *		'A' -> 1, 'B' ->2 ..... 'Z' -> 26
 *		Given an encoded message containing digits,determine the total number of ways to decode it.
 *		Example:
 *			encode_msg = "12", it can represented as "AB" or "L", thus, number of ways to decode is 2
 * Algorithm Class
 */
public class DocodeWays {
	
	// Solution 1: Recusion (TLE) idea, find all possible solution for decoding in the current level and return
	// input "1234"
	// 			1				12
	//		   / \			    / \
	// 		  2  23			   3  34
	//		 / \   \		  /	
	// 		3	34  4		 4
	//     /
	//	  4
	// Time: O(2^n)  Space: O(n)
	public int numDecodings(String s) {
		// sanity check
		if(s == null || s.length() == 0) return 0;
		return numDecodings(s.toCharArray(), 0);
	}
	private int numDecodings(char[] array, int level) {
		// base case
		if(level == array.length) {
			return 1;
		}
		int ways = 0;
		if(array[level] != '0') {
			ways += numDecodings(array, level + 1);
		}
		if(validEncoding(array, level)) {
			ways += numDecodings(array, level + 2);
		}
		return ways;
	}
	// check if array[start] and array[start+1] forms a valid encoding
	private boolean validEncoding(char[] array, int start) {
		if(start + 1 >= array.length) return false;
		if(array[start] == '1') {
			return true;
		}
		if(array[start] == '2' && array[start+1] - '0' <= 6) {
			return true;
		}
		return false;
	}
	// Solution 2: Recursion (TLE) or dfs with memorization
	// Time: O(n)  Space: O(n)
	public int numDecodingsWithMemorization(String s) {
		if(s == null || s.length() == 0) return 0;
		int[] m = new int[s.length() + 1];
		Arrays.fill(m, -1);
		return numDecodingsWithMemorization(s.toCharArray(), 0, m);
	}
	private int numDecodingsWithMemorization(char[] array, int level, int[] m) {
		if(m[level] != -1) {
			return m[level];
		}
		if(level == array.length) {
			m[level] = 1;
			return 1;
		}
		int ways = 0;
		if(array[level] != '0') {
			ways += numDecodingsWithMemorization(array, level + 1, m);
		}
		if(validEncoding(array, level)) {
			ways += numDecodingsWithMemorization(array, level + 2, m);
		}
		// remeber the ways number in current level
		m[level] = ways;
		return ways;
	}	
	// Solution 3: Bottom Up DB  
	// dp[i] : the valid decoding ways from s[i...n] 
	// Base Case: dp[n] = 1
	// Induction rule: 
	// 	dp[i] = dp[i+1] + dp[i+2]  (if corresponding situation is valid)
	//  Case 1 : dp[i+1] if s.charAt(i) is within '1' to '9'
	//	Case 2 : dp[i+2] if s.charAt(i) to s.charAt(i+1) froms "10" - "26"
	// Time: O(n)  Space: O(n) can be optimized to O(1)
	public int numDecodingDP(String s) {
		if(s == null || s.length() == 0) return 0;
		int[] dp = new int[s.length()];
		dp[s.length()-1] = 1;
		for(int i = s.length() - 2; i >= 0; i--) {
			if(s.charAt(i) != '0') {
				dp[i] += dp[i+1];
			}
			if(i + 2 < s.length() && validEncoding(s.toCharArray(), i)) {
				dp[i] += dp[i+2];
			}
		}
		System.out.println(Arrays.toString(dp));
		return dp[0];
	}
	// Solution 4: dp Optimization for space, Logic is same as Solution 3
	// Time: O(n)  Space: O(1)
	public int numDecodingPDOptimized(String s) {
		if(s == null || s.length() == 0) return 0;
		// base case
		int dp = 1;
		int dpIPlusTwo = 1;
		for(int i = s.length() - 1; i >= 0; i--) {
			int temp = 0;
			if(s.charAt(i) != '0') {
				temp += dp;
			}
			if(i + 2 < s.length() && validEncoding(s.toCharArray(), i)) {
				temp += dpIPlusTwo;
				dpIPlusTwo = dp;
			}
			dp = temp;
		}
		return dp;
	}
	public static void main(String[] args) {
		DocodeWays solution = new DocodeWays();
		int numDecodings = solution.numDecodings("12345");
		System.out.println(numDecodings);
	}
}
