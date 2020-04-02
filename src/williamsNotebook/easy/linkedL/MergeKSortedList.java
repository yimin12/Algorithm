/**
 * 
 */
package williamsNotebook.easy.linkedL;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**
* @author yiminH-mail:hymlaucs@gmail.com
* @version Create Time��2019��10��23�� ����11:59:15
* Description:
* 	Merge K sorted lists into one big sorted list in ascending order.
* Assumption:
* 	ListOfLists is not null, and none of the lists is null.
* Trick:
* 	Special trick by using minHeap
*/

public class MergeKSortedList {
	public ListNode merge(List<ListNode> listOfLists) {
//		Assumptions: listOfLists is not null, none of the lists is null
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(11, new MyComparator());
		ListNode dummy = new ListNode(0);
		ListNode cur = dummy;
		for(ListNode node : listOfLists) {
			if(node != null) {
				minHeap.offer(node);
			}
		}
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
			if(o1.value == o2.value) {
				return 0;
			}
			return o1.value < o2.value ? -1:1;
		}
		
	}
}
class ListNode{
	int value;
	ListNode next;
	public ListNode(int value) {
		this.value = value;
	}
}
