/**
 * 
 */
package williamsNotebook.easy.trieTree;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import williamsNotebook.easy.kthComparator.TopK;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月29日 下午4:13:12
* Description:
* 	Build tries from a list of <word, freq> pairs. Save top 10 for each node.
*   Input:  
* 		<"abc", 2>,  <"ac", 4>,  <"ab", 9> Output:<a[9,4,2]<b[9,2]<c[2]<>>c[4]<>>> 
*	Explanation:
*	        Root
             / 
           a(9,4,2)
          /    \
        b(9,2) c(4)
       /
     c(2)
*/

public class TrieService {

	static class TrieNode{
		public NavigableMap<Character, TrieNode> children;
		public List<Integer> topTen; 
		public TrieNode() {
			children = new TreeMap<Character, TrieNode>();
			this.topTen = new ArrayList<Integer>();
		}
	}
	private TrieNode root;
	public TrieService() {
		root  = new TrieNode();
	}
	public TrieNode getRoot() {
		return root;
	}
	public void insert(String word, int frequency) {
		TrieNode cur = root;
		int n = word.length();
		for(int i = 0; i < n; i++) {
			Character c = word.charAt(i);
			if(!cur.children.containsKey(c)) {
				cur.children.put(c, new TrieNode());
			}
			cur = cur.children.get(c);
			addFrequency(cur.topTen, frequency);
		} 
	}
	public void addFrequency(List<Integer> list, int frequency) {
		list.add(frequency);
		int n = list.size();
		int i = n - 1;
		// maintain the order in descending order, Worst case is O(len(words))
		while(i > 0) {
			if(list.get(i) > list.get(i - 1)) {
				int temp = list.get(i);
				int temp2 = list.get(i - 1);
				list.set(i, temp2);
				list.set(i-1, temp);
				i--;
			} else {
				break;
			}
		}
		if(n > 10) list.remove(n-1);
	}
}
