/**
 * 
 */
package williamsNotebook.easy.dynamicP;

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
	
	// Method 1: DP without optimizing the space Time= O(MN), Space = O(M*N) you can do the space optimization
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
	
	// Method 1: DP without optimizing the space Time= O(MN), Space = O(M*N) you can do the space optimization
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
	public static void main(String[] args) {
		LongestCommonString solution = new LongestCommonString();
		String longestCommonPrefix = solution.longestCommonPrefix(new String[] {"abc","abcd","abcdef"});
		System.out.println(longestCommonPrefix);
		
		// test for commonsubarray
		int longestCommonSubarray = solution.longestCommonSubsequence("student", "sweden");
		System.out.println(longestCommonSubarray);
	}
}
