package williamsNotebook.medium;

/**
 * @author yimin Huang
 *	Implement regular expression matching with support for '.' and '*'. '.' Matches any single character. 
 *	'*' Matches zero or more of the preceding element. The matching should cover the entire input string (not partial).
 *	
 *	The function prototype should be:
 *		bool isMatch(const char *s, const char *p)    (p is the regular expression)
 *	Some examples:
		isMatch("aa","a") → false
		isMatch("aa","aa") → true
		isMatch("aaa","aa") → false
		isMatch("aa", "a*") → true
		isMatch("aa", ".*") → true
		isMatch("ab", ".*") → true
		isMatch("aab", "c*a*b") → true
		
 * Algorithm Class
 */
public class ExpressionMatching {

	// Method 1: dfs to traverse all the possible cases
	// Time: worst case O(N^2+M^2) because you use the substring interface, Space : O(
	public boolean isMathch(String s, String p) {
		// if we match every single char in string p, return s.length
		if(p.length() == 0) return s.length() == 0;
		// trick '*' carefully because it must need the previous information
		// case 1: if the second char of pattern is not '*'
		if(p.length() == 1 || p.charAt(1) != '*') {
			// s[i] == p[j] || p[j] == '.' must hold for matching
			if(s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) return isMathch(s.substring(1), p.substring(1));
			else return false;
		} else {
			// case 2: if the second char of pattern is '*' and pattern.length != 1
			while(s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
				// 1st run: * is deletion, >= 2nd run: * is repetition; there is redundancy here!!
				if(isMathch(s, p.substring(2))) return true;
				s = s.substring(1);
			}
			return isMathch(s, p.substring(2));
		}
	}

	// Method 2: Dynamic Programming to traverse all the match
	// Time: O(M*N) Space: O(M*N)
	public boolean isMatchII(String s, String p) {
		if(s == null || p == null || s.length() * p.length() == 0) return false;
		int m = s.length(), n = p.length();
		boolean[][] dp = new boolean[m+1][n+1];
		dp[0][0] = true;
		// base case
		for(int j = 1; j <= n; j++) {
			if(p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j-2]; // * is deletion
			}
		}
		// induction rule:
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				if(p.charAt(j-1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
					dp[i][j] = dp[i - 1][j - 1];
				} else if(p.charAt(j - 1) == '*') {
					// case one : deletion for previous char
					if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
						dp[i][j] = dp[i][j-2] || dp[i-1][j];
					} else {
						dp[i][j] = dp[i][j - 2];
					}
				}
			}
		}
		return dp[m][n];
	}
	
	// Method 3: Space optimization for Method 2
	// Time ~ O(MN), Space ~ O(N)
	public boolean isMatchIII(String s, String p) {
		int m = s.length(), n = p.length();
		boolean[] dp = new boolean[n + 1];
		dp[0] = true;
		// initialize the first row
		for(int j = 1; j <= n; j++) {
			if(p.charAt(j - 1) == '*') {
				dp[j] = dp[j - 2];
			}
		}
		// fill up the table
		for(int i = 1; i <= m; i++) {
			// prev stores d[i-1][j-1]
			boolean prev = dp[0];
			 // add this line for 1D reduction!!
			dp[0] = false;
			for(int j = 1; j <= n; j++) {
				// curr stores d[i-1][j]
				boolean curr = dp[j];
				if(p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') dp[j]=prev;
				else if(p.charAt(j - 1) == '*') {
					if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
						// * is deletion or repetition
						dp[j] = dp[j - 2] || curr;
					} else {
						// * is deletion
						dp[j] = dp[j - 2];
					}
				} else {
					// add this line for 1D reduction!!
					dp[j] = false;
				}
				prev = curr;
			}
		}
		return dp[n];
	}
	
	/*
	 * Implement wildcard pattern matching with support for '?' and '*'. '?' Matches
	 * any single character. '*' Matches any sequence of characters (including the
	 * empty sequence).
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * The function prototype should be: bool isMatch(const char *s, const char *p)
	 * (p is the wildcard expression)
	 * 	Some examples:
			isMatch("aa","a") → false
			isMatch("aa","aa") → true
			isMatch("aaa","aa") → false
			isMatch("aa", "*") → true
			isMatch("aa", "a*") → true
			isMatch("ab", "?*") → true
			isMatch("aab", "c*a*b") → false
	 */
	// Method 1: 2D dp, Time ~ O(SP), Space ~ O(SP)
	public boolean isMatchIV(String s, String p) {
	    int lenS = s.length(), lenP = p.length();

	    // deal with the exceeding time limit case
	    int count = 0;
	    for (int i = 0; i < lenP; i++) {
	        if (p.charAt(i) != '*') count++;
	    }
	    if (count > lenS) return false;
	    
	    boolean[][] d = new boolean[lenS + 1][lenP + 1]; // i, j are the lengths of s[0..i-1] and p[0..j-1]
	    d[0][0] = true;
	    // initialize the first row
	    for (int j = 1; j <= lenP; j++) {
	        if (p.charAt(j - 1) == '*') d[0][j] = d[0][j - 1];
	    }
	    // fill up the table
	    for (int i = 1; i <= lenS; i++) {
	        for (int j = 1; j <= lenP; j++) {
	            if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?')
	                d[i][j] = d[i - 1][j - 1];
	            else if (p.charAt(j - 1) == '*') {
	                for (int k = 0; k <= i; k++) {
	                    if (d[k][j - 1] == true) {
	                        d[i][j] = true;
	                        break;
	                    }
	                }
	            }
	        }
	    }
	    return d[lenS][lenP];
	}
	
//	1-d DP（滚动数组）: Time ~ O(SP), Space ~ O(P)
	public boolean isMatchV(String s, String p) {
	    int lenS = s.length(), lenP = p.length();
	    
	    // deal with the exceeding time limit case
	    int count = 0;
	    for (int i = 0; i < lenP; i++) {
	        if (p.charAt(i) != '*') count++;
	    }
	    if (count > lenS) return false;
	    
	    boolean[] d = new boolean[lenP + 1];
	    boolean[] isTrue = new boolean[lenP + 1]; // isTrue[j-1] == T iff any d[m][j-1] == T (0 <= m <= i) 
	    d[0] = true;
	    isTrue[0] = true;
	    // initialize the first row
	    for (int j = 1; j <= lenP; j++) {
	        if (p.charAt(j - 1) == '*') d[j] = d[j - 1];
	        if (d[j] == true)   isTrue[j] = true;
	    }
	    // fill up the table
	    for (int i = 1; i <= lenS; i++) {
	        boolean prev = d[0];    // prev stores d[i - 1][j - 1]
	        d[0] = false;   // add this line for 1D reduction!!
	        for (int j = 1; j <= lenP; j++) {
	            boolean curr = d[j];    // curr stores d[i - 1][j]
	            if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '?')
	                d[j] = prev;
	            else if (p.charAt(j - 1) == '*') {
	                if (isTrue[j - 1] == true)  d[j] = true;
	            } else
	                d[j] = false;   // add this line for 1D reduction!!
	            if (d[j] == true)   isTrue[j] = true;
	            prev = curr;
	        }
	    }
	    return d[lenP];
	}
	
	public static void main(String[] args) {
		ExpressionMatching solution = new ExpressionMatching();
		System.out.println(solution.isMatchIII("ac", "ab*"));
	}
}
