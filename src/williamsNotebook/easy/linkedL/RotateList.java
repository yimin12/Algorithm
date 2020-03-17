/**
 * 
 */
package williamsNotebook.easy.linkedL;

import java.util.List;

import williamsNotebook.common.ListNode;

/**
 * @author yimin Huang
 * 
 *	Given a list, rotate the list to the right by k places, where k is non-negative.
 *	For example:
 *		Given 1->2->3->4->5->NULL and k = 2,
 *		return 4->5->1->2->3->NULL.
 *	
 * Algorithm Class
 */
public class RotateList {
	
	// Method 1: use two pointers
	// Two pointers: Time ~ O(N), Space ~ O(1)
	public ListNode rotate(ListNode head, int n) {
		if(head == null || head.next == null ||n <= 0) return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode slow = head, fast = head, prev = dummy;
		for(int i = 0; i < n; i++) {
			fast = fast.next;
		}
		while(fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		fast.next = dummy.next;
		dummy.next = slow.next;
		slow.next = null;
		return dummy.next;
	}
	
	public static void main(String[] args) {
		RotateList solution = new RotateList();
		ListNode head = new ListNode(1);
		ListNode two = new ListNode(2);
		ListNode three = new ListNode(3);
		ListNode four = new ListNode(4);
		ListNode five = new ListNode(5);
		head.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		ListNode rotate = solution.rotate(head, 2);
		while(rotate != null) {
			System.out.println(rotate.key);
			rotate = rotate.next;
		}
		
	}
}
