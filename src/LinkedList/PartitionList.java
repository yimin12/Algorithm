/**
 * 
 */
package LinkedList;

/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time：2019年10月9日 下午5:39:25
* Description:
* 	Given a linked list and a target value T, partition 
* 	it such that all nodes less than T are listed before 
* 	the nodes larger than or equal to target value T. 
* 	The original relative order of the nodes in each of
	the two partitions should be preserved.
  Example:
  	L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, 
  	is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
*/

public class PartitionList {
	public ListNode partition(ListNode head, int target) {
		if(head == null || head.next == null) {
			return head;
		}
//		keep the value that is smaller than target
		ListNode small = new ListNode(0);
//		keep the value that is larger than target
		ListNode large = new ListNode(0);
//		dummyHead for small
		ListNode curSmall = small;
//		dummyHead for large
		ListNode curLarge = large;
//		linear scan the LinkedList, and use two LInkedLIst to maintain the order
		while(head != null) {
			if((int)head.data < target) {
				curSmall.next = head;
				curSmall = curSmall.next;
			} else {
				curLarge.next = head;
				curLarge = curLarge.next;
			}
			head = head.next;
		}
//		connect two partition
		curSmall.next = large.next;
//		un-link the last node in large partition
		curLarge.next = null;
		return small.next;
	}
}
