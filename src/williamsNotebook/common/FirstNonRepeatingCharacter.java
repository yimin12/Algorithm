/**
 * 
 */
package williamsNotebook.common;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author yimin Huang
 *	 
 *		Given a stream of characters, find the first non-repeating character from stream. You need to tell the first non-repeating character O(1) time at any
 *		moment.
 *
 * Algorithm Class
 */
public class FirstNonRepeatingCharacter {

	// each node is double linked list node and it contains one distinct character
	static class Node{
		Node prev, next;
		Character character;
		Node(Character character){
			this.character = character;
		}
	}
	// maintain the head and tail all the time, only the characters appearing just once will be in the double linked list
	private Node head;
	private Node tail;
	// for any character, it could be in three state:
	//	1. not existed yet, it will not be in singled map or repeated set
	// 	2. only existed once, it be in singled map and there will be a corresponding node in the list
	// 	3. exists more than once, it will be in the repeated Set. and it will be removed from list
	private HashMap<Character, Node> singled;
	private HashSet<Character> repeated;
	
	public FirstNonRepeatingCharacter() {
		tail = new Node(null);
		tail.next = tail.prev = tail;
		head = tail;
		singled = new HashMap<Character, Node>();
		repeated = new HashSet<Character>();
	}
	public void read(char ch) {
		// if the character already exists more than once, we just ignore
		if(repeated.contains(ch)) {
			return;
		}
		Node node = singled.get(ch);
		if(node == null) {
			// if the character appears for the first time, it should be added to the list and added to nonRepeated
			node = new Node(ch);
			append(node);
		} else {
			// if the character is already in the nonRepeated Map, we should remove it from the Map and list, and put it into the repeat Set
			remove(node);
		}
	}
	public Character firstNonRepeating() {
		// when head == tail, it means there are only the sentinel node in the list
		if(head == tail) {
			return null;
		}
		return head.next.character;
	}
	
	private void append(Node node) {
		singled.put(node.character, node);
		tail.next = node;
		node.prev = tail;
		node.next = head;
		tail = tail.next;
	}
	
	private void remove(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
		if(node == tail) {
			tail = node.prev;
		}
		node.prev = node.next = null;
		repeated.add(node.character);
		singled.remove(node.character);
	}
}
