/**
 * 
 */
package williamsNotebook.common.linkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月19日 下午3:09:11
* Description:
*/
public class MyLinkedList {
	
	private SinglyListNode head;
	
	public MyLinkedList() {
		head = null;
	}
	// helper function
	private SinglyListNode getNode(int index) {
		SinglyListNode cur = head;
		for(int i = 0; i < index && cur != null; i++) {
			cur = cur.next;
		}
		return cur;
	}
	private SinglyListNode getTail() {
		SinglyListNode cur = head;
		while(cur != null && cur.next != null) {
			cur = cur.next;
		}
		return cur;
	}
	public int get(int index) {
		SinglyListNode cur = getNode(index);
		return cur == null ? -1 : cur.val;
	}
	// Add a node of value val before the first element of the linked list. After the insertion
	public void addAtHead(int val) {
		SinglyListNode cur = new SinglyListNode(val);
		cur.next = head;
		head = cur;
		return;
	}
	public void addAtTail(int val) {
		if(head == null) {
			addAtHead(val);
			return;
		}
		SinglyListNode prev = getTail();
		SinglyListNode cur = new SinglyListNode(val);
		prev.next = cur;
	}
	public void addAtIndex(int index, int val) {
		if(index == 0) {
			addAtHead(val);
			return;
		}
		SinglyListNode prev = getNode(index - 1);
		if(prev == null) {
			return;
		}
		SinglyListNode cur = new SinglyListNode(val);
		SinglyListNode next = prev.next;
		cur.next = next;
		prev.next = cur;
	}
}
