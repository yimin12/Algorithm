/**
 * 
 */
package williamsNotebook.common.linkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月19日 下午1:44:38
* Description:
* 	Design your implementation of the linked list. You can choose to use the singly 
* 	linked list or the doubly linked list. A node in a singly linked list should have 
* 	two attributes:val andnext.valis the value of the current node, andnext is a 
* 	pointer/reference to the next node. If you want to use the doubly linked list, you 
* 	will need one more attributeprevto indicate the previous node in the linked list.
* 	Assume all nodes in the linked list are 0-indexed.
* Implement these functions in your linked list class:
* 	get(index) : Get the value of theindex-th node in the linked list. If the index is invalid, return-1.
* 	addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, 
* 		the new node will be the first node of the linked list.
* 	addAtTail(val) : Append a node of value val to the last element of the linked list.
* 	addAtIndex(index, val) : Add a node of valueval before theindex-th node in the linked list.
* 	deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
*	MyLinkedList linkedList = new MyLinkedList();
	linkedList.addAtHead(1);
	linkedList.addAtTail(3);
	linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
	linkedList.get(1);            // returns 2
	linkedList.deleteAtIndex(1);  // now the linked list is 1->3
	linkedList.get(1);            // returns 3
*/

public class LinkedList {

	// Note: All values will be in the range of[1, 1000].
	//		The number of operations will be in the range of [1, 1000].
	//		Please do not use the built-in LinkedList library.
	class Node{
		int val;
		Node next;
		public Node(int val) {
			this.val = val;
		}
	}
	// meet the requirement of addAtHead and addAtTail
	private Node head;
	private Node tail;
	private int size;
	// constructor
	public LinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	// Implement the get()
	public int get(int index) {
		if(index >= size) {
			return -1;
		}
		Node cur = head;
		for(int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.val;
	}
	// Implement the addAtHead
	public void addAtHead(int val) {
		Node newHead = new Node(val);
		newHead.next = head;
		head = newHead;
		this.size++;
		// if there only one element, head == tail == newHead
		if(head.next == null) {
			tail = head;
		}
	}
	// Implement the addAtTail
	public void addAtTail(int val) {
		Node node = new Node(val);
		if(tail == null || size == 0) {
			head = node;
			tail = node;
			size++;
			return;
		}
		tail.next = node;
		tail = tail.next;
		size++;
	}
	// public void addAtIndex
	public void addAtIndex(int index, int val) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("You have been out of bound");
		}
		if(index == 0) {
			addAtHead(val);
		} else if(index == size - 1) {
			addAtTail(val);
		} else {
			Node cur = head;
			// find previous position
			for(int i = 0; i < index - 1; i++) {
				cur = cur.next;
			}
			Node n = new Node(val);
			n.next = cur.next;
			cur.next = n;
			size++;
		}
	}
	// Delete the index-th in the linked list
	public void deleteAtIndex(int index) {
		if(size == 0 || index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("You have been out of bound");
		}
		if(index == 0) {
			head = head.next;
			size--;
			if(size == 0) {
				tail = null;
			} 
		} else {
			Node cur = head;
			for(int i = 0; i < index - 1; i++) {
				cur = cur.next;
			}
			cur.next = cur.next.next;
			size--;
			if(cur.next == null) tail = cur;
		} 
	}
}
