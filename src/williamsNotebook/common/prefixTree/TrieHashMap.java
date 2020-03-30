/**
 * 
 */
package williamsNotebook.common.prefixTree;

import java.util.HashMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月28日 下午11:52:57
* Description:
* 	A trie node should contains the character, its children the flag that marks if it is a leaf node. You can use this diagram to 
*	to walk though the java solution
*/

public class TrieHashMap {

	private TrieNodeHash root;
	
	public TrieHashMap() {
		root = new TrieNodeHash();
	}
	
	//Insert a word into the trie
	public void insert(String word) {
		HashMap<Character, TrieNodeHash> children = root.children;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			TrieNodeHash t;
			if(children.containsKey(c)) {
				t = children.get(c);
			} else {
				t = new TrieNodeHash(c);
				children.put(c, t);
			}
			children = t.children;
			// Set leaf node 
			if(i == word.length() - 1) {
				t.isLeaf = true;
			}
		}
	}
	// Returns if the word is in the trie
	public boolean search(String word) {
		TrieNodeHash t = searchNode(word);
		if(t != null && t.isLeaf) {
			return true;
		} else {
			return false;
		}
	}
	
	public TrieNodeHash searchNode(String str) {
		HashMap<Character, TrieNodeHash> children = root.children;
		TrieNodeHash t = null;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(children.containsKey(c)) {
				t = children.get(c);
				children = t.children;
			} else {
				return null;
			}
		}
		return t;
	}
}

class TrieNodeHash {
	
	char c;
	HashMap<Character, TrieNodeHash> children = new HashMap<Character, TrieNodeHash>();
	boolean isLeaf;
	
	public TrieNodeHash() {}
	
	public TrieNodeHash(char c) {
		this.c = c;
	}
}