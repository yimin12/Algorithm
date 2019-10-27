/**
 * 
 */
package CacheAndStream;

import java.util.HashMap;
import java.util.Map;

import HashTable_String.RemoveCertainCharacters;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月25日 上午10:39:39
* Description:
* 	Implement a least recently used cache. It should provide set(), 
* 	get() operations. If not exists, return null (Java), false (C++), -1(Python).
*/
public class LRUCache<K, V> {
//	each node contains the key, value pair, and it is also a double linked list node
	static class Node<K, V>{
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
//	make it final for the pre-defined size limit of the cache
	private final int limit;
//	record all the time the head and tail of the double linked list
	private Node<K, V> head;
	private Node<K, V> tail;
//	maintain the relationship of key its corresponding node in the double linked list
	private Map<K, Node<K, V>> map;
	
	public LRUCache(int limit){
		this.limit = limit;
		this.map = new HashMap<K, Node<K,V>>();
	}
	public void set(K key, V value) {
		Node<K, V> node = null;
//		1. if the key already in the cache, we need to update its value and move it to head (most recent position)
		if(map.containsKey(key)) {
			node = map.get(key);
			node.value = value;
		} else if (map.size() < limit) {
//		2. if the key is not in the cache and we still have space, we can add append a new node to head
			node = new Node<K, V>(key, value);
		} else {
//		3. if the key is not in the cache and we do not have space, we need to evict the tail and reuse the node 
//		   and let it maintain the new key, value and put it to head.
			node = tail;
			remove(node);
			node.update(key, value);
		}
		append(node);
	}
	public V get(K key) {
		Node<K, V> node = map.get(key);
		if(node == null) {
			return null;
		}
//		even it is a read operation, it is still a write operaton to the double linked list, and we need to move the node to head
		remove(node);
		append(node);
		return node.value;
	}
	private Node<K, V> remove(Node<K, V> node){
		map.remove(node.key);
		if(node.prev != null) {
			node.prev.next = node.next;
		}
		if(node.next != null) {
			node.next.prev = node.prev;
		}
		if(node == head) {
			head = head.next;
		}
		if(node == tail) {
			tail = tail.prev;
		}
		node.next = node.prev = null;
		return node;
	}
//	Insert the most recently used url to head of map
	private Node<K, V> append(Node<K, V> node){
		map.put(node.key, node);
		if(head == null) {
			head = tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		return node;
	}
}
