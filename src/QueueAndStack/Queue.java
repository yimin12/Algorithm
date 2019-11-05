/**
 * 
 */
package QueueAndStack;

import QueueAndStack.Stack.ListNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年11月4日 下午7:27:20
* Description:
* 	implement the queue by using LinkedList with two pointer : head and tail
*/


public class Queue {
	static class ListNode{
		int value;
		private ListNode next;
		public ListNode(int value) {
			this.value = value;
		}
	}
//	two pointer head and tail
	ListNode head;
	ListNode tail;
	public Queue() {
		head = tail = null;
	}
	public Integer poll() {
		if(head == null) {
			return null;
		}
		ListNode node = head;
		head = head.next;
		if(head == null) {
			tail = null;
		}
		node.next = null;
		return node.value;
	}
	public Integer peek() {
		if(head == null) {
			return null;
		}
		return head.value;
	}
	public void offer(int ele) {
		if(head == null) {
			head = new ListNode(ele);
			tail = head;
		} else {
			tail.next = new ListNode(ele);
			tail = tail.next;
		}
		
	}
}
