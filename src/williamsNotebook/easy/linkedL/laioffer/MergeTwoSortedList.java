/**
 * 
 */
package williamsNotebook.easy.linkedL.laioffer;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��9�� ����5:12:53
* Description: Merge two sorted Linkedlist, 
* 
* 		Assumption: 
* 			1) All the value of these two LinkedList are greater than 0;
* 			2) All the 
*/

public class MergeTwoSortedList {
	
	public ListNode merge(ListNode one, ListNode two) {
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		while(one != null && two != null) {
			if((int)one.data <= (int)two.data) {
				cur.next = one;
				one = one.next;
			} else {
				cur.next = two;
				two = two.next;
			}
			cur = cur.next;
		}
//		After one of these two linkedlist has been placed
//		we dont need to use while loop here, because they are already linked, just connect to their head
		if(one!=null) {
			cur.next = one;
		} else {
			cur.next = two;
		}
		return dummy.next;
	}
}
