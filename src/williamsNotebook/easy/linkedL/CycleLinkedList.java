/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.ListNode;

/**
 * @author yimin Huang
 *		
 *		Given a linked list, determine if it has a cycle in it.
 *	
 * Algorithm Class
 */
public class CycleLinkedList {

	
	// Two Pointers, Time ~ O(N), Space ~ O(1)
	public boolean hasCycle(ListNode head) {
		if(head == null || head.next == null) return false;
		ListNode slow = head, fast = head;
		while(fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast) return true;
		}
		return false;
	}
	
	// Follow Up 2: Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
	public ListNode detectCycle(ListNode head) {
		if(head == null || head.next == null) return null;
		ListNode slow = head, fast = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(slow == fast) {
				ListNode newSlow = head;
				while(newSlow != slow) {
					slow = slow.next;
					newSlow = newSlow.next;
				}
				return newSlow;
			}
		}
		return null;
	}
}
