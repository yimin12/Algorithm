/**
 * 
 */
package LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月27日 下午8:13:06
* Description:
* 	You are given two non-empty linked lists representing two non-negative integers.
*   The digits are stored in reverse order and each of their nodes contain a single digit. 
*   Add the two numbers and return it as a linked list.
*   You may assume the two numbers do not contain any leading zero, except the number 0 itself.
* Examples:
* 	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8
	Explanation: 342 + 465 = 807.
*/

public class AddTwoNumber {
	class ListNode{
		int val;
		ListNode next;
		ListNode(int val){
			this.val = val;
		}
	}
	public ListNode addTwoNumbers(ListNode node1, ListNode node2) {
		 ListNode dummy = new ListNode(0);
//		 we should create two pointer
		 ListNode p1 = node1;
		 ListNode p2 = node2;
		 ListNode cur = dummy;
//		 record whether it has added to 10
		 int carry = 0;
		 while(p1 != null || p2 != null) {
//			 record the value it read very time
			 int x = (p1 != null) ? p1.val : 0;
			 int y = (p2 != null) ? p2.val : 0;
			 int sum = carry + x + y;
			 carry = sum/10;
			 cur.next = new ListNode(sum % 10);
			 cur = cur.next;
			 if(p1 != null) p1 = p1.next;
			 if(p2 != null) p2 = p2.next;
		 }
		 if(carry > 0) {
			 cur.next = new ListNode(carry);
		 }
		 return dummy.next;
	}
}
