package HashTable_Map_String;

import java.util.Arrays;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月13日 下午1:08:12
* Description: 
* 	A hashtable implementation of map, demonstration purpose, generic type is provided 
* 	(You need to consider concurrency problem)
* 		Cases you need to synchronized: when you need to manipulate (read or write) information of HashMap
* 	supported operations:
* 		size();
* 		isEmpty();
* 		clear();
* 		put(K key, V value);
* 		get(K key)
* 		containsKey(K key)
* 		containsValue(V value) // check if the desired value is in the map. O(n)
* 		remove(K key)
*/
public class MyHashMap<K, V> {
	// Node is static class of MyHashMap, since it is very closely bonded to HashMap class. we probably need to access this class
	// outside from MyHashMap Class
	public static class Node<K,V>{
		// there is unique key for hashMap
		final K key;
		V value;
		Node<K,V> next;
		Node(K key, V value){
			this.key = key;
			this.value = value;
		}
		// simple getter and setter for Node's Key and Node's Value
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}
	// static final variable are global constants
	public static final int DEFAULT_CAPACITY = 16;
	public static final float DEFAULT_LOAD_FACTOR = 0.75f;
	// create an buckets for my hashMap
	private Node<K, V>[] array;
	private int size; // it record how many key-value pairs are actually stored in the HashMap
	private float loadFactor; // determine whether to rehash
	// basic constructor
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	public MyHashMap(int cap, float loadfactor) {
		if(cap <= 0) {
			throw new IllegalArgumentException("capacity of HashMap could not be smaller than 0");
		}
		this.array = (Node<K, V>[])(new Node[cap]);
		this.size = 0;
		this.loadFactor = loadFactor;
	}
	// basic function implementation, every fields of hashMap should be locked, because it might have the concurrency problem
	public synchronized int size() {
		return size;
	}
	public synchronized boolean isEmpty() {
		return size==0;
	}
	public synchronized void clear() {
		// clean all the element in HashMap and replace them with null
		Arrays.fill(this.array, null);
		size = 0;
	}
	// hashCode implementation, just do some calculation, like helper class, do not need to synchronized !!!!
	private int hash(K key) {
		// Corner Case, when key is null
		if(key == null) {
			return 0;
		}
		// int code = key.hashCode();
		// return code >= 0 ? code : -code;
		// int range = [-2^31, 2^31-1]
		return key.hashCode() & 0X7FFFFFFF; 
		// it would guarantee non-negative, java's % return remainder rather than modulus. the remainder can be negative
	}
	private int getIndex(K key) {
		// put it into the bucket according the index
		return hash(key) % array.length;
	}
	private boolean equalsValue(V v1, V v2) {
		// v1 and v2 all possibly to be null
		/*
		 * if(v1 == null && v2 == null) { return true; } if(v1 == null || v2 == null) {
		 * return false; } return v1.equals(v2);
		 */
		return v1 == v2 || v1 != null && v1.equals(v2);
	}
	// traverse the whole array, and traverse each of the linked list in the array
	public synchronized boolean containsValue(V value) {
		// special case
		if(isEmpty()) {
			return false;
		}
		for(Node<K, V> node:array) {
			while(node!=null) {
				// check if the value equals() value, node.getValue() all possible to be null
				if(equalsValue(node.value, value)) {
					return true;
				}
			}
			node = node.next;
		}
		return false;
	}
	private boolean equalsKey(K k1, K k2) {
		return k1==k2 || k1!=null && k1.equals(k2);
	}
	public synchronized boolean containsKey(K key) {
		// get the index of the key
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while(node != null) {
			if(equalsKey(node.key,key)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}
	// get method, if key does not exist in the HashMap, return null
	public synchronized V get(K key) {
		int index = getIndex(key);
		Node<K, V> node = array[index];
		while(node != null) {
			if(equalsKey(node.key, key)) {
				return node.value;
			}
			node = node.next;
		}
		return null;
	}
	// insert/update: if key already exists, return the old corresponding value, if key not exists, return null
	public synchronized V put(K key, V value) {
		int index = getIndex(key);
		// if you get same index after hashing function, you should implement single linked list
		Node<K, V> head = array[index];
		// create a pointer, you can not lose the information of header
		Node<K, V> node = head;
		// find the target position you need to insert
		while(node != null) {
			if(equalsKey(node.key, key)) {
				V result = node.value;
				node.value = value;
				return result;
			}
			node = node.next;
		}
		// nothing found, you should append the new node before the head and update the new head insert operation
		Node<K, V> newNode = new Node(key, value);
		newNode.next = head;
		// create an newHead
		array[index] = newNode;
		size++;
		if(needRehashing()) {
			rehashing();
		}
		return null;
	}
	private boolean needRehashing() {
		// float loadFactor
		float ratio = (size + 0.0f) / array.length;
		return ratio >= loadFactor;
	}
	private void rehashing() {
		// create an variable to take the original array
		Node<K, V>[] recorder = this.array;
		this.array = (Node<K, V>[])(new Node[array.length << 2]);
		for(Node<K, V> node : recorder) {
			V result = put(node.key, node.value);
		}
	}
	// remove function, it will return null if the array does not contain the keys
	private synchronized V remove(K key) {
		int index = getIndex(key);
		Node<K, V> prev = null;
		Node<K, V> cur = array[index];
		while(cur!=null) {
			if(equalsKey(cur.key, key)) {
				if(prev == null) {
					// this is the first Node it that corresponding bucket
					array[index] = cur.next;
					size--;
					return cur.value;
				} else {
					prev.next = cur.next;
					size--;
					return cur.value;	
				}
			}
			prev = cur;
			cur = cur.next;
		}
		// if nothing found, just return null
		return null;
	}
}
