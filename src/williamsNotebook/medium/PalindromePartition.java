/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author yimin Huang
 *		
 *		Given a string s, partition s such that every substring of the partition is a palindrome. 
 *		Return all possible palindrome partitioning of s.
 *		For example, given s = "aab", Return [["aa","b"],["a","a","b"]]
 *
 * Algorithm Class
 */
public class PalindromePartition {
	
	// Method 1: partition by pure dfs
	// DFS: Time ~ O(2^N)
	private List<List<String>> listSet = new ArrayList<List<String>>();
	public List<List<String>> partitionDFS(String s){
		if(s == null) return null;
		dfsPartition(s, 0, new ArrayList<String>());
		return listSet;
	}
	private void dfsPartition(String s, int index, ArrayList<String> list) {
		if(index >= s.length()) {
			listSet.add(new ArrayList<String>(list));
		}
		for(int i = index; i < s.length(); i++) {
			if(isPalindrome(s, index, i)) {
				list.add(s.substring(index, i + 1));
				dfsPartition(s, i + 1, list);
				list.remove(list.size() - 1);
			}
		}
	}
	private boolean isPalindrome(String s, int start, int end) {
		int l = start, r = end;
		while(l < r) {
			if(s.charAt(l) != s.charAt(r)) return false;
			l++;
			r--;
		}
		return true;
	}
	// Method 2: Dynamic Programming
	// Insight : Use 2 DPs, DP1 check if a string is a palindrome, DP2 find all palindrome
	// boolean[i][j] represent that whether the substring s[i...j] is palindrome
	public List<List<String>> partition(String s){
		if(s == null) return null;
		int n = s.length();
		boolean[][] dp = new boolean[n][n];
		for(int i = n - 1; i >= 0; i--) {
			for(int j = i; j < n; j++) {
				dp[i][j] = (s.charAt(i) == s.charAt(j) && ((j - i) < 2|| dp[i-1][j+1]));
			}
		}
		// find all partition
		HashMap<Integer, List<List<String>>> map  = new HashMap<Integer, List<List<String>>>();
		for(int i = n - 1; i >= 0; i--) {
			List<List<String>> partition = new ArrayList<List<String>>();
			for(int j = i; j < n; j++) {
				if(dp[i][j]) {
					if(j == n - 1) {
						List<String> list = new ArrayList<String>();
						list.add(s.substring(i, j+1));
						partition.add(list);
					} else { // j < n - 1
						for(List<String> listPartition : map.get(j + 1)) {
							List<String> list = new ArrayList<String>();
							list.add(s.substring(i, j+1));
							list.addAll(listPartition);
							partition.add(list);
						}
					}
				}
			}
			map.put(i, partition);
		}
		return map.get(0);
	}
	
	// Follow Up 2: Given a string s, partition s such that every substring of the partition is a palindrome.
	// Return the minimum cuts needed for a palindrome partitioning of s.
	// For example, given s = "aab", Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
	// DFS: Time ~ O(2^N)
	private int minSize = Integer.MAX_VALUE;
	public int minCutDFS(String s) {
		dfsPartitionI(s, 0, new ArrayList<String>());
		return minSize;
	}
	private void dfsPartitionI(String s, int index, ArrayList<String> list) {
		if(index >= s.length() && minSize > list.size()) {
			minSize = list.size();
		}
		for(int i = index; i < s.length(); i++) {
			if(isPalindrome(s, index, i)) {
				list.add(s.substring(index, i+1));
				dfsPartitionI(s, i+1, list);
				list.remove(list.size() - 1);
			}
		}
	}
	// Method 2: Dynamic Programming 
	// Let d(i) be the min cut of s[i,n-1].
	// Let tab[i][j] = true if s[i, j] is a palindrome.
	// d(i) = min{1 + d(j + 1)}, j s.t. tab[i][j] = true (s[i, j] is a palindrome), i <= j <= n -1, d(n) = -1
	// tab[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i < 2 || tab[i + 1][j - 1] == true);
	public int minCutDP(String s) {
		int n = s.length();
		boolean[][] tab = new boolean[n][n];
		int[] min = new int[n];
		for(int i = n - 1; i >= 0; i--) {
			min[i] = n - i - 1;
			for(int j = i; j < n; j++) {
				tab[i][j] = (s.charAt(i) == s.charAt(j) && (j - i < 2 || tab[i-1][j+1]));
				if(tab[i][j]) min[i] = Math.min(min[i], j + 1 < n ? (1 + min[j+1]) : 0);
			}
		}
		return min[0];
	}
}
