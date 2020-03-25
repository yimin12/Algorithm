/**
 * 
 */
package williamsNotebook.easy.abstraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月25日 下午2:12:41
* Description:
* 	Given a non-empty array of integers, return the k most frequent elements.
* 	Input: nums = [1,1,1,2,2,3], k = 2  Output: [1,2]
*/
public class TopK {

	// Method 1: MaxHeap(N + Nlog(N-k)), Poll MaxHeap when maxHeap.size() > hashmap.size() - k
	public List<Integer> topKFrequentI(int[] array, int k){
		Map<Integer,Integer> map = new HashMap<Integer, Integer>();
		for(int n : array) {
			map.put(n,  map.getOrDefault(n, 0)+1);
		}
		PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<Map.Entry<Integer,Integer>>((a, b)->(b.getValue() - a.getValue()));
		for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
			maxHeap.add(entry);
		}
		List<Integer> res = new ArrayList<Integer>();
		while(res.size() < k) {
			Map.Entry<Integer, Integer> entry = maxHeap.poll();
			res.add(entry.getKey());
		}
		return res;
	}
	// Method 2: Max Heap version two
	// MaxHeap(Time: N + Nlog(N-k)) Space: O(k) 
	public List<Integer> topKFrequentII(int[] array, int k){
		List<Integer> res = new ArrayList<Integer>();
		if(array == null || array.length == 0) return res;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int num : array) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->map.get(b) - map.get(a));
		for(int key:map.keySet()) {
			pq.offer(key);
			if(pq.size() > map.size()-k) {
				res.add(pq.poll());
			}
		}
		return res;
	}
	// Method 3: Min Heap O(NlogK + N); Space: O(K)
	public List<Integer> topKFrequentIII(int[] array, int k){
		Map<Integer, Integer> counterMap = new HashMap<Integer, Integer>();
		for(int num:array) {
			counterMap.put(num, counterMap.getOrDefault(num, 0)+1);
		}
		PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer,Integer>>((a, b)->a.getValue() - b.getValue());
		for(Map.Entry<Integer, Integer> entry:counterMap.entrySet()) {
			minHeap.offer(entry);
			if(minHeap.size() > k) minHeap.poll();
		}
		List<Integer> res = new LinkedList<Integer>();
		// the return value is sorted in descending value, just keep adding at the start position
		while(!minHeap.isEmpty()) {
			res.add(0, minHeap.poll().getKey());
		}
		return res;
	}
	// Method 4: TreeMap 
	public List<Integer> topKFrequentIV(int[] array, int k){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int n : array) {
			map.put(n, map.getOrDefault(n, 0) + 1);
		}
		// param1: frequency  param2: List of candidate
		TreeMap<Integer, List<Integer>> freqMap = new TreeMap<Integer, List<Integer>>();
		for(int num : map.keySet()) {
			int freq = map.get(num);
			if(!freqMap.containsKey(freq)) {
				freqMap.put(freq, new LinkedList<Integer>());
			}
			freqMap.get(freq).add(num);
		}
		List<Integer> res = new ArrayList<Integer>();
		while(res.size() < k) {
			Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
			res.addAll(entry.getValue());
		}
		return res;
	}
	// Method 5: Bucket sorting, general thought
	// use an array to save numbers into different bucket whose index is the frequency
	public List<Integer> topKFrequent(int[] array, int k){
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int n : array) {
			map.put(n, map.getOrDefault(n, 0)+1);
		}
		// corner case: if there is only number in array, we need the bucket has index 1
		List<Integer>[] bucket = new List[array.length+1];
		for(int n : map.keySet()) {
			int freq = map.get(n);
			if(bucket[freq] != null) {
				bucket[freq] = new LinkedList<Integer>();
			}
			bucket[freq].add(n);
		}
		List<Integer> res = new LinkedList<Integer>();
		// the frequency will sorted by the bucket's index 
		for(int i = bucket.length - 1; i > 0 && res.size() < k; i--) {
			if(bucket[i] != null) {
				res.addAll(bucket[i]);
			}
		}
		return res;
	}
}
