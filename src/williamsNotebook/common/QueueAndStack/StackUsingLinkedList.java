/**
 * 
 */
package williamsNotebook.common.QueueAndStack;

import williamsNotebook.common.node.ListNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2020��3��19�� ����5:16:00
* Description:
* 	Write an algorithm to implement Stack using Linked List.
* 	Assume that we push at head and pop at head
*/

public class StackUsingLinkedList {

	private ListNode head;
	private int size;
	public StackUsingLinkedList() {
		this.head = null;
		this.size = 0;
	}
	public void push(int data) {
		ListNode node = new ListNode(data);
		if(getSize() == 0) {
			head  = node;
		} else {
			node.next = head;
			head = node;
		}
		System.out.println("Element " + data + "is pushed into Stack");
		size++;
	}
	public int pop() {
		if(getSize() == 0) {
			System.out.println("Stack is empty");
			return -1;
		} else {
			ListNode temp = head;
			head = head.next;
			size--;
			return temp.key;
		}
	}
	public void printStack() {
		ListNode cur = head;
		while(cur != null) {
			System.out.print(cur.key + " ");
			cur = cur.next;
		}
		System.out.println();
	}
	public int getSize() {
		return this.size;
	}
}
