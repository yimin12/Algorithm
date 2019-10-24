/**
 * 
 */
package LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月9日 下午5:23:48
* Description:
* 	Reorder the given single linkedlist by the following rules
* 	N1 -> N2 -> N3 -> N4 ..... ->Nn to be N1 -> Nn -> N2 -> Nn-1 -> N3 -> Nn-2
*/

public class ReorderList {
	public ListNode reorder(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
//		step1:find the middle node
		ListNode mid = findMid(head);
		ListNode one = head;
		ListNode two = mid.next;
//		de-link the second half from the list
		mid.next = null;
		return merge(one, reverse(two));
	}
	
	private ListNode findMid(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	
	private ListNode reverse(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
//		Use iterative way to reverse the linkedlist
		ListNode prev = null;
		while(head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head.next = next;
		}
		return prev;
	}
	
	private ListNode merge(ListNode one, ListNode two) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while(one != null && two != null) {
			cur.next = one;
			one = one.next;
			cur.next.next = two;
			two = two.next;
			cur = cur.next.next;	
		}
		if(one != null) {
			cur.next = one;
		} else {
			cur.next = two;
		}
		return dummy.next;	
	}
}
