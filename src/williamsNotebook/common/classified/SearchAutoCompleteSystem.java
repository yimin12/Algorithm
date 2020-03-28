/**
 * 
 */
package williamsNotebook.common.classified;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 下午10:21:45
* Description:
* 	Design a search auto complete system for a search engine. Users may input a sentence (at least one word and end with a special character'#').
*   For each character they type except '#', you need to return the top 3historical hot sentences that have prefix the same as the part of sentence 
*   	already typed. Here are the specific rules:
*   The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before
*   The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, 
*   you need to use ASCII-code order (smaller one appears first)
*   If less than 3 hot sentences exist, then just return as many as you can
*   When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list
*   Your job is to implement the following functions
*   AutocompleteSystem(String[] sentences, int[] times):This is the constructor. The input is historical data.Sentencesis a string array consists of 
*   previously typed sentences.Timesis the corresponding times a sentence has been typed. Your system should record these historical data
*   Now, the user wants to input a new sentence. The following function will provide the next character the user types:
*   List<String> input(char c):The inputc is the next character typed by the user. The character will only be lower-case letters ('a'to'z'),
*   blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical 
*   hot sentences that have prefix the same as the part of sentence already typed.
*/
// Trie + PriorityQueue (Min-Heap)
public class SearchAutoCompleteSystem {

	static class TrieNode{
		Map<Character, TrieNode> children;
		Map<String, Integer> counts;
		boolean isWord;
		
		public TrieNode() {
			children = new HashMap<Character, TrieNode>();
			counts = new HashMap<String, Integer>();
			isWord = false;
		}
	}
	TrieNode root;
	String prefix;
	public SearchAutoCompleteSystem(String[] sentences, int[] times) {
		root = new TrieNode();
		prefix = "";
		for(int i = 0; i < sentences.length; i++) {
			add(sentences[i], times[i]);
		}
	}
	private void add(String s, int count) {
		TrieNode curr = root;
		for(char c : s.toCharArray()) {
			// if curr does not contains key (null) use putIfAbsent
			curr.children.putIfAbsent(c, new TrieNode());
			curr = curr.children.get(c);
			// all the substring in s should contain the information of count
			curr.counts.put(s, curr.counts.getOrDefault(s, 0) + count);
		}
		curr.isWord = true;
	}
	public List<String> input(char c){
		if(c == '#') {
			// every new search, remember to count this one;
			add(prefix, 1);
			prefix = "";
			return new ArrayList<String>();
		}
		prefix += c;
		TrieNode curr = root;
		for(char ch:prefix.toCharArray()) {
			if(!curr.children.containsKey(ch)) {
				return new ArrayList<String>();
			}
			curr = curr.children.get(ch);
		}
		Comparator<Map.Entry<String, Integer>> cmp = new Comparator<Map.Entry<String,Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
			}
		};
		PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<Map.Entry<String,Integer>>(cmp);
		// the number of elements that need to return
		int k = 3;
		// Use priorityQueue to set the threshold.
		for(Map.Entry<String, Integer> entry: curr.counts.entrySet()) {
			pq.offer(entry);
			while(!pq.isEmpty() && pq.size() > k) {
				pq.poll();
			}
		}
		ArrayList<String> res = new ArrayList<String>();
		while(!pq.isEmpty()) {
			res.add(0,pq.poll().getKey());
		}
		return res;
	}
}
