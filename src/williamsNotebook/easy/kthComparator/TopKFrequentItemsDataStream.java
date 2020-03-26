/**
 * 
 */
package williamsNotebook.easy.kthComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午1:29:25
* Description:
* 	Find top_k _frequent words in realtime data stream.
* 	Implement three methods for Topk Class:
* 		TopK(k). The constructor.
* 		add(word). Add a new word.
* 		topk(). Get the current top _k _frequent words.
*   If two words have the same frequency, rank them by alphabet.
*   Example:	
  		TopK(2)
		add("lint")
		add("code")
		add("code")
		topk()
>> ["code", "lint"]
*/
// Using HashMap and TreeSet to implement the function
public class TopKFrequentItemsDataStream {
	private Map<String, Integer> words = null;
	// A SortedSet extended with navigation methods reporting closest matches for given search targets. Methods lower, floor, ceiling, and higher
	private NavigableSet<String> topK = null;
	private int k;
	
	private Comparator<String> myComparator = new Comparator<String>() {
		public int compare(String left, String right) {
			if(left.equals(right)) {
				return 0;
			}
			int left_count = words.get(left);
			int right_count = words.get(right);
			if(left_count != right_count) {
				return right_count - left_count;
			}
			return left.compareTo(right);
		}
	};
	
	public TopKFrequentItemsDataStream(int k) {
		this.k = k;
		words = new HashMap<String, Integer>();
		topK = new TreeSet<String>(myComparator);
	}
	
	public void add(String word) {
		if(words.containsKey(word)) {
			if(topK.contains(word)) {
				topK.remove(word);
			}
			words.put(word, words.get(word) + 1);
		}
		topK.add(word);
		if(topK.size() > k) {
			topK.pollLast();
		}
	}
	
	public List<String> topK(){
		List<String> results = new ArrayList<String>();
		Iterator iterator = topK.iterator();
		while(iterator.hasNext()) {
			String string = (String)iterator.next();
			results.add(string);
		}
		return results;
	}
	
}
