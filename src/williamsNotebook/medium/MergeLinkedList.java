/**
 * 
 */
package williamsNotebook.medium;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import williamsNotebook.common.ListNode;

/**
 * @author yimin Huang
 * 
 *		Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Algorithm Class
 */
public class MergeLinkedList {
	
	
	// linear traversal to merge two sorted linked list
	// Time: O(n+m), Space: O(1)
	public ListNode mergeTwoList(ListNode one, ListNode two) {
		// one easy way to handle multiple head is using dummy head
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while(one != null && two != null) {
			if(one.key < two.key) {
				cur.next = one;
				one = one.next;
			} else {
				cur.next = two;
				two = two.next;
			}
			cur = cur.next;
		}
		// post processing
		if(one != null) {
			cur.next = one;
		} else {
			cur.next = two;
		}
		return dummy.next;
	}
	
	// merge K sorted List, you can implement your own comparator
	// Time: O(k * n), Space: O(1)
	public ListNode mergeKList(List<ListNode> listOfLists) {
		// should sorted these k headers by priorityQueue
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(11, new MyComparator());
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		// step 1: offer all the node to minHeap, O(klogk)
		for(ListNode node:listOfLists) {
			if(node != null) {
				minHeap.offer(node);
			}
		}
		// step 2: get the next larger node from priority queue ( k*n*logk)
		while(!minHeap.isEmpty()) {
			cur.next = minHeap.poll();
			if(cur.next.next != null) {
				minHeap.offer(cur.next.next);
			}
			cur = cur.next;
		}
		return dummy.next;
	}
	
	static class MyComparator implements Comparator<ListNode>{

		@Override
		public int compare(ListNode o1, ListNode o2) {
			if(o1.key == o1.key) {
				return 0;
			}
			return o1.key < o2.key ? -1 : 1;
		}
		
	}
}
