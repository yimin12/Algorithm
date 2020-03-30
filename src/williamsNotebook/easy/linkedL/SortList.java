/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.node.ListNode;

/**
 * @author yimin Huang
 *
 *	Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Algorithm Class
 */
public class SortList {
	
	// Time ~ O(NlogN), Space ~ O(n)
	// Find the middle point, use two pointers: slow and fast
	public ListNode sortList(ListNode head) {
		if(head == null || head.next == null)  return head;
		// find the middle point (slow pointing to mid)
		ListNode slow = head, fast = head;
		// step 1: find the middle point
		while(fast != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		// step 2: merge lists recursively
		ListNode left = head, right = slow.next;
		slow.next = null; // break the list into two parts
		// divide
		left = sortList(left);
		right = sortList(right);
		// conquer
		return merge(left, right);
	}
	// merge the list in order recursive
	private ListNode merge(ListNode left, ListNode right) {
		// base case
		if(left == null) return right;
		if(right == null) return left;
		
		ListNode dummy = new ListNode(0);
		ListNode p = dummy;
		while(left != null && right != null) {
			if(left.key < right.key) {
				p.next = left;
				left = left.next;
			} else {
				p.next = right;
				right = right.next;
			}
			p = p.next;
		}
		// post processing
		if(left != null) {
			p.next = left;
		} else {
			p.next = right;
		}
		return dummy.next;
	}
	// other method of merge
	private ListNode mergeI(ListNode left, ListNode right) {
		ListNode dummy = new ListNode(0);
		dummy.next = left;
		ListNode prev = dummy;
		while(left != null && right != null) {
			if(left.key < right.key) {
				left = left.next;
			} else {
				ListNode next = right.next;
				right.next = prev.next;
				prev.next = right;
				right = next;
			}
			prev = prev.next;
		}
		if(right != null) prev.next = right;
		return dummy.next;
	}
	
	// FollowUp 1: Quick Sort LinkedList: Partition List

	/*
	 * Given a linked list and a value x, partition it such that all nodes less than
	 * x come before nodes greater than or equal to x. You should preserve the
	 * original relative order of the nodes in each of the two partitions. 
	 * For example, Given 1->4->3->2->5->2 and x = 3, return 1->2->2->4->3->5.
	 */
	public ListNode partition(ListNode head, int target) {
		if(head == null || head.next == null) {
			return head;
		}
		// create small dummy, and large dummy
		ListNode small = new ListNode(0);
		ListNode large = new ListNode(0);
		ListNode curSmall =  small;
		ListNode curLarge = large;
		while(head != null) {
			if(head.key < target) {
				curSmall.next = head;
				curSmall = curSmall.next;
			} else {
				curLarge.next = head;
				curLarge = curLarge.next;
			}
			head = head.next;
		}
		// post Processing : connect the curSmall and the curLarge
		curSmall.next = large;
		curLarge.next = null;
		return small.next;
	}
	
	// FollowUp 2: Reorder the given singly-linked list N1->N2->N3->N4->...Nn->null be N1->Nn->N2->Nn-1->N3...->null 
	// Time: O(n), Space: O(1);
	public ListNode reorder(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		// step 1: find the middle node
		ListNode mid = findMid(head);
		ListNode one = head;
		ListNode two = mid.next;
		// step 2: de-link second half the list
		mid.next = null;
		// step 3: reverse the second and merge
		return reorder(one, reverse(two));
	}
	// Time: O(n) Space: O(1)
	private ListNode findMid(ListNode head) {
		ListNode slow = head, fast = head;
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	// Time: O(n) Space: O(1)
	private ListNode reverse(ListNode head) {
		// sanity check
		if(head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		while(head != null) {
			ListNode next = head.next;
			head.next = prev;
			prev = head;	
			head = next;
		}
		return prev;
	}
	private ListNode reorder(ListNode one, ListNode two) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while(one != null && two != null) {
			cur.next = one;
			one = one.next;
			cur.next.next = two;
			two = two.next;
			cur = cur.next.next;
		}
		// post processing: connect the rest
		if(one != null) {
			cur.next = one;
		} 
		if(two != null) {
			cur.next = two;
		}
		return dummy.next;
	}
	// test case
	public static void main(String[] args) {
		SortList solution = new SortList();
		ListNode head = new ListNode(1);
		ListNode two = new ListNode(2);
		ListNode three = new ListNode(3);
		ListNode four = new ListNode(4);
		ListNode five = new ListNode(5);
		head.next = two;
		two.next = three;
		three.next = four;
		four.next = five;
		ListNode reorder = solution.reorder(head);
		while(reorder != null) {
			System.out.println(reorder.key);
			reorder = reorder.next;
		}
	}
}
