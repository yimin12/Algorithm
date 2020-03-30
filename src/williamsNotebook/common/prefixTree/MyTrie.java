/**
 * 
 */
package williamsNotebook.common.prefixTree;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月28日 下午11:27:52
* Description:
*/
public class MyTrie {

	private MyTrieNode root;
	public MyTrie() {
		root = new MyTrieNode();
	}
	
	// Insert a word into the trie;
	public void insert(String word) {
		MyTrieNode node = root;
		for(int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			if(!node.containsKey(currentChar)) {
				node.put(currentChar, new MyTrieNode());
			}
			node = node.get(currentChar);
		}
		node.setEnd();
	}
	// returns the node where search ends
	private MyTrieNode searchPrefix(String word) {
		MyTrieNode node = root;
		for(int i = 0; i < word.length(); i++) {
			char curLetter = word.charAt(i);
			if(node.containsKey(curLetter)) {
				node = node.get(curLetter);
			} else {
				return null;
			}
		}
		return node;
	}
	// Returns if the word is in the trie
	public boolean search(String word) {
		MyTrieNode node = searchPrefix(word);
		return node != null && node.isEnd();
	}
	// Returns if there is any word in the trie that starts with the given prefix
	public boolean startsWith(String prefix) {
		MyTrieNode node = searchPrefix(prefix);
		return node != null;
	}
	
}
