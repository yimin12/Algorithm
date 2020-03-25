/**
 * 
 */
package williamsNotebook.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import williamsNotebook.medium.AnimalShelterSingle.Node;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午12:45:50
* Description:
* 	Given a non-empty list of words, return thekmost frequent elements.
* 	Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
* Example 1:
* 	 ["i", "love", "leetcode", "i", "love", "coding"], k = 2 Output:  ["i", "love"]
* 	 Explanation:  "i" and "love" are the two most frequent words.   Note that "i" comes before "love" due to a lower alphabetical order.
*/
public class TwoFrequentWords {

	// Method 1: Sorting by PriorityQueue
	// Time: O(nlogn) Space: O(n);
	public List<String> topKFrequent(String[] words, int k){
		Map<String, Integer> cnt = new HashMap<String, Integer>();
		List<String> result = new LinkedList<String>();
		for(String word:words) {
			cnt.put(word, cnt.getOrDefault(word, 0) + 1);
		}
		Queue<String> pq = new PriorityQueue<String>((w1, w2) -> cnt.get(w1).equals(cnt.get(w2)) ? w1.compareTo(w2) : cnt.get(w2) - cnt.get(w1));
		for(String word:cnt.keySet()) {
			pq.offer(word);
		}
		for(int i = 0; i < k; i++) {
			result.add(pq.poll());
		}
		return result;
	}
	// Method 2: Use Custom Excapsulation and PriorityQueue
	// Time: O(nlogn) Space: O(n)
	static class Node {
		String s;
		int cnt;
		Node(String s){
			this.s = s;
			cnt = 1;
		}
		void increase() {
			this.cnt++;
		}
	}
	public List<String> topKFrequentI(String[] words, int k){
		List<String> reslut = new ArrayList<String>();
		Map<String, Node> map = new HashMap<String, Node>();
		for(String s:words) {
			if(!map.containsKey(s)) {
				map.put(s, new Node(s));
			} else {
				map.get(s).increase();
			}
		}
		Queue<Node> q = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node a, Node b) {
				int result = b.cnt - a.cnt;
				// if the frequency is equal, sort in lexi
				if(result == 0) {
					return a.s.compareTo(b.s);
				}
				return result;
			}
		});
		for(Map.Entry<String,Node> entry: map.entrySet()) {
			q.offer(entry.getValue());
		}
		for(int i = 0; i < k; i++) {
			reslut.add(q.poll().s);
		}
		return reslut;
	}
	// Method 3: Simply Sorting
	// Time: O(nlogn) Space: O(n)
	public List<String> topKFrequentII(String[] words, int k){
		Map<String, Integer> cnt = new HashMap<String, Integer>();
		for(String string : words) {
			cnt.put(string, cnt.getOrDefault(string, 0) + 1);
		}
		List<String> candidates = new ArrayList<String>();
		Collections.sort(candidates, (w1, w2) -> cnt.get(w1).equals(cnt.get(w2)) ? w1.compareTo(w2): cnt.get(w2) - cnt.get(w1));
		return candidates.subList(0, k);
	}

}
