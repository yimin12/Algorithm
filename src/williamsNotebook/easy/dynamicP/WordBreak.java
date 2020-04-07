/**
 * 
 */
package williamsNotebook.easy.dynamicP;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author yimin Huang
 *
 *	Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *	For example, given s = "leetcode", dict = ["leet", "code"]. Return true because "leetcode" can be segmented as "leet code"
 *	
 * Algorithm Class
 */
public class WordBreak {

	//	 左大段，右小段
	// 1-d DP: Time ~ O(N^2), Space ~ O(N)
	public boolean wordBreakI(String s, Set<String> dict) {
		int n = s.length();
		boolean dp[] = new boolean[s.length()+1];
		dp[0] = true;
		for(int i = 1; i <= s.length(); i++) {
			for(int j = 0; j < i; j++) {
				if(dp[j] == true && dict.contains(s.subSequence(i, j))) {
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}
	// Version 2: Optimize with Max Length constraint
	private int getMaxLength(List<String> wordDict) {
		int max = 0;
		for(String word : wordDict) {
			if(word.length() > max) {
				max = word.length();
			}
		}
		return max;
	}
	public boolean wordBreakI(String s, List<String> wordDict) {
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		int maxLen = getMaxLength(wordDict);
		for(int i = 1; i <= s.length(); i++) {
			for(int j = i - 1; j >= 0 && i - j <= maxLen; j--) {
				dp[i] = dp[j] && wordDict.contains(s.subSequence(j, i));
				if(dp[i]) break;
			}
		}
		return dp[s.length()];
	}
	// Follow Up 2: Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word
	// Return all such possible sentences. For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
	// A solution is ["cats and dog", "cat sand dog"].
	// Method 1: DFS and DP
	public List<String> wordBreakII(String s, Set<String> dict){
		int n = s.length();
		boolean dp[] = new boolean[s.length()+1];
		dp[0] = true;
		for(int i = 1; i <= s.length(); i++) {
			for(int j = 0; j < i; j++) {
				if(dp[j] == true && dict.contains(s.subSequence(i, j))) {
					dp[i] = true;
					break;
				}
			}
		}
		List<String> listSet = new ArrayList<String>();
		if(dp[n]) dfs(s, 0, dict, new StringBuilder(), listSet);
		return listSet;
	}
	private void dfs(String s, int index, Set<String> dict, StringBuilder sb, List<String> listSet) {
		if(s.length() == index) {
			listSet.add(sb.toString());
		} else {
			for(int i = index + 1; i <= s.length(); i++) {
				if(dict.contains(s.substring(index, i))) {
					int len = sb.length();
					if(len != 0) sb.append(" ");
					sb.append(s.substring(index, i));
					dfs(s, i, dict, sb, listSet);
					sb.delete(len, sb.length());
				}
			}
		}
	}
}
