/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.node.ListNode;

/**
 * @author yimin Huang
 *	Given a linked list, remove the nth node from the end of list and return its head.
 *	For example
 *		 Given linked list: 1->2->3->4->5, and n = 2.
 *		 After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Algorithm Class
 */
public class RemoveListNode {

	// All the implemented method should be completed within Time:O(n) and Space:O(1)
	// Assume that k >= 0
	public ListNode removeNthfromEnd(ListNode head, int k) {
		if(head == null) return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode prev = dummy.next, cur = dummy.next;
		// because you add dummy node ,you need to advance one step more
		while(k > -1) {
			if(cur.next != null) {
				cur = cur.next;
				k--;
			} else if(cur.next == null && k > 0) {
				throw new IllegalArgumentException("the length is shorter than k");
			}
		}
		
		while(cur != null) {
			cur = cur.next;
			prev = prev.next;
		}
		
		prev.next = prev.next.next;
		return dummy.next;
	}
	
	// Situation I : Keep one duplicate
	/*
	 * Given a sorted linked list, delete all duplicates such that each element
	 * appear only once. 
	 * For example, Given 1->1->2, return 1->2. Given
	 * 	1->1->2->3->3, return 1->2->3.
	 */
	
	public ListNode deleteDuplicate(ListNode head) {
		if(head == null) return head;
		ListNode slow = head, fast = head;
		while(fast != null) {
			if(slow.key == fast.key) {
				fast = fast.next;
				continue;
			}
			slow.next = fast;
			slow = slow.next;
		}
		// you need to care the post processing, when the last part of the same nodes
		slow.next = null;
		return head;
	}
	
	// Situation II : Keep zero duplicates
	/*
	 * Given a sorted linked list, delete all nodes that have duplicate numbers,
	 * leaving only distinct numbers from the original list. 
	 * For example, Given 1->2->3->3->4->4->5, return 1->2->5. Given 1->1->1->2->3, return 2->3.
	 */
	
	public ListNode deleteDuplicateII(ListNode head) {
		// Assumption: the given array is sorted 
		if(head == null) return head;
		// because the header might be affected, we should have a dummy head
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy, curr = dummy.next;
		while(curr != null && curr.next != null) {
			if(curr.key != curr.next.key) {
				prev = curr;
			} else {
				while(curr.next != null && curr.key == curr.next.key) curr = curr.next;
				prev.next = curr.next;
			}
			curr = curr.next;
		}
		return dummy.next;
	}
	
	// Remove specific list element
	/*
	 * Remove all elements from a linked list of integers that have value val.
	 * Example Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6 Return: 1 --> 2 --> 3 --> 4 --> 5
	 */
	public ListNode removeElements(ListNode head, int value) {
		if(head == null) return head;
		ListNode dummy = new ListNode(0);
		ListNode prev = dummy.next, curr = dummy.next;
		while(curr != null) {
			if(curr.key == value) {
				prev.next = curr.next;
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
		return dummy.next;
	}
	public static void main(String[] args) {
		RemoveListNode solution = new RemoveListNode();
		ListNode head = new ListNode(1);
		ListNode two = new ListNode(1);
		ListNode three = new ListNode(1);
		ListNode four = new ListNode(2);
		ListNode five = new ListNode(3);
		head.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		ListNode removeNthfromEnd = solution.deleteDuplicateII(head);
		while(removeNthfromEnd != null) {
			System.out.println(removeNthfromEnd.key);
			removeNthfromEnd = removeNthfromEnd.next;
		}
	}
}
