/**
 * 
 */
package williamsNotebook.common.prefixTree;

/**
 * @author yimin Huang
 *
 * Algorithm Class
 */
public class Trie {
	
	private TrieNode root;
	public Trie() {
		root = new TrieNode();
	}
	public void insert(String word) {
		root = put(root, word, 0);
	}
	private TrieNode put(TrieNode x, String str, int index) {
		if(x == null) x = new TrieNode();
		TrieNode cur = x;
		while(index != str.length()) {
			int c = str.charAt(index) - 'a';
			cur.next[c] = new TrieNode();
			cur = cur.next[c];
			index++;
		}
		return x;
	}
	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode x = get(root, word, 0);
		if(x == null) return false;
		else return true;
	}
	// use recursion way to get 
	private TrieNode get(TrieNode x, String word, int index) {
		if(x == null) return null;
		if(index == word.length()) return x;
		int c = word.charAt(index) - 'a';
		return get(x.next[c], word, index+1);
	}
	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode x = get(root, prefix, 0);
		if(x == null) return false;
		else return true;
	}
}
