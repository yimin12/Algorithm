/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.ListNode;

/**
 * @author yimin Huang
 *	
 *		Reverse the given LinkedList in different pattern and in different way
 *
 * Algorithm Class
 */
public class ReverseListNode {

	// Situation 1: reverse linked list
	// Method 1: iterative way, Time: O(n), Space: O(1)
	public ListNode reverseIterative(ListNode head) {
		// sanity check
		if(head == null || head.next == null) return head;
		ListNode prev  = null;
		while(head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;
			head = next;
		}
		return prev;
	}
	// Method 2: recursive way, Time: O(n), Space: O(n)
	public ListNode recursiveRecursive(ListNode head) {
		if(head == null || head.next == null);
		ListNode newHead = recursiveRecursive(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	
	// FollowUp : reverse Linked list in pair
	// Method 1: iterative way, Time: O(n), Space: O(1)
	public ListNode reverseInPairIterative(ListNode head) {
		if(head == null || head.next == null) return head;
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
	// Method 2: recursive way, Time: O(n), Space(n)
	public ListNode reverseInPairRecursive(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode newHead = head.next;
		head.next = reverseInPairRecursive(head.next.next);
		newHead.next = head;
		return newHead;
	}
	
	// FollowUp 2: Reverse a linked list from position m to n. Do it in-place and in one-pass.
	/*
	 * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4, return
	 * 		1->4->3->2->5->NULL.
	 * Assumption: 1 鈮� m 鈮� n 鈮� length of list.
	 */
	// Time ~ O(N), Space ~ O(1)
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy =  new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy, curr = dummy;
		for(int i = 0; i < m - 1; i++) {
			prev = prev.next;
		}
		curr = prev.next;
		for(int i = 0; i < n - m; i++) {
			ListNode temp = curr.next.next;
			curr.next.next = prev.next;
			prev.next = curr.next;
			curr.next = temp;
		}
		return dummy.next;
	}
	
	// FollowUp 3: Reverse Nodes in k-group
	/*
	 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list. 
	 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is. 
	 * You may not alter the values in the nodes, only nodes itself may be changed. Only constant memory
	 * is allowed. 
	 * For example, Given this linked list: 1->2->3->4->5 For k = 2, you
	 * should return: 2->1->4->3->5 For k = 3, you should return: 3->2->1->4->5
	 */
	// Time ~ O(2N), Space ~ O(1)
	public ListNode reverseKGroup(ListNode head, int k) {
		if(head == null || k < 0) throw new IllegalArgumentException("bye bye");
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		
		ListNode prev = dummy, cur = dummy.next;
		int pos = 1;
		while(cur != null) {
			if(pos == k) {
				// reset the position
				pos = 0;
				prev = reverse(prev, cur);
				cur = prev.next;
			} else {
				cur = cur.next;
			}
			pos++;
		}
		return dummy.next;
	}
	// the reverse range start from prev.next to end 
	private ListNode reverse(ListNode prev, ListNode end) {
		if(prev == null || end == null) return null;
		ListNode cur = prev.next;
		while(prev.next != end) {
			ListNode next = cur.next.next;
			cur.next.next = prev.next;
			prev.next = cur.next;
			cur.next = next;
		}
		return cur;
	}
	// Method 2: use for loop, Time ~ O(2N), Space ~ O(1)
	public ListNode reverseKGroupII(ListNode head, int k) {
		if(head == null || k < 0) throw new IllegalArgumentException("bye bye");
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		if(head == null || k == 0) return dummy.next;
		int len = 0;
		ListNode p = head;
		while(p != null) {
			p = p.next;
			len++;
		}
		ListNode prev = dummy, curr = dummy.next;
		for(int i = 0; i < len / k; i++) {
			for(int j = 0; j < k - 1; j++) {
				// do the reverse here
				ListNode temp = curr.next.next;
				curr.next.next = prev.next;
				prev.next = curr.next;
				curr.next = temp;
			}
			prev = curr;
			curr = curr.next;
		}
		return dummy.next;
	}
	
	// Follow Up 4: Reverse a linked list from position m _to _n. Do it in one-pass.
	// Input: 1->2->3->4->5->NULL, m = 2, n = 4
	// Output: 1->4->3->2->5->NULL
	// Assumption:  1 ≤m≤n≤ length of list.
	public ListNode revereseBetween(ListNode head, int m, int n) {
		// Assumption, if something is invalid, return itself
		if (head == null || head.next == null || m >= n) {
	           return head; 
	        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        for (int i = 1; i < m; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        } 
        ListNode prevNode = head, tailNode = head.next;
        ListNode cur = head.next;
        prevNode.next = null;
        for(int i = m; i <= n; i++) {
        	if(i == n) {
        		tailNode.next = cur.next;
        	}
        	ListNode next = cur.next;
        	cur.next = prevNode.next;
        	prevNode.next = cur;
        	cur = next;
        }
        return dummy.next;
		
	}
	public static void main(String[] args) {
		ReverseListNode solution = new ReverseListNode();
		ListNode head = new ListNode(1);
		ListNode two = new ListNode(2);
		ListNode three = new ListNode(3);
		ListNode four = new ListNode(4);
		ListNode five = new ListNode(5);
		head.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		ListNode reverseKGroup = solution.revereseBetween(head,2,4);
		while(reverseKGroup != null) {
			System.out.print(reverseKGroup.key + " ");
			reverseKGroup = reverseKGroup.next;
		}
	}
}
