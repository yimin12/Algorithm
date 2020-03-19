/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.ListNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月18日 下午11:07:39
* Description:
* 	Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we 
* 	are talking about the node number and not the value in the nodes.
* 	You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
* 	Input: 1->2->3->4->5->NULL
* 	Output: 1->3->5->2->4->NULL
*/

public class OddEvenLinkedList {
	
	// We should do it in-place
	public ListNode oddEvenList(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode odd = head, even = head.next, evenHead = even;
		// we set even as boundary because it is the later one.
		while(even != null && even.next != null) {
			odd.next = odd.next.next;
			even.next = even.next.next;
			odd = odd.next;
			even = even.next;
		}
		odd.next = evenHead;
		return head;
	}
}
