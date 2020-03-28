/**
 * 
 */
package williamsNotebook.common.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月26日 上午11:41:20
* Description:
* 	LFU (Least Frequently Used) is a famous cache eviction algorithm.
* 	For a cache with capacity k, if the cache is full and need to evict a key in it, the key with the least frequently used will be kicked out.
* 	Implement set and get method for LFU cache.
* Example:	Given capacity=3
	set(2,2)
	set(1,1)
	get(2)
	>> 2
	get(1)
	>> 1
	get(2)
	>> 2
	set(3,3)
	set(4,4)
	get(3)
	>> -1
	get(2)
	>> 2
	get(1)
	>> 1
	get(4)
	>> 4
*/
public class LFUCache {
	
	private class CacheNode{
		public final int key;
		public int value;
		public int frequency;
		public CacheNode(int key, int value, int frequency) {
			this.key = key;
			this.value = value;
			this.frequency = frequency;
		}
	}
	private final Map<Integer, CacheNode> cache;
	private final LinkedHashSet[] frequencyList;
	private int lowestFrequency;
	private int maxFrequency;
	private final int maxCacheSize;
	
	// Constructor
	public LFUCache(int capacity) {
		this.cache = new HashMap<Integer, CacheNode>();
		this.frequencyList = new LinkedHashSet[capacity * 2];
		this.lowestFrequency = 0;
		this.maxFrequency = capacity * 2 - 1;
		this.maxCacheSize = capacity;
		initFrequencyList();
	}
	// offer element to the cache, public APIs
	public void set(int key, int value) {
		CacheNode cur = cache.get(key);
		// case 1.0: cache does not contain key
		if(cur == null) {
			if(cache.size() == maxFrequency) {
				doEviction();
			}
			LinkedHashSet<CacheNode> nodes = frequencyList[0];
			cur = new CacheNode(key, value, 0);
			nodes.add(cur);
			cache.put(key, cur);
			lowestFrequency = 0;
		} else {
			cur.value = value;
		}
		addFrequency(cur);
	}
	// get the element in the cache, public APIs
	public int get(int key) {
		CacheNode cur = cache.get(key);
		if(cur != null) {
			addFrequency(cur);
			return cur.value;
		} else {
			return -1;
		}
	}
	// internal helper function for adding frequency
	public void addFrequency(CacheNode currentNode) {
		int currentFrequency = currentNode.frequency;
		if(currentFrequency < maxFrequency) {
			int nextFrequency = currentFrequency + 1;
			LinkedHashSet<CacheNode> currCacheNodes = frequencyList[currentFrequency];
			LinkedHashSet<CacheNode> newNodes = frequencyList[nextFrequency];
			moveToNextFrequency(currentNode, nextFrequency, currCacheNodes, newNodes);
			cache.put(currentNode.key, currentNode);
			if(lowestFrequency == currentFrequency && currCacheNodes.isEmpty()) {
				lowestFrequency = nextFrequency;
			}
		} else {
			LinkedHashSet<CacheNode> nodes = frequencyList[currentFrequency];
			nodes.remove(currentFrequency);
			nodes.add(currentNode);
		}
	}
	public int remove(int key) {
		CacheNode cur = cache.remove(key);
		if(cur != null) {
			LinkedHashSet<CacheNode> nodes = frequencyList[cur.frequency];
			nodes.remove(cur);
			if(lowestFrequency == cur.frequency) {
				findNextLowestFrequency();
			}
			return cur.value;
		} else {
			return -1;
		}
	}
	public int frequencyOf(int key) {
		CacheNode node = cache.get(key);
		if(node != null) {
			return node.frequency;
		} else {
			return 0;
		}
	}
	public void clear() {
		for(int i = 0; i <= maxFrequency; i++) {
			frequencyList[i].clear();
		}
		cache.clear();
		lowestFrequency = 0;
	}
	public int size() {
		return cache.size();
	}
	public boolean isEmpty() {
		return this.cache.isEmpty();
	}
	public boolean containsKey(int key) {
		return this.cache.containsKey(key);
	}
	private void initFrequencyList() {
		for(int i = 0; i <= maxFrequency; i++) {
			frequencyList[i] = new LinkedHashSet<CacheNode>();
		}
	}
	private void doEviction() {
		int currentlyDeleted = 0;
		double target = 1;
		while(currentlyDeleted < target) {
			LinkedHashSet<CacheNode> nodes = frequencyList[lowestFrequency];
			if(nodes.isEmpty()) {
				continue;
			} else {
				Iterator<CacheNode> iterator = nodes.iterator();
				while(iterator.hasNext()) {
					CacheNode node = iterator.next();
					iterator.remove();
					cache.remove(node.key);
				}
				if(!iterator.hasNext()) {
					findNextLowestFrequency();
				}
			}
		}
	}
	private void moveToNextFrequency(CacheNode current, int nextFrequency, LinkedHashSet<CacheNode> currentNodes, LinkedHashSet<CacheNode> newNodes) {
		currentNodes.remove(current);
		newNodes.add(current);
		current.frequency = nextFrequency;
	}
	private void findNextLowestFrequency() {
		while(lowestFrequency <= maxFrequency && frequencyList[lowestFrequency].isEmpty()) {
			lowestFrequency++;
		}
		if(lowestFrequency > maxFrequency) {
			lowestFrequency = 0;
		}
	}
}
