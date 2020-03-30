/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.node.ListNode;

/**
 * @author yimin Huang
 *
 *	Write a program to find the node at which the intersection of two singly linked lists begins.
 *	For example, the following two linked lists:   begin to intersect at node c1.
 *			A:         a1 → a2
                                ↘
                                  c1 → c2 → c3
                                ↗            
			B:     b1 → b2 → b3
	Assumption:
		If the two linked lists have no intersection at all, return null
		The linked lists must retain their original structure after the function returns.
		You may assume there are no cycles anywhere in the entire linked structure.
		Your code should preferably run in O(n) time and use only O(1) memory.

 * Algorithm Class
 */
public class IntersectionOfTwoList {

	public ListNode getIntersection(ListNode one, ListNode two) {
		// Assumption:
		if(one == null || two == null) return null;
		ListNode curA = one, curB = two;
		int lenA = 0, lenB = 0;
		while(curA != null) {
			curA = curA.next;
			lenA++;
		}
		while(curB != null) {
			curB = curB.next;
			lenB++;
		}
		curA = one;
		curB = two;
		for(int i = 0; i < Math.abs(lenA - lenB); i++) {
			if(lenA > lenB) curA = curA.next;
			else curB = curB.next;
		}
		while(curA != null && curB != null) {
			if(curA == curB) return curA;
			curA = curA.next;
			curB = curB.next;
		}
		return null;
	}
}
