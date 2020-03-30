/**
 * 
 */
package williamsNotebook.easy.trieTree;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月29日 下午2:20:00
* Description:
*   Given a list of strings words representing an English Dictionary, find the longest word in words that can 
*   be built one character at a time by other words in words. If there is more than one possible answer, return 
*   the longest word with the smallest lexicographical order.
*   If there is no answer, return the empty string.
* Example:
* 	Input:  words = ["w","wo","wor","worl", "world"]  Output: "world";
* 	Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
* Assumption:
* 	All the strings in the input will only contain lowercase letters.
* 	The length of words will be in the range [1, 1000].
* 	The length of words[i] will be in the range [1, 30].
*/

public class LongestWordDictionary {

	// Method 1: Trie + DFS(Simluate by stack)
	public String longestWord(String[] words) {
		Trie trie = new Trie();
		for(String word : words) {
			trie.insert(word);
		}
		return dfs(trie.getRoot());
	}
	private String dfs(TrieNode root) {
		String longest = "";
		Deque<TrieNode> stack = new ArrayDeque<TrieNode>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TrieNode node = stack.pop();
			if(node == null) continue;
			if(node.isEnd == true || node == root) {
				if(node != root) {
					if(node.word.length() > longest.length() || (node.word.length() == longest.length() && node.word.compareTo(longest) < 0)) {
						longest = node.word;
					}
				}
				for(TrieNode child:node.children) {
					stack.push(child);
				}
			}
		}
		return longest;
	}
	class Trie{
		TrieNode root;
		public Trie() {
			root = new TrieNode();
		}
		public void insert(String word) {
			TrieNode node = root;
			char[] charArray = word.toCharArray();
			for(char ch : charArray) {
				if(!node.contains(ch)) {
					node.put(ch, node);
				}
				node.get(ch);
			}
			node.isEnd = true;
			node.word = word;
		}
		public TrieNode getRoot() {
			return this.root;
		}
	}
	
	class TrieNode{
		TrieNode[] children;
		boolean isEnd;
		String word;
		public TrieNode() {
			children = new TrieNode[26];
		}
		boolean contains(char ch) {
			return children[ch - 'a'] != null;
		}
		TrieNode get(char ch) {
			return children[ch - 'a'];
		}
		void put(char ch, TrieNode node) {
			this.children[ch - 'a'] = node;
		}
	}
}
