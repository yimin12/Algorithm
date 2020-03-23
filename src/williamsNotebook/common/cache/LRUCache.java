/**
 * 
 */
package williamsNotebook.common.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yimin Huang
 *		
 *		Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.
 *		get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 *		set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should 
 *		invalidate the least recently used item before inserting a new item
 *
 *		Implement it with double linkedlist
 *
 * Algorithm Class
 */
public class LRUCache<K, V> {

	static class Node<K, V> {
		Node<K, V> next;
		Node<K, V> prev;
		K key;
		V value;
		Node(K key, V value){
			this.key = key;
			this.value = value;
		}
		void update(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	// make if final for the pre-define size limit of the cache
	private final int limit;
	private Node<K, V> head;
	private Node<K, V> tail;
	private Map<K, Node<K, V>> map;
	
	public LRUCache(int limit) {
		this.limit = limit;
		this.map = new HashMap<K, LRUCache.Node<K,V>>();
	}
	public void set(K key, V value) {
		// defaultly create an node when you use set
		Node<K, V> node = null;
		if(map.containsKey(key)) {
			// Case 1.0 if the key already in the cache, we need to update its value and move it to head(most recent position)
			node = map.get(key);
			node.value = value;
			remove(node); // remove the node becase it has been override
		} else if(map.size() < limit) {
			// Case 2.0 if the key is not in the cache, and there are still have space for new node
			node = new Node<K, V>(key, value);
		} else {
			// Case 3.0. if the key is not in cache and we do not have space any more, we need to evict the tail and reuse the node let it maintain the new key, value and put it to end
			node = tail;
			remove(node);
			node.update(key, value);
			
		}
		append(node);// append the node to the head
	}
	public V get(K key) {
		Node<K, V> node = map.get(key);
		if(node == null) return null;
		remove(node);
		append(node);
		return node.value;
	}
	// return the appended node
	private Node<K, V> append(Node<K, V> node) {
		map.put(node.key, node);
		if(head == null) {
			head = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		return node;
	}
	// return the deleted node
	private Node<K, V> remove(Node<K, V> node){
		// Step 1: delete the element in map
		map.remove(node.key);
		// Step 2: delinked the node with others
		if(node.prev != null) {
			node.prev.next = node.next;
		}
		if(node.next != null) {
			node.next.prev = node.prev;
		}
		// Step 3: Maintain the head and tail pointers
		if(node == head) {
			head = head.next;
		}
		if(node == tail) {
			tail = tail.prev;
		}
		node.next = node.prev = null;
		return node;
	}
}
