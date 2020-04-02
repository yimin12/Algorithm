/**
 * 
 */
package williamsNotebook.easy.linkedL.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��9�� ����4:49:17
* Description:	Find the middle node of a singled linked list
* 	1) 1 -> 2 -> 3 -> null, the middle node is 2
* 	2) 1 -> 2 -> 3 -> 4 -> null, the middle node is 2
* 	3) null, the middle node is null;
*/

public class MiddleNode {
	
	public ListNode findMiddle(ListNode head) {
		if(head == null) return head;
		ListNode fast = head;
		ListNode slow = head;
//		you need to ensure that there is no npe
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
}
