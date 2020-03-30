/**
 * 
 */
package williamsNotebook.common.prefixTree;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月28日 下午10:10:00
* Description:
* 	Assumption: We may assume that all inputs consist of lowercase letters a - z
*/
public class MyTrieNode {

	private MyTrieNode[] links;
	private final int R = 26;
	private boolean isEnd;
	public MyTrieNode() {
		links = new MyTrieNode[R];
	}
	public  boolean containsKey(char ch) {
		return links[ch - 'a'] != null;
	}
	public MyTrieNode get(char ch) {
		return links[ch - 'a'];
	}
	public void put(char ch, MyTrieNode node) {
		links[ch - 'a'] = node;
	}
	public void setEnd() {
		isEnd = true;
	}
	public boolean isEnd() {
		return isEnd;
	}
}
