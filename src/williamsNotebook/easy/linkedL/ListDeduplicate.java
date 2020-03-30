/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.node.ListNode;

/**
 * @author yimin Huang
 *		
 *		Given a sorted linked list, delete all duplicates such that each element appear only once.
 *		For example,
 *		Given 1->1->2, return 1->2.
 *		Given 1->1->2->3->3, return 1->2->3.
 *	
 * Algorithm Class
 */
public class ListDeduplicate {

	
	// Linear Traversal, Keep one duplicates
	public ListNode dedup(ListNode head) {
		ListNode cur = head;
		while(cur != null && cur.next != null) {
			if(cur.key == cur.next.key) {
				cur.next = cur.next.next;
			} else {
				cur = cur.next;
			}
		}
		return head;
	}
	
	// Follow Up : Keep Zero Duplicate
	// Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
	// For example,
	// Given 1->2->3->3->4->4->5, return 1->2->5.
	// Given 1->1->1->2->3, return 2->3.
	public ListNode dedupII(ListNode head) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy, cur = dummy.next;
		while(cur != null && cur.next != null) {
			if(cur.key != cur.next.key) {
				prev = cur;
			} else {
				while(cur.next != null && cur.key == cur.next.key) cur = cur.next;
				prev.next = cur.next;
			}
			cur = cur.next;
		}
		return dummy.next;
	}
	public static void main(String[] args) {
		ListDeduplicate solution = new ListDeduplicate();
		ListNode head = new ListNode(1);
		ListNode two = new ListNode(2);
		ListNode three = new ListNode(3);
		ListNode four = new ListNode(3);
		ListNode five = new ListNode(4);
		ListNode six = new ListNode(4);
		ListNode seven = new ListNode(5);
		head.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		five.next = six;
		six.next = seven;
		ListNode dedupII = solution.dedupII(head);
		while(dedupII != null) {
			System.out.print(dedupII.key + " ");
			dedupII = dedupII.next;
		}
	}
}
