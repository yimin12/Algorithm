/**
 * 
 */
package williamsNotebook.common.prefixTree;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月29日 上午12:24:35
* Description:
* 
*/

public class TrieArray {

	private TrieNodeArray root;
	
	public TrieArray() {
		root = new TrieNodeArray();
	}
	// Inserts a word into the trie
	public void insert(String word) {
		TrieNodeArray p = root;
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			int index = c - 'a';
			if(p.arr[index] == null) {
				TrieNodeArray temp = new TrieNodeArray();
				p.arr[index] = temp;
				p = temp;
			} else {
				p = p.arr[index];
			}
		}
		p.isEnd = true;
	}
	public boolean search(String word) {
		TrieNodeArray p = searchNode(word);
		if(p == null) {
			return false;
		} else {
			if(p.isEnd) return true;
		}
		return false;
	}
	public boolean startsWith(String prefix) {
		TrieNodeArray p = searchNode(prefix);
		if(p == null) {
			return false;
		} else {
			return true;
		}
	}
	public TrieNodeArray searchNode(String s) {
		TrieNodeArray p = root;
		for(int i = 0; i < s.length();i++) {
			char c = s.charAt(i);
			int index = c - 'a';
			if(p.arr[index] != null) {
				p = p.arr[index];
			} else {
				return null;
			}
		}
		if(p == root) return null; // the input s is null or empty
		return p;
	}
}
class TrieNodeArray{
	TrieNodeArray[] arr;
	boolean isEnd;
	public TrieNodeArray() {
		this.arr = new TrieNodeArray[26];
	}
}
