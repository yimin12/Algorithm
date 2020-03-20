/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.ListNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2020年3月19日 下午3:45:31
* Description:
* 	Given a singly linked list, determine if it is a palindrome.
*/


public class PalindromeLinkedList {

	public boolean isPalindrome(ListNode head) {
		if(head == null || head.next == null) return false;
		// Step 1: find the middle node
		ListNode fast = head, slow = head;
		while(fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		ListNode first = head;
		ListNode second = reverse(slow);
		// no matter it is odd or even, the difference of length of each half must not greater than 1
		while(second != null && first != null) {
			if(first.key != second.key) {
				return false;
			}
			first = first.next;
			second = second.next;
		}
		return true;
	}
	private ListNode reverse(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode prev = null, cur = head;
		while(cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}
	public static void main(String[] args) {
		PalindromeLinkedList solution = new PalindromeLinkedList();
		ListNode head = new ListNode(1);
		ListNode one = new ListNode(2);
		ListNode two = new ListNode(1);
//		ListNode three = new ListNode(1);
		head.next = one; one.next = two; 
//		two.next = three;
		boolean palindrome = solution.isPalindrome(head);
		System.out.println(palindrome);
	}
}
