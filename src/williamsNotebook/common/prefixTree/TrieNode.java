/**
 * 
 */
package williamsNotebook.common.prefixTree;

/**
 * @author yimin Huang
 *		
 *	implement a trie with insert, search, and startsWith methods.
 *	You may assume that all inputs are consist of lowercase letters a-z.
 *	
 * Algorithm Class
 */
public class TrieNode {

	boolean flag; // whether it is the end of word
	TrieNode[] next;
	// constructor
	public TrieNode() {
		this.flag = false;
		this.next = new TrieNode[26];
	}
}

