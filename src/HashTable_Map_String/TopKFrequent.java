/**
 * 
 */
package HashTable_Map_String;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月13日 上午11:33:00
* Description:
* 
* Assumption:
* 	combo is not, and k >= 1
*/

/**
 * @author 61771
 *
 */
public class TopKFrequent {
	public String[] topKFrequent(String[] combo, int k) {
//		handle the special case of empty combo at very beginning
		if(combo.length == 0) {
			return new String[0];
		}
//		get all the distinct strings as keys and their frequencies as value.
//		NOTICE: the freqMap has at least size 1
		Map<String, Integer> freqMap = getFreqMap(combo);
//		minHeap on the frequencies of the strings
//		NOTICE: using Map.Entry as the element type directly so taht all the operations are mostly efficient
		PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
//				compare the frequencies, directly call the compareTo method since return frequencies are 
//				 represented by Integer
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		for(Map.Entry<String, Integer> entry:freqMap.entrySet()) {
			if(minHeap.size()<k) {
				minHeap.offer(entry);
			} else if(entry.getValue() > minHeap.peek().getValue()) {
				minHeap.poll();
				minHeap.offer(entry);
			}
		}
//		 Since the returned array requires the strings sorted by their frequencies, use a separate helper
//		method to do this operation
		return freqArray(minHeap);
	}
	private Map<String, Integer> getFreqMap(String[] combo){
		Map<String, Integer> freqMap = new HashMap<String, Integer>();
//		NOTICE:when possible, choose the most efficient way for
//		HashMap: operations
		for(String s:combo) {
			Integer freqInteger = freqMap.get(s);
			if(freqInteger == null) {
				freqMap.put(s, 1);
			} else {
				freqMap.put(s,freqInteger +1);
			}
		}
		return freqMap;
	}
	private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
		String[] result = new String[minHeap.size()];
		for(int i = minHeap.size() - 1; i >= 0; i--) {
			result[i] = minHeap.poll().getKey();
		}
		return result;
	}
}
