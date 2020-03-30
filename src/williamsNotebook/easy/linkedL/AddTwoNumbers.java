/**
 * 
 */
package williamsNotebook.easy.linkedL;

import williamsNotebook.common.node.ListNode;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2020��3��18�� ����3:30:31
* Description:
* 	You are given two non-empty linked lists representing two non-negative integers. 
*   The digits are stored in reverse order and each of their nodes contain a single digit. Add 
*   the two numbers and return it as a linked list.
*   
*/
public class AddTwoNumbers {

	// Follow Up 1: 
	// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	// Output: 7 -> 0 -> 8
	// Explanation: 342 + 465 = 807.
	public ListNode addTwoNumbers(ListNode one, ListNode two) {
		// Assumption: Sanity Check
		if(one == null && two  == null) return null;
		if(one == null || two == null) return one == null ? two : one;
		ListNode dummy = new ListNode(0);
		ListNode pOne = one, pTwo = two, curr = dummy;
		int carry = 0;
		while(pOne != null || pTwo != null) {
			int x = pOne == null ? 0 : pOne.key;
			int y = pTwo == null ? 0 : pTwo.key;
			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if(pOne != null) pOne = pOne.next;
			if(pTwo != null) pTwo = pTwo.next;
		}
		// post processing, check the carray
		if(carry > 0) {
			curr.next = new ListNode(carry);
		}
		return dummy.next;
	}
	// Follow Up 2: start from highest digit
	// Given 6->1->7 + 2->9->5. That is, 617 + 295.
	// Return 9->1->2. That is, 912.
	public ListNode addTwoNumbersII(ListNode one, ListNode two) {
		// sanity check
		if(one == null && two  == null) return null;
		if(one == null || two == null) return one == null ? two : one;
		// step 1: reverse
		ListNode pOne = reverse(one);
		ListNode pTwo = reverse(two);
		// step 2: add numbers exactly the same way as the former one
		return reverse(addTwoNumbers(pOne, pTwo));
	}
	private ListNode reverse(ListNode head) {
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
		AddTwoNumbers solution = new AddTwoNumbers();
		ListNode one = new ListNode(2);
		ListNode aa = new ListNode(4);
		ListNode ab = new ListNode(3);
		ListNode ac = new ListNode(3);
		ListNode two = new ListNode(9);
		ListNode ba = new ListNode(6);
		ListNode bb = new ListNode(4);
		ListNode bc = new ListNode(9);
		one.next = aa; aa.next = ab; ab.next = ac;
		two.next = ba; ba.next = bb; bb.next = bc;
		
		ListNode addTwoNumbers = solution.addTwoNumbersII(null, null);
		while(addTwoNumbers != null) {
			System.out.print(addTwoNumbers.key + " ");
			addTwoNumbers = addTwoNumbers.next;
		}
	}
	
}
