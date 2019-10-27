/**
 * 
 */
package CacheAndStream;

import java.util.HashMap;
import java.util.HashSet;
/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月25日 上午11:15:27
* Description:
* 	Given a stream of characters, find the first non-repeating character from stream. 
* 	You need to tell the first non-repeating character in O(1) time at any moment.
* 	Implement two methods of the class:
* 		read() - read one character from the stream
* 		firstNonRepeating() - return the first non-repoeating character from the stream at any time when calling the method, 
* 		return null if there does not exist such characters
* Examples:
* 	read:
* 	a   b   c   a   c   c    b
* 	firstNonRepeating:
* 	a   a   a   b   b   b   null
*/

public class FirstNonRepeating {
//	each node is double linked list node and it contains one distinct character
	static class Node{
		Node prev;
		Node next;
		Character character;
		
		Node(Character character){
			this.character = character;
		}
	}
//	record the head and tail of the list all the time. only the characters appearing just once will be in the double linked list
	private Node head;
	private Node tail;
//	for any character, it could be in three state:
//	1: not existed yet, it will be in singled Map or repeated Set
//	2: only exists once, it will be in singled Map and there will be a corresponding node in the list
//	3: exists more than once, it will be in the repeated Set, and it will be removed from the list
	private HashSet<Character> repeated;
	private HashMap<Character, Node> singled;
	
	public FirstNonRepeating() {
//		an example of using sentinel node to eliminate some corner cases
		tail = new Node(null);
		tail.next = tail.prev = tail;
		head = tail;
		singled = new HashMap<Character, FirstNonRepeating.Node>();
		repeated = new HashSet<Character>();
	}
	public void read(char ch) {
//		if the character already exists more than once, we just ignore
		if(repeated.contains(ch)) {
			return;
		}
		Node node = singled.get(ch);
		if(node == null) {
//			if the character appears for the first time, it should be added to the list and added to the nonRepeated
			node = new Node(ch);
			append(node);
		} else {
//			if the character is already in the nonRepeated Map, we should remove it from the Map and list, and put it into
//			the repeated Set
			remove(node);
		}
	}
	private void append(Node node) {
		singled.put(node.character, node);
		tail.next = node;
		node.prev = tail;
		node.next = head;
		tail = tail.next;
	}
	private void remove(Node node) {
//		use sentinel node so that some of the corner case will be eliminated
		node.prev.next = node.next;
		node.next.prev = node.prev;
		if(node == null) {
			tail = node.prev;
		}
		node.prev = node.next = null;
		repeated.add(node.character);
		singled.remove(node.character);
	}
	public Character firstNonRepCharacter() {
//		when head == tail, it means there is only the sentinel node in the list
		if(head == tail) {
			return null;
		}
		return head.next.character;
	}
}
