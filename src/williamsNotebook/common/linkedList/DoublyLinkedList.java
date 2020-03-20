/**
 * 
 */
package williamsNotebook.common.linkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月19日 下午3:33:21
* Description:
* 	
*/

public class DoublyLinkedList {

	DoublyListNode head;
	public DoublyLinkedList() {
		head = null;
	};
	// helper function
	private DoublyListNode getNode(int index) {
		DoublyListNode cur = head;
		for(int i = 0; i < index && cur != null; i++) {
			cur = cur.next;
		}
		return cur;
	}
	private DoublyListNode getTail() {
		DoublyListNode cur = head;
		while(cur != null && cur.next != null) {
			cur = cur.next;
		}
		return cur;
	}
	public int get(int index) {
		DoublyListNode cur = getNode(index);
		return cur == null ? -1 : cur.val;
	}
	// Add new nodes
	public void addAtHead(int val) {
		DoublyListNode cur = new DoublyListNode(val);
		if(head == null) {
			addAtHead(val);
			return;
		}
		cur.next = head;
		if(head != null) {
			head.prev = cur;
		}
		head = cur;
		return;
	}
	public void addAtTail(int val) {
		if(head == null) {
			addAtHead(val);
			return;
		}
		DoublyListNode prev = getTail();
		DoublyListNode cur = new DoublyListNode(val);
		prev.next = cur;
		cur.prev = prev;
	}
	public void addAtIndex(int index, int val) {
		if(index == 0) {
			addAtHead(val);
			return;
		}
		DoublyListNode prev = getNode(index - 1);
		if(prev == null) {
			return;
		}
		DoublyListNode cur = new DoublyListNode(val);
		DoublyListNode next = prev.next;
		cur.prev = prev;
		cur.next = next;
		prev.next = cur;
		if(next != null) {
			next.prev = cur;
		}
	}
}
