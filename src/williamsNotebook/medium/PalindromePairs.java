/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月29日 下午2:54:23
* Description:
*  	Given a list of unique words, find all pairs of distinct indices(i, j)in the given list, so that the 
*  	concatenation of the two words, i.e.words[i] + words[j]is a palindrome.
*  	Input: ["abcd","dcba","lls","s","sssll"]
*  	Output: [[0,1],[1,0],[3,2],[2,4]] 
*  	Explanation: 
*  		The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
*/

public class PalindromePairs {

	// Brute Force, base on the palindrome definition
	public List<List<Integer>> palindromePairsBrute(String[] words){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		int n = words.length;
		// you should try left right pair and the right left pair for validate palindrome
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(i == j) {
					continue;
				}
				String candidate = words[i] + words[j];
				if(isPalindrome(candidate)) {
					List<Integer> tmp = new ArrayList<Integer>();
					tmp.add(i);
					tmp.add(j);
					res.add(tmp);
				}
			}
		}
		return res;
	}
	private boolean isPalindrome(String s) {
		if(s.length() < 2) {
			return true;
		}
		int i = 0, j = s.length() - 1;
		while(i < j) {
			if(s.charAt(i) != s.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	// Method 2: Trie
	static class TrieNode{
		TrieNode[] children;
		int index; 
		List<Integer> list;

		public TrieNode() {
			children = new TrieNode[26];
			index = -1;
			list = new ArrayList<Integer>();
		}
	}
	public List<List<Integer>> palindromePairs(String[] words){
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		TrieNode root = new TrieNode();
		for(int i = 0; i < words.length; i++) {
			addWord(root, words[i], i);
		}
		for(int i = 0; i < words.length; i++) {
			search(words, i, root, res);
		}
		return res;
	}
	private void addWord(TrieNode root, String word, int index) {
		for(int i = word.length() - 1; i >= 0; i--) {
			int j = word.charAt(i) - 'a';
			if(root.children[j] == null) {
				root.children[j] = new TrieNode();
			}
			if(isPalindrome(word, 0, i)) {
				root.list.add(index);
			}
			root = root.children[j];
		}
		root.list.add(index);
		root.index = index;
	}
	private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
		for(int j = 0; j < words[i].length(); j++) {
			if(root.index >= 0 && root.index != i && isPalindrome(words[i], j, words[i].length() - 1)) {
				res.add(Arrays.asList(i, root.index));
			}
			root = root.children[words[i].charAt(j) - 'a'];
			if(root == null) return;
		}
		for(int j : root.list) {
			if(i == j) continue;
			res.add(Arrays.asList(i,j));
		}
	}
	private boolean isPalindrome(String word, int i, int j) {
		while(i < j) {
			if(word.charAt(i++) != word.charAt(j--)) return false;
		}
		return true;
	}
}
