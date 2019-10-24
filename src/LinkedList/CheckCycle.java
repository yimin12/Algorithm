/**
 * 
 */
package LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月9日 下午4:54:45
* Description:
* 	Check whether the LinkedList has circulation
*/

public class CheckCycle {
	
	public boolean hasCycle(ListNode head) {
		if(head == null || head.next == null) {
			return false;
		}
		ListNode slow = head;
		ListNode fast = head;
		while(fast != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast) {
				return true;
			}
		}
		return false;
	}
}
