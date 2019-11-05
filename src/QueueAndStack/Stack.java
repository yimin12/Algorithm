/**
 * 
 */
package QueueAndStack;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年11月4日 下午7:16:01
* Description:
* 	implement the stack by linkedList
* 	only need one pointer
*/

public class Stack {
	static class ListNode{
		int value;
		private ListNode next;
		public ListNode(int value) {
			this.value = value;
		}
	}
	private ListNode head;
	public Stack() {
		head = null;
	}
	public Integer pop() {
		if(head == null) {
			return null;
		}
		ListNode prev = head;
		head = head.next;
		prev.next = null;
		return prev.value;
	}
	public Integer peek() {
		if(head == null) return null;
		return head.value;
	}
	public void push(int ele) {
		if(head == null) {
			head = new ListNode(ele);
		}
		ListNode node = new ListNode(ele);
		node.next = head;
	}
}
