/**
 * 
 */
package williamsNotebook.easy.linkedL.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��9�� ����4:33:17
* Description: Reverse Single LinkedList, both iterative way and recusive ways
*/

public class ReverseLinkedList {
	
//	iterative way to reverse the linkedList
	public ListNode reverseLinkedList(ListNode head) {
		ListNode prev = null;
		while(head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
	
	public ListNode reverseLinkedListI(ListNode head) {
//		be careful about the base case, need to make sure head.next.next != null
		if(head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseLinkedList(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
}
