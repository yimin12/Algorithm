/**
 * 
 */
package williamsNotebook.easy.linkedL.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��16�� ����10:33:49
* Description:
* 	Reverse pairs of elements in a singly-linked list.
* Examples:
	L = null, after reverse is null
	L = 1 -> null, after reverse is 1 -> null
	L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
	L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
*/

public class ReverseListInPairs {
//	Method 1:recursive way
	public ListNode reverseInPairs(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = reverseInPairs(head.next.next);
		newHead.next = head;
		return newHead;
	}
//	Method 2: iterative
	public ListNode reverseInPairsI(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode cur = dummy;
		while(cur.next != null && cur.next.next != null) {
			ListNode next = cur.next.next;
			cur.next.next = cur.next.next.next;
			next.next = cur.next;
			cur.next = next;
			cur = cur.next.next;
		}
		return dummy.next;
	}
}
