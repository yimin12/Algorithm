/**
 * 
 */
package williamsNotebook.medium;


/**
 * @author yimin Huang
 *
 *		Design a data structure that supports the following two operations:
 *		void addWord(word)
 *		bool search(word)
 *		search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 *		For example:
			addWord("bad")
			addWord("dad")
			addWord("mad")
			search("pad") -> false
			search("bad") -> true
			search(".ad") -> true
			search("b..") -> true
		Note: You may assume that all words are consist of lowercase letters a-z.
		Hint: You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.
		
 * Algorithm Class
 */
public class WordDictionary {
	
	// Add: Time ~ O(L), Search: Time ~ O(L) to O(RL); Space ~ O(RN) to O(RNL) where R = 26
	// Use tire to store dictionary: addWord() and search() are similar to insert() and search() in Trie().
	// Only difference is to implement the regular expression '.' here:
	private final static int R = 26;
	private TrieNode root;
	
	// R-Way trie Node
	private class TrieNode {
		boolean eow;
		TrieNode[] next;
		public TrieNode() {
			eow = false;
			next = new TrieNode[R];
		}
	}
	public WordDictionary() {
		root = new TrieNode();
	}
	public void addWord(String word) {
		root = put(root, word, 0);
	}
	// recursive way to add the trie tree
	private TrieNode put(TrieNode x, String s, int d) {
		if(x == null) x = new TrieNode();
		if(d == s.length()) {
			x.eow = true;
			return x;
		}
		int c = s.charAt(d) - 'a';
		x.next[c] = put(x.next[c], s, d+1);
		return x;
	}
	public boolean search(String word) {
		TrieNode x = get(root, word, 0);
		if(x == null) return false;
		else return x.eow;
	}
	private TrieNode get(TrieNode root, String s, int index) {
		if(root == null) return null;
		if(index == s.length()) return root;
		// case 1: if encounter '.' , try every possibilities
		if(s.charAt(index) == '.') {
			for(int c = 0; c < R; c++) {
				TrieNode y = get(root.next[c], s, index + 1);
				if(y != null && y.eow) return y;
			}
			return null;
		} else {
			// case 2: check whether it is in the trie tree.
			int c = s.charAt(index) - 'a';
			return get(root.next[c], s, index + 1);
					
		}
	}
}
